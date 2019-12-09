package entities;

import java.util.LinkedList;

import enums.PlayerRole;

public class HumanPlayer extends Player {

	public HumanPlayer(String nickname, PlayerRole role, LinkedList<Hand> recentHands) {
		super(nickname, role, recentHands);
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
		String nickname = this.messenger.askForInput(prompt, new String[] {}, true);
		this.setNickname(nickname);
	}

	@Override
	public Boolean decideRunForLandlord() {
		String prompt = "Player " + this.getNickname() + ": Do you want to claim for landlord? [y/n] ";
		String input = this.messenger.askForInput(prompt, new String[] { "Y", "N" }, false);
		if (input.toUpperCase().equals("Y"))
			return true;
		else
			return false;
	}

	@Override
	public String getPlayChoice() {
		this.messenger.print("Please choose the cards to play. Input 'help' for more information. Input 'pass' to skip this round.\n");
		String cmd = "";

		while (true) {
			String prompt = "[" + this.getRole() + "] " + this.getNickname() + " >> ";
			cmd = this.messenger.askForInput(prompt, new String[] {}, false);

			if (cmd.toUpperCase().equals("HELP")) {
				this.messenger.println(this.messenger.inputHelpInfo());
			} else if (cmd.toUpperCase().equals("SUGGEST")) {
				this.messenger.println(this.messenger.inputSuggest(this, recentHands.peekLast()));
			} else {
				break;
			}
		}
		return cmd;
	}
}
