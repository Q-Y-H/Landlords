package entities;

import java.util.ArrayList;
import java.util.Scanner;

import enums.PlayerRole;
import helpers.Messenger;

public class HumanPlayer extends Player {

	public HumanPlayer(String nickname, PlayerRole role) {
		super(nickname, role);
	}

	public HumanPlayer(String nickname) {
		super(nickname);
	}

	public HumanPlayer() {
	}

	@Override
	public void askForNickname() {
		// TODO Auto-generated method stub
		String prompt = "[Player " + this.getId() + "] Please Set Your Nickname >> ";
		String nickname = Messenger.getInstance().askForInput(prompt, new String[] {}, true);
		this.setNickname(nickname);
	}

	@Override
	public Boolean decideRunForLandlord() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getPlayChoice() {
		// TODO Auto-generated method stub
		Messenger.print("Please choose the cards to play. Input 'help' for more information.\n");
		String cmd = "";

		while(true) {
			String prompt = "[" + this.getRole() + "] " + this.getNickname() + " >> ";
			cmd = Messenger.getInstance().askForInput(prompt, new String[] {}, false);
			
			if (cmd.toUpperCase().equals("HELP")) {
				// TODO
			}else if (cmd.toUpperCase().equals("SUGGEST")) {
				// TODO
			}else {
				break;
			}
		} 
		return cmd;
	}
}
