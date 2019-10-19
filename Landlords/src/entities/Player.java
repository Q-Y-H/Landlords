package entities;

import java.util.ArrayList;


public class Player {

	private static Hand lastHand;
	private ArrayList<Poker> pokerOnHand = new ArrayList<Poker>();
	
	public Player(ArrayList<Poker> pokerOnHand) {
		super();
		this.pokerOnHand = pokerOnHand;
	}
	
	public static Hand getLastHand() {
		return Player.lastHand;
	}
	
	public static void setLastHand(Hand Hand) {
		Player.lastHand = Hand;
	}
	
	public ArrayList<Poker> getPokerOnHand() {
		// return pokerOnHand;
		return new ArrayList<Poker>(this.pokerOnHand);
	}
	
	public void setPokerOnHand(ArrayList<Poker> pokerOnHand) {
		this.pokerOnHand = pokerOnHand;
	}
}
