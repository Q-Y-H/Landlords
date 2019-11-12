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
	public void decideRunForLandlord() {
		// TODO Auto-generated method stub
		
	}

}
