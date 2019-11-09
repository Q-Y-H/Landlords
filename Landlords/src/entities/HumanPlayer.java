package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import enums.HandType;
import enums.PlayerRole;
import helpers.Helper;
import helpers.Messenger;

public class HumanPlayer extends Player{

	public HumanPlayer(String nickname, PlayerRole role) {
		super(nickname, role);
	}

	public HumanPlayer(String nickname) {
		super(nickname);
	}

	public HumanPlayer() {
	}
	
	@Override
	public ArrayList<String> playCards(List<Card> formerCards) {
		Messenger.print("Please choose the cards to play. Input 'help' for example inputs.\n");
		ArrayList<String> inputCardNames = new ArrayList<String>();

		// Input Processing
		Scanner in=new Scanner(System.in);
		String cmd = Messenger.printAskForInput(in,"play",
				"[" + this.getRole() + "] " + this.getNickname() + " >> ");
		
		if (cmd.equals("HELP")||cmd.equals("PASS")) {
			inputCardNames.add(cmd);
			return inputCardNames;
		}
		
		Scanner cmdScanner = new Scanner(cmd);
		while (cmdScanner.hasNext()) // TODO: exception handle
			inputCardNames.add(cmdScanner.next());
		cmdScanner.close();

		return inputCardNames;
	}

}
