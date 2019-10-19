package entities;

import enums.Rank;
import enums.PokerSuit;

public class Poker{
	
	private Rank rank;	//大小
	
	private PokerSuit suit;		//花色


	public Poker(Rank level, PokerSuit type) {
		this.rank = level;
		this.suit = type;
	}

	public final Rank getLevel() {
		return rank;
	}

	public final int getRank() {
		return rank.getRank();
	}
	
	public final PokerSuit getType() {
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
