package helpers;

import java.util.ArrayList;

import entities.Hand;
import entities.Poker;
import enums.HandType;
import enums.Rank;

public class Checker {
	public static Hand checkHandType(ArrayList<Poker> hand) {
		if(hand != null && !hand.isEmpty()) {
			Sort.sortPoker(hand);
			
			int[] numOfRanks = new int[20];
			for(Poker card: hand) numOfRanks[card.getLevel().getRank()]++;
			int startOfRank=0, endOfRank=0, length = 0, endOfTrio = 0, endOfQuad = 0;
			int[] start = new int[5];
			int[] count = new int[5];
			
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
			if(startOfRank==16 && endOfRank==17) return new Hand(HandType.ROCKET);
			
			//34567, 334455, 333444, 33334444
			if(endOfRank - startOfRank == length - 1 && endOfRank < 15) {
				if(count[1] >= 5 && count[2]+count[3]+count[4] == 0) 
					return new Hand(HandType.SOLO, Rank.getRankByValue(start[1]), length);
				if(count[2] >= 3 && count[1]+count[3]+count[4] == 0)
					return new Hand(HandType.PAIR, Rank.getRankByValue(start[2]), length);
				if(count[3] >= 2 && count[1]+count[2]+count[4] == 0)
					return new Hand(HandType.TRIO, Rank.getRankByValue(start[3]), length);
				if(count[4] >= 2 && count[1]+count[2]+count[3] == 0)
					return new Hand(HandType.BOMB, Rank.getRankByValue(start[4]), length);
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
				//3333+44, 33334444+5566
				if(count[4] == count[2] && count[1]+count[3] == 0) {
					int n = count[2], temp = start[2];
					Hand[] kickers = new Hand[n];
					for(int i=0; i<n; i++, temp++) {
						while(numOfRanks[temp] != 2) temp++;
						kickers[i] = new Hand(HandType.PAIR, Rank.getRankByValue(temp));
					}
					return new Hand(HandType.QUAD, Rank.getRankByValue(start[3]), kickers, count[4]);
				}
			}
		}
 		
		return new Hand(HandType.ILLEGAL);//illegal
	}
}
