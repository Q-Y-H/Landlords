package entities;

import java.util.Comparator;

import enums.Rank;
import enums.Suit;

public class Card{
	
	private Rank rank;
	private Suit suit;

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
		return this.getRank().ordinal() - card.getRank().ordinal();
	}
	
	public static Comparator<Card> cardComparator = new Comparator<Card>() {
		@Override
		public int compare(Card o1, Card o2) {
			int res = o1.compareTo(o2);
			if(res == 0) return o1.getSuit().ordinal()-o2.getSuit().ordinal();
			return res;
		}
	};
	
	@Override
	public String toString() {
		return String.valueOf(rank.getName()) + " ";
	}
	
}
