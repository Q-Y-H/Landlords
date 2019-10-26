package entities;

import java.util.ArrayList;
import java.util.List;

import enums.HandType;

public class CardRoom {
	
	private List<Player> players;
	private List<Card> landlordCards;
	private Hand lastHand;
	private int landlordID;
	private Player lastHandPlayer;
	
	public CardRoom() { 
		this.players = new ArrayList<Player>();
		this.landlordCards = null;
		this.lastHand = new Hand(HandType.ILLEGAL);
		this.lastHandPlayer = null;
	}
	
	public List<Player> getPlayers() {
		return players;
	}
	public void setPlayers(List<Player> players) {
		this.players = players;
	}
	public List<Card> getLandlordCards() {
		return landlordCards;
	}
	public void setLandlordCards(List<Card> landlordCards) {
		this.landlordCards = landlordCards;
	}
	public Hand getLastHand() {
		return lastHand;
	}
	public void setLastHand(Hand lastHand) {
		this.lastHand = lastHand;
	}

	public int getLandlordID() {
		return landlordID;
	}

	public void setLandlordID(int landlordID) {
		this.landlordID = landlordID;
	}

	public Player getLastHandPlayer() {
		return lastHandPlayer;
	}

	public void setLastHandPlayer(Player lastHandPlayer) {
		this.lastHandPlayer = lastHandPlayer;
	}
}
