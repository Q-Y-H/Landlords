package entities;

import java.util.ArrayList;
import java.util.List;

public class PokerRoom {
	
	private List<Player> players;
	private List<Card> landlordCards;
	private Hand lastHand;
	
	public PokerRoom() {
		this.players = new ArrayList<Player>();
		this.landlordCards = null;
		this.lastHand = null;
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
}
