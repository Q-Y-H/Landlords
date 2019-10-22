package entities;

import java.util.ArrayList;
import java.util.List;

import enums.Rank;
import enums.Suit;

public class CardCase {
	
	private List<Card> baseCards;
	
	public CardCase() {
		this.baseCards = new ArrayList<Card>(54);
		
		Rank[] ranks = Rank.values();
		Suit[] suits = Suit.values();

		for(Rank rank: ranks) {
			if(rank == Rank.RANK_BLACK_JOKER) {
				this.baseCards.add(new Card(rank, Suit.BLANK));
				continue;
			}
			if(rank == Rank.RANK_RED_JOKER) {
				this.baseCards.add(new Card(rank, Suit.BLANK));
				continue;
			}
			for(Suit suit: suits) {
				if(suit == Suit.BLANK) {
					continue;
				}
				this.baseCards.add(new Card(rank, suit));
			}
		}
	}

	public List<Card> getBaseCards() {
		return baseCards;
	}

	public void setBaseCards(List<Card> baseCards) {
		this.baseCards = baseCards;
	}
	
	
}
