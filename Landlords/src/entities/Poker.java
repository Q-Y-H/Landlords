package entities;

import enums.PokerRank;
import enums.PokerSuit;

public class Poker{
	
	private PokerRank rank;	//大小
	
	private PokerSuit suit;		//花色

	public Poker(PokerRank level, PokerSuit type) {
		this.rank = level;
		this.suit = type;
	}

	public final PokerRank getLevel() {
		return rank;
	}

	public final PokerSuit getType() {
		return suit;
	}
	
	public boolean greater(Poker b) {	//compare the level between two poker
		//return true if this.level>b.level
		if (this.rank.getLevel() >b.rank.getLevel()) 
			return true;
		else 
			return false;
	}

	@Override
	public String toString() {
		return String.valueOf(rank.getName()) + " ";
	}
	
}
