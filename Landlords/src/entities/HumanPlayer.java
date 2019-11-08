package entities;

import java.util.List;

import enums.PlayerRole;

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
	public List<Card> playCards(List<Card> cards) {
		// TODO Auto-generated method stub
		return null;
	}

}
