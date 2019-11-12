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
		int cursor = new Random().nextInt(3);
		int landlordID = 0;
		int nWaive = 0;

		for (int i = 0; i < 4; ++i) {
			if (i == 3) {
				if (nWaive == 3) { // all waive
					landlordID = new Random().nextInt(3);
					break;
				} else if (nWaive == 2) // two players waive
					break;
				else if (nWaive == 1) // one player waives
					do
						cursor = (cursor + 1) % 3;
					while (choices.get(cursor));
				else if (nWaive == 0) // all run for landlord
					cursor = (cursor + 1) % 3;
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
	}
	
	public void gameStart() {
		boolean isFinish = false;
		int cursor = room.getLandlordID();
		List<Player> players = this.room.getPlayers();

		Messenger.clear();
		Messenger.print("Game Start!\n");

		while (!isFinish) {
			Player player = players.get(cursor);
			List<Card> playerCards = player.getCards();
			Messenger.waitForPlayer(player);
			Messenger.clear();
			Command<String> playChoiceCommand = new PlayChoiceCommand();
			this.playerController.storeAndExecute(playChoiceCommand);
			
			// TODO: Modification
			Messenger.print(Messenger.playersInfo(players, cursor, previousCardsList));

			while (true) {
				ArrayList<String> anwser = player.playCards(room.getLastHand().getCards());
				if (anwser.equals("HELP")) {
					Messenger.print(Messenger.inputHelp()); // TODO: need to be implemented
					continue;
				}

				if (anwser.equals("PASS")) {
					// TODO: Landlord cannot pass in the first round? or cannot pass in the
					// winning round?
					if (room.getLastHandPlayer() == null) {
						Messenger.print("Cannot pass in first round");
						continue;
					}

					previousCardsList.add(new ArrayList<Card>());
					if (previousCardsList.size() >= 3)
						previousCardsList.remove();
					break;
				}

				if (anwser.size() == 0 || !Helper.isValidInputCardNames(anwser)) {
					System.out.println(Messenger.inputErrorMessage());
					continue;
				}

				List<Card> selectedCards = player.checkCardsOnHand(anwser); // check if cards are on hand
				if (selectedCards == null) {
					System.out.println(Messenger.cardsNotOnHandError());
					continue;
				}

				Hand currHand = Hand.cards2hand(selectedCards);
				if (currHand.getType() == HandType.ILLEGAL) {
					System.out.println(Messenger.disobeyRulesError());
					continue;
				}

				Hand lastHand = room.getLastHand();
				if (room.getLastHandPlayer() == null || room.getLastHandPlayer() == player
						|| lastHand.compareTo(currHand) < 0) {
					player.removeCards(selectedCards);
					room.setLastHand(currHand);
					room.setLastHandPlayer(player);
					previousCardsList.add(selectedCards);
					if (previousCardsList.size() >= 3)
						previousCardsList.remove();
					break;
				} else {
					System.out.println(Messenger.disobeyRulesError());
					continue;
				}
			}

			if (!previousCardsList.getLast().isEmpty())
				Messenger.print(Messenger.printCards(previousCardsList.getLast()));

			Messenger.waiting();
			Messenger.clear();

			// check finish
			if (player.getCards().size() == 0)
				finishFlag = true;

			// Room info update
			cursor = (cursor + 1) % 3;
		}
	}
}
