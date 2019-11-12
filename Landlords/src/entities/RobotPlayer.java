package entities;

import enums.PlayerRole;

public class RobotPlayer extends Player{
	
	public RobotPlayer(String nickname, PlayerRole role) {
		super(nickname, role);
	}

	public RobotPlayer(String nickname) {
		super(nickname);
	}

	public RobotPlayer() {
	}

	@Override
	public void askForNickname() {
		// TODO Auto-generated method stub
		this.setNickname("Robot " + getId());
	}

	@Override
	public Boolean decideRunForLandlord() {
		return null;
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getPlayChoice() {
		// TODO Auto-generated method stub
		return null;
	}

}
