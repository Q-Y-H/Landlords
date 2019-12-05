package entities;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import Commands.Command;
import Commands.DecideRunForLandlordCommand;
import Commands.PlayChoiceCommand;
import Commands.SetNicknameCommand;
import Exceptions.CardsNotOnHandException;
import Exceptions.DisobeyRulesException;
import Exceptions.InputInvalidException;
import enums.HandType;
import enums.PlayerRole;
import enums.Rank;
import helpers.Messenger;

public class GameBoard {
	private CardRoom room;
	private PlayerController playerController;

	public GameBoard(CardRoom room) {
		this.room = room;
		this.playerController = new PlayerController();
	}

	public void run() {
		room.setup();
		setNickName();
		electLandlord();
		gameStart();
		checkWinner();
		Messenger.getInstance().waiting();
	}

	private void checkWinner() {
		// TODO Auto-generated method stub
		if (room.getLastHandPlayer().getRole() == PlayerRole.LANDLORD)
			Messenger.getInstance().print("Landlord wins!");
		else
			Messenger.getInstance().print("Peasants win!");
	}

	private void setNickName() {
		// TODO Auto-generated method stub
		for (Player player : room.getPlayers()) {
			this.playerController.storeAndExecute(new SetNicknameCommand(player));
		}
	}
	
	public boolean isValidInputCardNames(ArrayList<String> cardNames) {
		for (String cardName : cardNames) {
			if (!Rank.aliasSetContains(cardName))
				return false;
		}
		return true;
	}
	
	public void electLandlord() {
		List<Player> players = this.room.getPlayers();
		List<Boolean> choices = new ArrayList<Boolean>();
		int cursor = new Random().nextInt(3);//range: 0, 1, 2
		int landlordID = 0;
		int nWaive = 0;

		for (int i = 0; i < 4; ++i) {
			if (i == 3) {
				if (nWaive == 3) { // all waive
					landlordID = new Random().nextInt(3);
					break;
				} 
				else if (nWaive == 2) // two players waive
					break;
				else if (nWaive == 1) // one player waives
					if(!choices.get(0)) cursor = (cursor + 1) % 3;
				else if (nWaive == 0) ;// all run for landlord: give the chance to the first player
			}

			Player player = players.get(cursor);
			Command<Boolean> runForLandlord = new DecideRunForLandlordCommand(player);
			this.playerController.storeAndExecute(runForLandlord);

			Boolean isRunningForLandlord = runForLandlord.getResult();
			choices.add(isRunningForLandlord);
			if (isRunningForLandlord)
				landlordID = cursor;
			else
				nWaive++;

			cursor = (cursor + 1) % 3;
		}

		room.setLandlordID(landlordID);
		players.get(landlordID).setRole(PlayerRole.LANDLORD);
		players.get(landlordID).getCards().addAll(this.room.getLandlordCards());
		CardCase.sortCards(players.get(landlordID).getCards());

		Messenger.getInstance().print("The landlord is Player " + players.get(landlordID).getNickname());
		Messenger.getInstance().print("Landlord cards:");
		Messenger.getInstance().print(Messenger.getInstance().printCards(this.room.getLandlordCards()));
		Messenger.getInstance().waiting();
	}

	public void gameStart() {
		boolean isFinish = false;
		int cursor = room.getLandlordID();
		List<Player> players = this.room.getPlayers();
		LinkedList<Hand> handHistory = this.room.getHandHistory();

		Messenger.getInstance().clear();
		Messenger.getInstance().print("Game Start!\n");

		while (!isFinish) {
			Player player = players.get(cursor);
			//List<Card> playerCards = player.getCards();
			Messenger.getInstance().waitForPlayer(player);
			Messenger.getInstance().clear();
			Messenger.getInstance().print(Messenger.getInstance().playersInfo(cursor, this.room)); // TODO: Modification

			while (true) {
				try {
					Command<String> playChoiceCommand = new PlayChoiceCommand(player);
					this.playerController.storeAndExecute(playChoiceCommand);
					String cmd = playChoiceCommand.getResult(); // TODO: refactor it to "redo"


					if (cmd.toUpperCase().equals("PASS")) {
						if (handHistory.isEmpty() || room.getLastHandPlayer() == player) {
							Messenger.getInstance().print("Cannot pass.");
							continue;
						} else {
							handHistory.add(new Hand(null, null, null, 0, new ArrayList<Card>()));
							break;
						}
					}

					ArrayList<String> inputCardNames = new ArrayList<String>();
					Scanner cmdScanner = new Scanner(cmd);
					while (cmdScanner.hasNext()) // TODO: exception handle
						inputCardNames.add(cmdScanner.next());
					cmdScanner.close();

//				if (inputCardNames.size() == 0 || !Helper.isValidInputCardNames(inputCardNames)) {
//					Messenger.getInstance().print(Messenger.getInstance().inputErrorMessage()); // TODO: Exception Handler
//					continue;
//				}
					if (inputCardNames.size() == 0 || !isValidInputCardNames(inputCardNames)) {
						throw new InputInvalidException();
					}

					List<Card> selectedCards = player.checkCardsOnHand(inputCardNames); // check if cards are on hand
//				if (selectedCards == null) {
//					Messenger.getInstance().print(Messenger.getInstance().cardsNotOnHandError());
//					continue;
//				}
					if (selectedCards == null) {
						throw new CardsNotOnHandException();
					}

					Hand currHand = Hand.cards2hand(selectedCards);
//				if (currHand.getType() == HandType.ILLEGAL) {
//					Messenger.getInstance().print(Messenger.getInstance().disobeyRulesError());
//					continue;
//				}
					if (currHand.getType() == HandType.ILLEGAL) {
						throw new DisobeyRulesException();
					}

					/*compare lastHnad with currHand*/
					Hand lastHand;
					if(room.getLastHandPlayer() == null || room.getLastHandPlayer() == player
							|| handHistory.isEmpty()) //create a fake hand
						lastHand = new Hand(HandType.ILLEGAL,null, null, 0, new ArrayList<Card>());
					else
						lastHand = handHistory.getLast();
					//if the last player "PASS"
					if(lastHand.getType() == null) {
						int index = handHistory.size()-2;
						lastHand = handHistory.get(index);
					}
					
					Hand lastValidHand=null;
					for(int i=handHistory.size()-1;i>=0;i--) {
						if(handHistory.get(i).getType()!=null) {
							lastValidHand=handHistory.get(i);
							break;
						}					
					}
					
					if (room.getLastHandPlayer() == null || room.getLastHandPlayer() == player
							|| handHistory.isEmpty() || lastValidHand.isSmallerThan(currHand) == true) {
						player.removeCards(selectedCards);
						room.setLastHandPlayer(player);
						handHistory.add(currHand);
						break;
					}
					else {
//					System.out.println(Messenger.getInstance().disobeyRulesError());
//					continue;
						throw new DisobeyRulesException();
					}
				} catch (InputInvalidException e1) {
					Messenger.getInstance().print(e1.getMessage());
				} catch (CardsNotOnHandException e2) {
					Messenger.getInstance().print(e2.getMessage());
				} catch (DisobeyRulesException e3) {
					Messenger.getInstance().print(e3.getMessage());
				}
			}

			if (!handHistory.getLast().getCards().isEmpty())
				Messenger.getInstance().print(Messenger.getInstance().printCards(handHistory.getLast().getCards()));

			Messenger.getInstance().waiting();
			Messenger.getInstance().clear();

			// check finish
			if (player.getCards().size() == 0)
				isFinish = true;

			// Room info update
			cursor = (cursor + 1) % 3;
		}
	}
}
