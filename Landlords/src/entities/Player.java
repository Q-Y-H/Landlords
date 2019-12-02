package entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import enums.PlayerRole;
import enums.Rank;

public abstract class Player {

	private static int idCounter = 0;
	private int id;
	private String nickname;
	private PlayerRole role;
	protected List<Card> cards = new ArrayList<Card>();
	protected LinkedList<Hand> handHistroy=new LinkedList<Hand>();
	
	public Player(String nickname, PlayerRole role,LinkedList<Hand> handHistory) {
		this.setId(idCounter);
		this.nickname = nickname;
		this.role = role;
		this.cards = null;
		this.handHistroy=handHistory;
		++Player.idCounter;
	}

	public Player(String nickname) {
		this(nickname, null,null);
	}

	public Player() {
		this(null, null,null);
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
		List<Card> res = new ArrayList<Card>();
		Collections.sort(cardNames, new Comparator<String>() {
			@Override
			public int compare(String s1, String s2) {
				return Rank.getRankByName(s1).ordinal() - Rank.getRankByName(s2).ordinal();
			}
		});

		int i = 0, j = 0;
		while (i < cardNames.size() && j < this.cards.size()) {
			if (this.cards.get(j).getRank().hasAlias(cardNames.get(i))) {
				res.add(this.cards.get(j));
				++i;
			}
			++j;
		}
		if (res.size() != cardNames.size())
			return null;
		return res;
	}

	public void removeCards(List<Card> handCards) {
		cards.removeAll(handCards);
	}
	
	public void removeCards(Hand hand) {		
		this.removeCards(hand.getCards());
	}
	
	public void playCards(List<Card> cards) {
		// TODO Auto-generated method stub

	}

	public abstract void askForNickname();

	public abstract Boolean decideRunForLandlord();

	public abstract String getPlayChoice();

}
