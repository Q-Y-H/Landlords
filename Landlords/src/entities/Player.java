package entities;

import java.util.ArrayList;
import java.util.List;

import javax.management.relation.Role;

import enums.PlayerRole;


public class Player {

	private static int idCounter = 0; 
	private int id;
	private String nickname;
	private PlayerRole role;
	private List<Card> cards = new ArrayList<Card>();

	public Player(String nickname, PlayerRole role) {
		this.setId(idCounter);
		this.nickname = nickname;
		this.role = role;
		this.cards = null;
		
		++Player.idCounter;
	}
	
	public Player(String nickname) {
		this(nickname, null);
	}
	
	public Player() {
		this(null, null);
	}

	public PlayerRole getRole() {
		return role;
	}

	public void setRole(PlayerRole role) {
		this.role = role;
	}

	public List<Card> getCards() {
		return cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Card> checkCardsOnHand(ArrayList<String> cardNames) {
		// TODO Auto-generated method stub
		
		//if not contain
		ArrayList<String> names = new ArrayList<String>();
		for(Card c: cards) 
			names.add(c.getRank().getName());
		for(String name: cardNames)
			if(!names.contains(name)) return null; 
		
		//if contain all cardNames(input)
		List<Card> output = new ArrayList<Card>();
		for(String name: cardNames) {
			for(int i=0;i<cards.size();i++) {
				if((cards.get(i).getRank().getName()).equals(name)) {
					output.add(cards.get(i));
					continue;
				}
			}
		}
		return output;
	}
	
	public void removeCards(List<Card> handCards) {
		for(int i=0;i<handCards.size();i++)
			if(cards.contains(handCards.get(i)))
				cards.remove(handCards.get(i));
	}

	public void playCards(List<Card> cards) {
		// TODO Auto-generated method stub
		
	}

}
