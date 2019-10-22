package entities;

import java.util.ArrayList;
import java.util.List;

import enums.PlayerRole;


public class Player {

	private static Hand lastHand;
	private List<Card> pokers = new ArrayList<Card>();
	private PlayerRole role;
	

	public Player(ArrayList<Card> pokers) {
		super();
		this.pokers = pokers;
	}
	
	public static Hand getLastHand() {
		return Player.lastHand;
	}
	
	public static void setLastHand(Hand Hand) {
		Player.lastHand = Hand;
	}
	
	public ArrayList<Card> getPokerOnHand() {
		// return pokerOnHand;
		return new ArrayList<Card>(this.pokers);
	}
	
	public void setPokerOnHand(ArrayList<Card> pokerOnHand) {
		this.pokers = pokerOnHand;
	}
}
