package entities;

import java.util.LinkedList;

import enums.PlayerRole;
import helpers.Messenger;

public class HumanPlayer extends Player {

	public HumanPlayer(String nickname, PlayerRole role,LinkedList<Hand> recentHands) {
		super(nickname, role,recentHands);
	}

	public HumanPlayer(String nickname) {
		super(nickname);
	}

	public HumanPlayer() {}

	@Override
	public void askForNickname() {
		// TODO Auto-generated method stub
		String prompt = "[Player " + this.getId() + "] Please Set Your Nickname >> ";
		String nickname = Messenger.getInstance().askForInput(prompt, new String[] {}, true);
		this.setNickname(nickname);
	}

	@Override
	public Boolean decideRunForLandlord() {
		// TODO
		// Messenger.getInstance().handleRunForLandlord(players, cursor, choices, first);
		String prompt = "Player " + this.getNickname() + ": Do you want to run for landlord? [y/n] ";
		String input = Messenger.getInstance().askForInput(prompt, new String[] { "Y", "N" }, false);
		if (input.toUpperCase().equals("Y"))
			return true;
		else 
			return false;
	}

	@Override
	public String getPlayChoice() {
		// TODO Auto-generated method stub
		Messenger.getInstance().print("Please choose the cards to play. Input 'help' for more information.\n");
		String cmd = "";

		while(true) {
			String prompt = "[" + this.getRole() + "] " + this.getNickname() + " >> ";
			cmd = Messenger.getInstance().askForInput(prompt, new String[] {}, false);
			
			if (cmd.toUpperCase().equals("HELP")) {
				cmd=Messenger.getInstance().inputHelp(recentHands.getLast());
			}else if (cmd.toUpperCase().equals("SUGGEST")) {
				cmd=Messenger.getInstance().inputSuggest(this,recentHands.getLast());
			}else {
				break;
			}
		} 
		return cmd;
	}
}
