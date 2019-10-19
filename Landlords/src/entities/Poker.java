package entities;

import enums.PokerRank;
import enums.PokerSuit;

public class Poker{
	
	private PokerRank rank;	//大小
	
	private PokerSuit suit;		//花色

	public Poker(PokerRank	rank, PokerSuit suit) {
		this.rank = rank;
		this.suit = suit;
	}

	public final int getRank() {
		return rank.getRank();
	}
	
	public final PokerSuit getSuit() {
		return suit;
	}

	public boolean compareTo(Poker b) {	
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
