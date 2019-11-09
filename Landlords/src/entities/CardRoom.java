package entities;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import enums.HandType;
import enums.PlayerRole;
import helpers.Helper;
import helpers.Messenger;

public abstract class CardRoom {
	
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
		this.lastHand = new Hand(HandType.ILLEGAL,null);
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
		return this.players.get((player.getId()+1)%3);
	}
	
	public Player getPrePlayer(Player player) {
		return this.players.get((player.getId()+2)%3);
	}
	
	public abstract void initialize();
	
	public void selectLandlord() {
		
		/*
		 * Landlord election
		 */
		Random rand = new Random();
		int cursor = rand.nextInt(3);
		int landlordID = 0;
		List<Boolean> choices = new ArrayList<Boolean>();
		int nWaive = 0;

		for (int i = 0; i < 4; ++i) {
			if(i == 3) 
				if (nWaive == 3) { // all waive
					landlordID = rand.nextInt(3);
					break;	
				} 
				else if(nWaive == 2) // two players waive
					break;
				else if (nWaive == 1) // one player waives
					do
						cursor = (cursor+1)%3;
					while(choices.get(cursor));
				else if (nWaive == 0)  // all run for landlord
					cursor = (cursor+1)%3;
			
			String cmd="";
			Player player = players.get(cursor);
			if(player instanceof HumanPlayer) {
			Messenger.handleRunForLandlord(players, cursor, choices);
			Scanner in = new Scanner(System.in);
			cmd = Messenger.printAskForInput(in, "landlord", "Player " + player.getNickname() + 
					": Do you want to run for landlord? [y/n] ");
			}
			else {
				cmd=((RobotPlayer) player).runForLandlord();
			}
			if (cmd.equals("Y")) { // TODO: check invalid input like 'ilsdhcvi'
				choices.add(true);
				landlordID = cursor;
			} 
			else {
				choices.add(false);
				nWaive++;
			}
			
			
			cursor = (cursor + 1) % 3;
		}
		
		/* ******************** Default landlord, modify it later */
		this.setLandlordID(landlordID);
		players.get(landlordID).setRole(PlayerRole.LANDLORD);
		players.get(landlordID).getCards().addAll(this.getLandlordCards());
		Helper.sortCards(players.get(landlordID).getCards());
		Messenger.print("The landlord is Player " + players.get(landlordID).getNickname());
		Messenger.print("Landlord cards:");
		Messenger.print(Messenger.printCards(this.getLandlordCards()));
		/* ******************** */
	}

}
