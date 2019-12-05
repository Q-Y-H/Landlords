package entities;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import enums.HandType;
import enums.Rank;
import helpers.Helper;

public class Hand {

	private HandType type;
	private Rank primal;
	private Hand[] kickers;
	private int chainLength;
	private List<Card> cards=new ArrayList<Card>();
	private int weight=0;

	public Hand(HandType type,Rank primal,Hand[] kickers,int chainLength,List<Card> cards) {
		this.setType(type);
		this.primal=primal;
		this.kickers=kickers; 
		this.chainLength=chainLength;
		this.setCards(cards);
	}
	
	public HandType getType() {
		return type;
	}

	public Rank getPrimal() {
		return primal;
	}

	public boolean isSmallerThan(Hand h) {
		if (h.type == HandType.ILLEGAL || type == HandType.ROCKET)
			return false;
		if (type == null || type == HandType.ILLEGAL || h.type == HandType.ROCKET)
			return true;
		if (type == h.type) {
			if (chainLength != h.chainLength)
				return false;
			if (kickers != null && h.kickers != null) {
				if (kickers[0].type != h.kickers[0].type)
					return false;
				else if (primal.ordinal() < h.primal.ordinal())
					return true;
			} else if (!(kickers == null && h.kickers == null))
				return false;

			if (primal.ordinal() < h.primal.ordinal())
				return true;
		}
		if (h.type == HandType.BOMB)
			return true;
		else
			return false;
	}

	public String toString() {
		if (type == HandType.ILLEGAL)
			return "Illegal " + "\n";
		String kickersInfo = "";
		kickersInfo = (kickers == null) ? "null " : kickers[0].getInfo();
		return "Handtype: "+type + " " + "Primal: "+primal.getName() + " Kickers: " + kickersInfo + "Chainlength: "+chainLength + " cards:"+this.cards+ " weight: "+this.getWeight()+"\n";
	}

	public String getInfo() {
		return type + " " + primal.getName() + " ";
	}

	public void setType(HandType type) {
		this.type = type;
	}

	private static int sumOfArr(int arr[], int start, int end) {
		int sum=0;
		for(int i=start; i<=end; i++) sum += arr[i];
		return sum;
	}
	
	//convert cards into Hand
	public static Hand cards2hand(List<Card> cards) {
		if(cards != null && !cards.isEmpty()) {
			Helper.sortCards(cards);
			
			int[] numOfRanks = new int[20];
			for(Card card: cards) numOfRanks[card.getRank().ordinal()+3]++;		//numOfRanks stores how many times a rank occurs
			int startOfRank=0, endOfRank=0, length = 0, endOfTrio = 0, endOfQuad = 0;	//length stores number of different ranks
			int[] start = new int[30];	//stores the first card's rank of different cards combination length i.e. SOLO(1),PAIR(2)
			int[] count = new int[30];	//stores how many times the card combination of different length occur
			
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
				for(int i=1; i<=4; i++) {
					if(count[i]==1 && sumOfArr(count,1,4)-count[i]==0) {
						if(i==4) return new Hand(HandType.BOMB, Rank.getRankByValue(start[4]), null, 1, cards); 
						return new Hand(HandType.getHandType(i), Rank.getRankByValue(start[i]), null, 1, cards);
					}
				}
			}
			
			//pair of jokers
			if(startOfRank==16 && endOfRank==17) return new Hand(HandType.ROCKET, Rank.RANK_BLACK_JOKER, null, 1, cards);
			
			//34567, 334455, 333444, 33334444
			if(endOfRank - startOfRank == length - 1 && endOfRank < 15) {
				int threshold[] = {5, 3, 2};
				for(int i=1; i<=3; i++) {
					if(count[i] >= threshold[i-1] && sumOfArr(count,1,4)-count[i]==0)
						return new Hand(HandType.getHandType(i), Rank.getRankByValue(start[i]), null, length, cards);
				}
			}
			//333+4, 333444+56, 333+44, 333444+5566, 3333+45, 33334444+5678, 3333+4455, 33334444+55667788
			for(int k=3; k<=4; k++) {
				int end[] = {endOfTrio, endOfQuad};
				if(count[k] !=0 && end[k-3] - start[k] == count[k]-1) {
					for(int i=1; i<=2; i++) {
						int cnt = (k==3)? count[k] : 2*count[k];
						if( cnt == count[i] && sumOfArr(count,1,4)-count[k]-count[i] == 0) {
							int n = count[i], temp = start[i];
							Hand[] kickers = new Hand[n];
							for(int j=0; j<n; j++, temp++) {
								while(numOfRanks[temp] != i) temp++; 
								kickers[j] = new Hand(HandType.getHandType(i), Rank.getRankByValue(temp), null, 1, cards);
							}
							return new Hand(HandType.getHandType(k), Rank.getRankByValue(start[k]), kickers, count[k],cards);
						}
					}		
				}
			}
		}
		return new Hand(HandType.ILLEGAL,null, null, 0, cards);//illegal
	}

	public List<Card> getCards() {
		return cards;
	}

	public void setCards(List<Card> cards) {
		this.cards.clear();
		this.cards.addAll(cards);
	}
	public static Comparator<Hand> handComparator = new Comparator<Hand>() {

		@Override
		public int compare(Hand h1, Hand h2) {
			return (h1.getWeight()-h2.getWeight());
			}
		};

	protected int getWeight() {
		List<Card> response = null;
		int[] numOfCards = new int[20];

		switch(type){
		case ROCKET:{
			return 20;
		}
		case BOMB:{
			return primal.ordinal()+8;
		}
		case SOLO:{
			if(this.getChainLength()!=1) {
				return primal.ordinal()-chainLength-7;
			}
			else {
				return primal.ordinal()-7;
			}
		}
		case PAIR:{
			if(this.getChainLength()!=1) {
				return primal.ordinal()-chainLength;
			}
			else {
				return primal.ordinal()-7;
			}
		}
		case TRIO:{
			if(this.getChainLength()!=1) {
				return primal.ordinal()/2;
			}
			else {
				return primal.ordinal()-8;
			}
		}
		case QUAD:{
			return primal.ordinal()/2;
		}
		default:
			break;
		}
		
		return weight;
	}

	public int getChainLength() {
		return this.chainLength;
	}
}
