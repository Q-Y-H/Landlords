package entities;

import java.util.Comparator;

import enums.Rank;
import enums.Suit;

public class Card{
	
	private Rank rank;
	private Suit suit;
	
	public static Comparator<Card> cardComparator = new Comparator<Card>() {
		@Override
		public int compare(Card o1, Card o2) {
			return o1.compareTo(o2);
		}
	};

	public Card(Rank rank, Suit suit) {
		this.rank = rank;
		this.suit = suit;
	}

	public Rank getRank() {
		return rank;
	}

	public Suit getSuit() {
		return suit;
	}
	
	public int compareTo(Card card) {	
		return this.getRank().getValue() - card.getRank().getValue();
	}
	
	@Override
	public String toString() {
		return String.valueOf(rank.getName()) + " ";
	}
	
	
}
