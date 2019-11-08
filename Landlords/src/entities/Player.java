package entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.management.relation.Role;

import enums.PlayerRole;
import enums.Rank;


public abstract class Player {

	private static int idCounter = 0; 
	private int id;
	private String nickname;
	private PlayerRole role;
	protected List<Card> cards = new ArrayList<Card>();

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

	public List<Card> checkCardsOnHand(ArrayList<String> cardNames) { // TODO: cardNames should be sorted
		int i = 0, j = 0;
		List<Card> res = new ArrayList<Card>();
		Collections.sort(cardNames, new Comparator<String>() {
	        @Override
	        public int compare(String s1, String s2)
	        {
	            return Rank.getRankByName(s1).ordinal() - Rank.getRankByName(s2).ordinal();
	        }
	    });
		
		while(i < cardNames.size() && j < this.cards.size()) {
			if (cardNames.get(i).toUpperCase().equals(this.cards.get(j).getRank().getName())) { // TODO: adapt for a | A
				res.add(this.cards.get(j));
				++i; ++j;
			}
			else
				++j;
		}
		if (res.size() != cardNames.size())
			return null;
		return res;
	}
	
	public void removeCards(List<Card> handCards) {
		for(int i=0;i<handCards.size();i++)
			if(cards.contains(handCards.get(i)))
				cards.remove(handCards.get(i));
	}
	
	public void removeCards(Hand hand) {
		removeCards(hand.getCards());
	}
	
	public abstract List<Card> playCards(List<Card> cards);

	public List<Card> getCards() {
		return this.cards;
	}

}
