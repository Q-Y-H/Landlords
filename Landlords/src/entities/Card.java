package entities;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import enums.Rank;
import enums.Suit;

public class Card{
	
	private Rank rank;
	
	private Suit suit;


	public Card(Rank rank, Suit suit) {
		this.rank = rank;
		this.suit = suit;
	}

	public final Rank getLevel() {
		return rank;
	}

	public final int getRank() {
		return rank.getRank();
	}
	
	public final Suit getType() {
		return suit;
	}
	

	public int compareTo(Card card) {	
		return this.getRank() - card.getRank();
	}

	private static Comparator<Card> cardComparator = new Comparator<Card>() {
		@Override
		public int compare(Card o1, Card o2) {
			return o1.compareTo(o2);
		}
	};
	
	public static void sortPoker(List<Card> cards){
		Collections.sort(cards, cardComparator);
	}
	
	@Override
	public String toString() {
		return String.valueOf(rank.getName()) + " ";
	}
	
	
}
