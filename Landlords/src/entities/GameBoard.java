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
import helpers.Helper;
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
		Messenger.waiting();
	}

	private void checkWinner() {
		// TODO Auto-generated method stub
		if (room.getLastHandPlayer().getRole() == PlayerRole.LANDLORD)
			Messenger.print("Landlord wins!");
		else
			Messenger.print("Peasants win!");
	}

	private void setNickName() {
		// TODO Auto-generated method stub
		for (Player player : room.getPlayers()) {
			this.playerController.storeAndExecute(new SetNicknameCommand(player));
		}
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
		Helper.sortCards(players.get(landlordID).getCards());

		Messenger.print("The landlord is Player " + players.get(landlordID).getNickname());
		Messenger.print("Landlord cards:");
		Messenger.print(Messenger.printCards(this.room.getLandlordCards()));
		Messenger.waiting();
	}

	public void gameStart() {
		boolean isFinish = false;
		int cursor = room.getLandlordID();
		List<Player> players = this.room.getPlayers();
		LinkedList<Hand> handHistoty = this.room.getHandHistoty();

		Messenger.clear();
		Messenger.print("Game Start!\n");

		while (!isFinish) {
			Player player = players.get(cursor);
			List<Card> playerCards = player.getCards();
			Messenger.waitForPlayer(player);
			Messenger.clear();
			Messenger.print(Messenger.playersInfo(cursor, this.room)); // TODO: Modification

			while (true) {
				try {
					Command<String> playChoiceCommand = new PlayChoiceCommand(player);
					this.playerController.storeAndExecute(playChoiceCommand);
					String cmd = playChoiceCommand.getResult(); // TODO: refactor it to "redo" 
					if (cmd.toUpperCase().equals("PASS")) {
						if (handHistoty.isEmpty() || room.getLastHandPlayer() == player) {
							Messenger.print("Cannot pass.");
							continue;
						} else {
							handHistoty.add(new Hand(null, null, null, 0, new ArrayList<Card>()));
							break;
						}
					}
					
					ArrayList<String> inputCardNames = new ArrayList<String>();
					Scanner cmdScanner = new Scanner(cmd);
					while (cmdScanner.hasNext()) // TODO: exception handle
						inputCardNames.add(cmdScanner.next());
					cmdScanner.close();

//				if (inputCardNames.size() == 0 || !Helper.isValidInputCardNames(inputCardNames)) {
//					Messenger.print(Messenger.inputErrorMessage()); // TODO: Exception Handler
//					continue;
//				}
					if (inputCardNames.size() == 0 || !Helper.isValidInputCardNames(inputCardNames)) {
						throw new InputInvalidException();
					}

					List<Card> selectedCards = player.checkCardsOnHand(inputCardNames); // check if cards are on hand
//				if (selectedCards == null) {
//					Messenger.print(Messenger.cardsNotOnHandError());
//					continue;
//				}
					if (selectedCards == null) {
						throw new CardsNotOnHandException();
					}

					Hand currHand = Hand.cards2hand(selectedCards);
//				if (currHand.getType() == HandType.ILLEGAL) {
//					Messenger.print(Messenger.disobeyRulesError());
//					continue;
//				}
					if (currHand.getType() == HandType.ILLEGAL) {
						throw new DisobeyRulesException();
					}
					
					if (room.getLastHandPlayer() == null || room.getLastHandPlayer() == player
							|| handHistoty.isEmpty() || handHistoty.getLast().isSmallerThan(currHand) == true) {
						player.removeCards(selectedCards);
						room.setLastHandPlayer(player);
						handHistoty.add(currHand);
						break;
					} else {
//					System.out.println(Messenger.disobeyRulesError());
//					continue;
						throw new DisobeyRulesException();
					}
				} catch (InputInvalidException e1) {
					Messenger.print(e1.getMessage());
				} catch (CardsNotOnHandException e2) {
					Messenger.print(e2.getMessage());
				} catch (DisobeyRulesException e3) {
					Messenger.print(e3.getMessage());
				}
			}

			if (!handHistoty.getLast().getCards().isEmpty())
				Messenger.print(Messenger.printCards(handHistoty.getLast().getCards()));

			Messenger.waiting();
			Messenger.clear();

			// check finish
			if (player.getCards().size() == 0)
				isFinish = true;

			// Room info update
			cursor = (cursor + 1) % 3;
		}
	}
}
