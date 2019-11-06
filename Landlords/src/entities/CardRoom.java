package entities;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import enums.HandType;

public class CardRoom {

	private List<Player> players;
	private List<Card> landlordCards;
	private Hand lastHand;
	private Player lastHandPlayer;
	private int landlordID;
	private CardCase cardCase;
	private LinkedList<List<Card>> previousCardsList;

	public CardRoom() {
		this.players = new ArrayList<Player>();
		this.landlordCards = null;
		this.lastHand = new Hand(HandType.ILLEGAL);
		this.lastHandPlayer = null;
		this.cardCase = new CardCase();
		this.setPreviousCardsList(new LinkedList<List<Card>>());
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

	public CardCase getCardCase() {
		return this.cardCase;
	}

	public LinkedList<List<Card>> getPreviousCardsList() {
		return previousCardsList;
	}

	public void setPreviousCardsList(LinkedList<List<Card>> previousCardsList) {
		this.previousCardsList = previousCardsList;
	}

	public Player getNextPlayer(Player player) {
		return this.players.get((player.getId() + 1) % 3);
	}

	public Player getPrePlayer(Player player) {
		return this.players.get((player.getId() + 2) % 3);
	}

//	public List<Card> getBaseCards() {
//		return this.cardCase.getBaseCards();
//	}
}
