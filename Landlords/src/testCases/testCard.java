package testCases;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import entities.Card;
import enums.Rank;

@TestMethodOrder(MethodOrderer.Alphanumeric.class)
public class testCard {

	@Test // single smaller
	public void compareTo_1stCardRankGreaterthan2ndCardRank_ReturnTrue() {
		Card poker1 = new Card(Rank.RANK_K, null);
		Card poker2 = new Card(Rank.RANK_Q, null);
		int result = poker1.compareTo(poker2);
		assertEquals(result, 1);
	}

	@Test // single smaller
	public void compareTo_1stCardRankLowerThan2ndCardRank_ReturnFalse() {
		Card poker1 = new Card(Rank.RANK_3, null);
		Card poker2 = new Card(Rank.RANK_10, null);
		int result = poker1.compareTo(poker2);
		assertEquals(result, -7);
	}
	
	@Test // toString
	public void toString_singleCardJ() {
		Card poker =  new Card(Rank.RANK_J, null);
		assertEquals(poker.toString(), "J ");
	}
	
	@Test // toString
	public void toString_singleCardB() {
		Card poker =  new Card(Rank.RANK_BLACK_JOKER, null);
		assertEquals(poker.toString(), "B ");
	}
	
	@Test // toString
	public void toString_singleCardR() {
		Card poker =  new Card(Rank.RANK_RED_JOKER, null);
		assertEquals(poker.toString(), "R ");
	}
}
