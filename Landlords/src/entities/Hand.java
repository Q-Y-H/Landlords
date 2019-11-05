package entities;

import java.util.List;

import enums.HandType;
import enums.Rank;
import helpers.Helper;


public class Hand implements Comparable<Hand>{
	
	private HandType type;
	private Rank primal;
	private Hand[] kickers;
	private int chainLength;
	private List<Card> cards;
	
	private boolean sameCategoryWith(Hand h) {
		if(type!=h.type) 
			return false;
		else if(chainLength!=h.chainLength)
			return false;
		else if(kickers!=null && h.kickers!=null) 
			if(kickers[0].getType()!=h.kickers[0].getType())
				return false;
		else if(!(kickers==null && h.kickers==null))
			return false;
		return true; 
	}
	
	public int compareTo(Hand h) {
		// TODO Refractory: throw exceptions
		if(type==HandType.ROCKET || h.getType()==HandType.ILLEGAL || h.getType()==null)
			return 1;
		else if( h.getType()==HandType.ROCKET || type==HandType.ILLEGAL||h.type==HandType.BOMB && type!=HandType.BOMB)
			return -1;		
		else if(!sameCategoryWith(h))
			return 1;
		else if(primal.ordinal()<h.primal.ordinal())
			return -1;
		else if(primal.ordinal()==h.primal.ordinal())
			return 0;
		else 
			return 1;
	}
	
	public Rank getPrimal() {
		return primal;
	}
  
	public String toString() {
		if(type == HandType.ILLEGAL) return "Illegal "+"\n";
		String kickersInfo = "";
		kickersInfo = (kickers == null)?  "null " : kickers[0].getInfo();
		return type+" "+primal.getName()+" Kickers: "+ kickersInfo + chainLength+"\n";
	}
	
	public String getInfo() {return type +" "+primal.getName()+" ";}
	
	
	public Hand(HandType type,Rank primal,Hand[] kickers,int chainLength) {
		this.setType(type);
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
	
	public HandType getType() {
		return type;
	}

	public void setType(HandType type) {
		this.type = type;
	}
	
	
	// TODO Refractory: use for loop
	public static Hand cards2hand(List<Card> cards) {
		if(cards != null && !cards.isEmpty()) {
			Helper.sortCards(cards);
			
			int[] numOfRanks = new int[20];
			for(Card card: cards) numOfRanks[card.getRank().ordinal()+3]++;		//numOfRanks stores how many times a rank occurs
			int startOfRank=0, endOfRank=0, length = 0, endOfTrio = 0, endOfQuad = 0;	//length stores number of different ranks
			int[] start = new int[5];	//stores the first card's rank of different cards combination length i.e. SOLO(1),PAIR(2)
			int[] count = new int[5];	//stores how many times the card combination of different length occur
			
			for(int rank=0; rank<numOfRanks.length; rank++) {
				if(numOfRanks[rank] == 0) continue;
				length++;
				if(startOfRank == 0) startOfRank = rank;
				endOfRank = rank;
				if(start[numOfRanks[rank]] == 0) start[numOfRanks[rank]] = rank;
				switch(numOfRanks[rank]) {
					case 3: endOfTrio = rank;break;		
					case 4: endOfQuad = rank;break;
				}
				count[numOfRanks[rank]]++;
			}
			
			if(startOfRank == endOfRank) {//3, 33, 333, 3333
				if(count[1] == 1 && count[2]+count[3]+count[4] == 0) 
					return new Hand(HandType.SOLO, Rank.getRankByValue(start[1]));
				if(count[2] == 1 && count[1]+count[3]+count[4] == 0)
					return new Hand(HandType.PAIR, Rank.getRankByValue(start[2]));
				if(count[3] == 1 && count[1]+count[2]+count[4] == 0)
					return new Hand(HandType.TRIO, Rank.getRankByValue(start[3]));
				if(count[4] == 1 && count[1]+count[2]+count[3] == 0)
					return new Hand(HandType.BOMB, Rank.getRankByValue(start[4]));
			}
			
			//pair of jokers
			if(startOfRank==16 && endOfRank==17) return new Hand(HandType.ROCKET, Rank.RANK_BLACK_JOKER);
			
			//34567, 334455, 333444, 33334444
			if(endOfRank - startOfRank == length - 1 && endOfRank < 15) {
				if(count[1] >= 5 && count[2]+count[3]+count[4] == 0) 
					return new Hand(HandType.SOLO, Rank.getRankByValue(start[1]), length);
				if(count[2] >= 3 && count[1]+count[3]+count[4] == 0)
					return new Hand(HandType.PAIR, Rank.getRankByValue(start[2]), length);
				if(count[3] >= 2 && count[1]+count[2]+count[4] == 0)
					return new Hand(HandType.TRIO, Rank.getRankByValue(start[3]), length);
//				if(count[4] >= 2 && count[1]+count[2]+count[3] == 0)
//					return new Hand(HandType.BOMB, Rank.getRankByValue(start[4]), length);
			}
			
			if(count[3] != 0 && endOfTrio - start[3] == count[3]-1) {
				//333+4, 333444+56
				if(count[3] == count[1] && count[2]+count[4] == 0) {
					int n = count[1], temp = start[1];
					Hand[] kickers = new Hand[n];
					for(int i=0; i<n; i++, temp++) {
						while(numOfRanks[temp] != 1) temp++;
						kickers[i] = new Hand(HandType.SOLO, Rank.getRankByValue(temp));
					}
					return new Hand(HandType.TRIO, Rank.getRankByValue(start[3]), kickers, count[3]);
				}
				//333+44, 333444+5566
				if(count[3] == count[2] && count[1]+count[4] == 0) {
					int n = count[2], temp = start[2];
					Hand[] kickers = new Hand[n];
					for(int i=0; i<n; i++, temp++) {
						while(numOfRanks[temp] != 2) temp++;
						kickers[i] = new Hand(HandType.PAIR, Rank.getRankByValue(temp));
					}
					return new Hand(HandType.TRIO, Rank.getRankByValue(start[3]), kickers, count[3]);
				}					
			}
			
			if(count[4] != 0 && endOfQuad - start[4] == count[4]-1) {
				//3333+45, 33334444+5678
				if(2*count[4] == count[1] && count[2]+count[3] == 0) {
					int n = count[1], temp = start[1];
					Hand[] kickers = new Hand[n];
					for(int i=0; i<n; i++, temp++) {
						while(numOfRanks[temp] != 1) temp++;
						kickers[i] = new Hand(HandType.SOLO, Rank.getRankByValue(temp));
					}
					return new Hand(HandType.QUAD, Rank.getRankByValue(start[4]), kickers, count[4]);
				}
				//3333+4455, 33334444+55667788
				if(2*count[4] == count[2] && count[1]+count[3] == 0) {
					int n = count[2], temp = start[2];
					Hand[] kickers = new Hand[n];
					for(int i=0; i<n; i++, temp++) {
						while(numOfRanks[temp] != 2) temp++;
						kickers[i] = new Hand(HandType.PAIR, Rank.getRankByValue(temp));
					}
					return new Hand(HandType.QUAD, Rank.getRankByValue(start[4]), kickers, count[4]);
				}
			}
		}
 		
		return new Hand(HandType.ILLEGAL);//illegal
	}

	public boolean over(Hand lastHand) {
		// TODO Auto-generated method stub
		return false;
	} 

}
