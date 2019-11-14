package entities;

import java.util.List;

import enums.HandType;
import enums.Rank;

public class StraightOfCards extends Hand{
	private int chainLength;
	private HandType type;
	private Rank endRank;
		
	public StraightOfCards(HandType handType, Rank EndRank, int length, List<Card> cards) {
		super(handType,Rank.getRankByValue(EndRank.ordinal()-length+3),null,length,cards);
		chainLength=length;
		type=handType;
		endRank=EndRank;
	}
	public int getChainLength() {
		return chainLength;
	}
	public int getEnd() {
		return endRank.ordinal()+3;
	}
	public List<Card>getCards(){
		return super.getCards();
	}
}
