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

public class GameBoard {
	public int rand;
	private CardRoom room;
	private PlayerController playerController;
	private Messenger messenger;

	public GameBoard(CardRoom room) {
		this.room = room;
		this.playerController = new PlayerController();
		this.messenger = Messenger.getInstance();
	}

	public void run() {
		room.setup();
		setNickname();
		electLandlord();
		gameStart();
		checkWinner();
	}

	private void setNickname() {
		// TODO Auto-generated method stub
		for (Player player : room.getPlayers()) {
			this.playerController.storeAndExecute(new SetNicknameCommand(player));
		}
	}

	private void electLandlord() {
		List<Player> players = this.room.getPlayers();
		List<Boolean> choices = new ArrayList<Boolean>();
		int cursor = new Random().nextInt(3);//range: 0, 1, 2
		rand = cursor;
		int landlordID = 0;
		int nWaive = 0;

		for (int i = 0; i < 4; ++i) {
			if (i == 3) {
				if (nWaive == 3) { // all waive
					landlordID = new Random().nextInt(3);
					rand = landlordID;
					break;
				} 
				else if (nWaive == 2) // two players waive
					break;
				else if (nWaive == 1 && !choices.get(0)) // one player waives
					cursor = (cursor + 1) % 3;
				// all run for landlord: give the chance to the first player
			}

			Player player = players.get(cursor);
			Command<Boolean> runForLandlord = new DecideRunForLandlordCommand(player);
			this.playerController.storeAndExecute(runForLandlord);

			Boolean isRunningForLandlord = runForLandlord.getResult();
			choices.add(isRunningForLandlord);
			if (isRunningForLandlord)
				landlordID = cursor;
			else
				++nWaive;

			cursor = (cursor + 1) % 3;
		}

		room.setLandlordID(landlordID);
		players.get(landlordID).setRole(PlayerRole.LANDLORD);
		players.get(landlordID).getCards().addAll(this.room.getLandlordCards());
		CardRoom.sortCards(players.get(landlordID).getCards());

		this.messenger.print("The landlord is Player " + players.get(landlordID).getNickname());
		this.messenger.print("Landlord cards:");
		this.messenger.print(this.messenger.printCards(this.room.getLandlordCards()));
		this.messenger.waiting();
	}

	public void gameStart() {
		boolean isFinish = false;
		int cursor = room.getLandlordID();
		List<Player> players = this.room.getPlayers();
		LinkedList<Hand> handHistory = this.room.getHandHistory();

		this.messenger.clear();
		this.messenger.print("Game Start!\n");

		while (!isFinish) {
			Player player = players.get(cursor);
			this.messenger.waitForPlayer(player);
			this.messenger.clear();
			this.messenger.print(this.messenger.prevPlayersInfo(cursor, this.room));

			while (true) {
				try {
					Command<String> playChoiceCommand = new PlayChoiceCommand(player);
					this.playerController.storeAndExecute(playChoiceCommand);
					String cmd = playChoiceCommand.getResult();

					if(cmd.toUpperCase().equals("SUGGEST")) {
						Messenger.getInstance().inputSuggest(player, handHistory.getLast());
						continue;
					}

					if(cmd.toUpperCase().equals("HELP")) {
						Messenger.getInstance().inputHelp(handHistory.getLast());
						continue;
					}

					if (cmd.toUpperCase().equals("PASS")) {
						if (handHistory.isEmpty() || room.getLastHandPlayer() == player) {
							this.messenger.print("Cannot pass.");
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

					if (inputCardNames.size() == 0 || !isValidInputCardNames(inputCardNames)) {
						throw new InputInvalidException();
					}

					List<Card> selectedCards = player.checkCardsOnHand(inputCardNames); // check if cards are on hand

					if (selectedCards == null) {
						throw new CardsNotOnHandException();
					}

					Hand currHand = Hand.cards2hand(selectedCards);

					if (currHand.getType() == HandType.ILLEGAL) {
						throw new DisobeyRulesException();
					}

					/* compare lastHand with currHand */
					Hand lastHand;
					if (room.getLastHandPlayer() == null || room.getLastHandPlayer() == player || handHistory.isEmpty())
						lastHand = new Hand(HandType.ILLEGAL, null, null, 0, new ArrayList<Card>());
					else
						lastHand = handHistory.getLast();
					// if the last player "PASS"
					if (lastHand.getCards().size() == 0) {
						int index = handHistory.size() - 2;
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
					} else {
						throw new DisobeyRulesException();
					}
				} catch (InputInvalidException e1) {
					this.messenger.print(e1.getMessage());
				} catch (CardsNotOnHandException e2) {
					this.messenger.print(e2.getMessage());
				} catch (DisobeyRulesException e3) {
					this.messenger.print(e3.getMessage());
				}
			}

			if (!handHistory.getLast().getCards().isEmpty())
				this.messenger.print(this.messenger.printCards(handHistory.getLast().getCards()));

			this.messenger.waiting();
			this.messenger.clear();

			// check finish
			if (player.getCards().size() == 0)
				isFinish = true;

			// update active player
			cursor = (cursor + 1) % 3;
		}
	} 
	
	public boolean isValidInputCardNames(ArrayList<String> cardNames) {
		for (String cardName : cardNames) {
			if (!Rank.aliasSetContains(cardName))
				return false;
		}
		return true;
	}
	
	private void checkWinner() {
		if (room.getLastHandPlayer().getRole() == PlayerRole.LANDLORD)
			this.messenger.print("Landlord wins!");
		else
			this.messenger.print("Peasants win!");
		this.messenger.waiting();
	}
}
