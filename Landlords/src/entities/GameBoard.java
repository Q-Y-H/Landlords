package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import enums.PlayerRole;

public class GameBoard {
	private CardRoom room;
	private Messenger messenger;

	public GameBoard(CardRoom room) {
		this.room = room;
		this.messenger = Messenger.getInstance();
		this.messenger.setCardRoom(room);
	}

	public void run() {
		room.setup();
		room.askForNicknames();
		claimLandlord();
		this.messenger.showLandlordInfo();
		runGame();
		checkWinner();
	}

	private void claimLandlord(int initCursor) {
		List<Player> players = this.room.getPlayers();
		List<Boolean> choices = new ArrayList<Boolean>();
		int currCursor = initCursor;
		int landlordID = 0;
		int nWaive = 0;

		for (int i = 0; i < 4; ++i) {
			if (i == 3) {
				if (nWaive == 1 && !choices.get(0)) // one player waives
					currCursor = ++currCursor % 3;
				else if (nWaive == 2) // two players waive
					break;
				else if (nWaive == 3) { // all waive
					landlordID = new Random().nextInt(3);
					break;
				} // all run for landlord: give the chance to the first player
			}

			this.messenger.handleClaimLandlord(choices, players, currCursor, initCursor);
			this.messenger.println("");
			Boolean isClaimLandlord = this.room.askForClaimLandlord(currCursor);

			if (isClaimLandlord)
				landlordID = currCursor;
			else
				++nWaive;

			choices.add(isClaimLandlord);
			currCursor = ++currCursor % 3;
		}

		room.updateLandlord(landlordID);
	}

	private void claimLandlord() {
		claimLandlord(new Random().nextInt(3));
	}

	public void runGame() {
		int cursor = room.getLandlordID() - 1;
		List<Player> players = this.room.getPlayers();

		this.messenger.clear();
		this.messenger.printBetweenBanner("Game Start!");
		this.messenger.println("");

		do {
			cursor = ++cursor % 3;
			Player player = players.get(cursor);
			this.messenger.waitForPlayer(player);
			this.messenger.clear();
			this.messenger.print(this.messenger.prevPlayersInfo(cursor, this.room));
			boolean isValidInput = false;

			while (!isValidInput) {
				String cmd = this.room.askForPlayChoice(cursor);
				isValidInput = this.room.processPlayerChoice(cursor, cmd);
			}

			Hand lastHand = this.room.getHandHistory().getLast();
			if (!lastHand.getCards().isEmpty())
				this.messenger.println(this.messenger.printCards(lastHand.getCards()));

			this.messenger.waiting();
			this.messenger.clear();

			this.room.updateRecentHands();
		} while (!this.room.checkFinished(cursor));
	}

	private void checkWinner() {
		if (this.room.checkLastPlayerRole() == PlayerRole.LANDLORD)
			this.messenger.printBetweenBanner("Landlord wins!");
		else
			this.messenger.printBetweenBanner("Peasants win!");
		this.messenger.waiting();
	}
}
