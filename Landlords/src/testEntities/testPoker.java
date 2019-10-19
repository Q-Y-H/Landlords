package testEntities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import entities.Poker;
import enums.PokerRank;

class TestPoker {

	@Test
	void Should_ReturnTure_When_RankIsHigher() {
		Poker poker1=new Poker(PokerRank.RANK_K, null);
		Poker poker2=new Poker(PokerRank.RANK_Q, null);
		boolean result=poker1.compareTo(poker2);
		assertEquals(result,true);
	}
	
	@Test
	void Should_ReturnFalso_When_RankIsLower() {
		Poker poker1=new Poker(PokerRank.RANK_3, null);
		Poker poker2=new Poker(PokerRank.RANK_10, null);
		boolean result=poker1.compareTo(poker2);
		assertEquals(result,false);
	}
}
