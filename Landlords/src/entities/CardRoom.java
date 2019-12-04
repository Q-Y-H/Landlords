package entities;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import enums.PlayerRole;
import enums.RoomType;
import helpers.Helper;

public class CardRoom {

	private List<Player> players;
	private List<Card> landlordCards;
	private Player lastHandPlayer;
	private int landlordID;
	private CardCase cardCase;
	private LinkedList<Hand> handHistory;
	private RoomType type;

	public CardRoom() {
		this.players = new ArrayList<Player>();
		this.landlordCards = null;
//		this.lastHand = new Hand(HandType.ILLEGAL,null,null,0,null);
		this.lastHandPlayer = null;
		this.cardCase = new CardCase();
		this.handHistory = new LinkedList<Hand>();
		this.type = null;
	}

	public void setup() {
		Helper.shuffleCards(this.cardCase);

		// Cut the base cards into 4 portions;
		List<List<Card>> cardLists = Helper.cutCards(this.cardCase);

		// Sort cards
		for (List<Card> cards : cardLists) {
			Helper.sortCards(cards);
		}
		
		// The last one portion for the landlord
		this.landlordCards = cardLists.get(3);
		
		if(this.type == RoomType.PVP) {
			for(int i = 0; i<3; ++i) 
				this.players.add(new HumanPlayer("undefined", PlayerRole.PEASANT,handHistory));
		} else if (this.type == RoomType.PVE) {
			this.players.add(new HumanPlayer("undefined", PlayerRole.PEASANT,handHistory));
			this.players.add(new RobotPlayer("undefined", PlayerRole.PEASANT,handHistory));
			this.players.add(new RobotPlayer("undefined", PlayerRole.PEASANT,handHistory));
		} else {
			// TODO: implement in exception case such as null
		}
		
		for(Player player:this.players) {
			player.setCards(cardLists.get(player.getId()%3));
		}
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

	public Player getNextPlayer(Player player) {
		return this.players.get((player.getId() + 1) % 3);
	}

	public Player getPrePlayer(Player player) {
		return this.players.get((player.getId() + 2) % 3);
	}

	public RoomType getType() {
		return type;
	}

	public void setType(RoomType type) {
		this.type = type;
	}

	public LinkedList<Hand> getHandHistory() {
		return handHistory;
	}

	public void setHandHistory(LinkedList<Hand> handHistory) {
		this.handHistory = handHistory;
	}

}
