package entities;

import java.util.ArrayList;

import enums.PokerHand;

public class Player {

	private static PokerHand lastHand;
	private ArrayList<Poker> pokerOnHand = new ArrayList<Poker>();
	
	public Player(ArrayList<Poker> pokerOnHand) {
		super();
		this.pokerOnHand = pokerOnHand;
	}
	
	public static PokerHand getLastHand() {
		return Player.lastHand;
	}
	
	public static void setLastHand(PokerHand pokerHand) {
		Player.lastHand = pokerHand;
	}
	
	public ArrayList<Poker> getPokerOnHand() {
		// return pokerOnHand;
		return new ArrayList<Poker>(this.pokerOnHand);
	}
	
	public void setPokerOnHand(ArrayList<Poker> pokerOnHand) {
		this.pokerOnHand = pokerOnHand;
	}
}
