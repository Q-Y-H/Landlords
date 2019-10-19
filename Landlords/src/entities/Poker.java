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

	public final PokerSuit getType() {
		return suit;
	}
	
	public boolean greater(Poker b) {	//compare the level between two poker
		//return true if this.level>b.level
		if (this.rank.getRank() >b.getLevel().getRank()) 
			return true;
		else 
			return false;
	}

	@Override
	public String toString() {
		return String.valueOf(rank.getName()) + " ";
	}
	
}
