package entities;


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
	

	public boolean compareTo(Card b) {	
		//compare the rank between two poker
		//return true if this.rank>b.rank
		if (this.getRank() >b.getRank()) 
			return true;
		else 
			return false;
	}

	@Override
	public String toString() {
		return String.valueOf(rank.getName()) + " ";
	}
	
	
}
