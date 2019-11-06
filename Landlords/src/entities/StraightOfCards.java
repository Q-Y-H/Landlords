package entities;

import enums.HandType;
import enums.Rank;

public class StraightOfCards {
	private int chainLength;
	private HandType type;
	private Rank endRank;
	
	public StraightOfCards() {}
	
	public StraightOfCards(int length, HandType handType, Rank EndRank) {
		chainLength=length;
		type=handType;
		endRank=EndRank;
	}
}
