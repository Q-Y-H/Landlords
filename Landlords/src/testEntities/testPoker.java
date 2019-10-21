package testEntities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import entities.Poker;
import enums.Rank;

class TestPoker {

	@Test
	void Should_ReturnTure_When_RankIsHigher() {
		Poker poker1=new Poker(Rank.RANK_K, null);
		Poker poker2=new Poker(Rank.RANK_Q, null);
		boolean result=poker1.compareTo(poker2);
		assertEquals(result,true);
	}
	
	@Test
	void Should_ReturnFalso_When_RankIsLower() {
		Poker poker1=new Poker(Rank.RANK_3, null);
		Poker poker2=new Poker(Rank.RANK_10, null);
		boolean result=poker1.compareTo(poker2);
		assertEquals(result,false);
	}
}
