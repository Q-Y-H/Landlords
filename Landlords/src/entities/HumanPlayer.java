package entities;

import enums.PlayerRole;
import enums.RoomType;
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
	public void askForNickname() {
		// TODO Auto-generated method stub
		String prompt = "[Player " + this.getId() + "] Please Set Your Nickname >> ";
		String nickname = Messenger.askForInput(prompt, new String[] {}, true);
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
		
	}

}
