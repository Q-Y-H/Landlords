package entities;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import enums.PlayerRole;
import helpers.Helper;
import helpers.Messenger;

public class humanPlayer extends Player{
	public humanPlayer(String nickname, PlayerRole role) {
		super(nickname,role);
	}
	
	public humanPlayer(String nickname) {
		super(nickname, null);
	}
	
	public humanPlayer() {
		super(null, null);
	}
	
	@Override
	public boolean playCards(Scanner in,CardRoom room) {
		LinkedList<List<Card>> previousCardsList = room.getPreviousCardsList();
		Messenger.print("Please choose the cards to play. Input 'help' for example inputs.\n");
		Messenger.printAskForInput("[" + this.getRole() + "] " + this.getNickname() + " >> ");
		
		// Input Processing
		String cmd = in.nextLine();
		if (cmd.toUpperCase().equals("HELP")) {
			Messenger.print(Messenger.inputHelp()); // TODO: need to be implemented
			return false;
		}
		
		if (cmd.toUpperCase().equals("PASS")) { 
			// TODO: Landlord cannot pass in the first round? or cannot pass in the
			// winning round?
			previousCardsList.add(new ArrayList<Card>());
			if (previousCardsList.size() >= 3)
				previousCardsList.remove();
			return true;
		}
		
		ArrayList<String> inputCardNames = new ArrayList<String>();
		Scanner cmdScanner = new Scanner(cmd);
		while (cmdScanner.hasNext()) { // TODO: exception handle
			inputCardNames.add(cmdScanner.next());
		}
		cmdScanner.close();

		// TODO: if input nothing, PASS or re-input?
		if (inputCardNames.size() == 0) {
			System.out.println(Messenger.inputErrorMessage());
			return false;
		}
		
		if (!Helper.isValidInputCardNames(inputCardNames)) { // check if valid string input
			System.out.println(Messenger.inputErrorMessage());
			return false;
		}
		
		List<Card> selectedCards = this.checkCardsOnHand(inputCardNames); // check if cards are on hand
		if (selectedCards == null) {
			System.out.println(Messenger.cardsNotOnHandError());
			return false;
		}

		Hand currHand = Hand.cards2hand(selectedCards);
		Hand lastHand = room.getLastHand();
		if (room.getLastHandPlayer() == null || room.getLastHandPlayer() == this
				|| lastHand.compareTo(currHand) < 0) {
			this.removeCards(selectedCards);
			room.setLastHand(currHand);
			room.setLastHandPlayer(this);
			previousCardsList.add(selectedCards);
			if (previousCardsList.size() >= 3)
				previousCardsList.remove();
			return true;
		} else {
			System.out.println(Messenger.disobeyRulesError());
			return true;
		}
	}

	
}
