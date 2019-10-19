package entities;

import enums.HandType;
import enums.Rank;


public class Hand {
	
	HandType type;
	Rank primal;
	Hand[] kickers;
	int chainLength;
	
	public Hand(HandType type,Rank primal,Hand[] kickers,int chainLength) {
		this.type=type;
		this.primal=primal;
		this.kickers=kickers;
		this.chainLength=chainLength;
	}
	
	public Hand(HandType type,Rank primal,int chainLength) {
		this(type,primal,null,chainLength);
	}
	
	public Hand(HandType type,Rank primal,Hand[] kickers) {
		this(type,primal,kickers,1);
	}
	
	public Hand(HandType type,Rank primal) {
		this(type,primal,null,1);
	}
	public Hand(HandType type) {
		this(type,null,null,0);
	}
}
