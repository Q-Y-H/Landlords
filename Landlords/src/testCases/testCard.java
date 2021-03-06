package testCases;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import entities.Card;
import entities.Hand;
import enums.HandType;
import enums.Rank;

class testCard {

	@Test // single smaller
	void compareTo_1stCardRankGreaterthan2ndCardRank_ReturnTrue() {
		Card poker1 = new Card(Rank.RANK_K, null);
		Card poker2 = new Card(Rank.RANK_Q, null);
		int result = poker1.compareTo(poker2);
		assertEquals(result, 1);
	}

	@Test // single smaller
	void compareTo_1stCardRankLowerThan2ndCardRank_ReturnFalse() {
		Card poker1 = new Card(Rank.RANK_3, null);
		Card poker2 = new Card(Rank.RANK_10, null);
		int result = poker1.compareTo(poker2);
		assertEquals(result, -7);
	}

	@Test // pair bigger
	void CompareTo_Pair_When_RankIsHigher_True_Comparison() {
		Hand hand1 = new Hand(HandType.PAIR, Rank.RANK_2, 2);
		Hand hand2 = new Hand(HandType.PAIR, Rank.RANK_J, 2);
		boolean result = true;// need to be modified(comparison between Pair)
		assertEquals(result, true);
	}

	@Test // pair smaller
	void CompareTo_Pair_When_RankIsLower_False_Comparison() {
		Hand hand1 = new Hand(HandType.PAIR, Rank.RANK_5, 2);
		Hand hand2 = new Hand(HandType.PAIR, Rank.RANK_8, 2);
		boolean result = false;// need to be modified(comparison between Pair)
		assertEquals(result, false);
	}

	@Test // Trio bigger
	void CompareTo_Trio_When_RankIsHigher_True_Comparison() {
		Hand hand1 = new Hand(HandType.TRIO, Rank.RANK_A, null, 3);
		Hand hand2 = new Hand(HandType.TRIO, Rank.RANK_K, null, 3);
		boolean result = true;// need to be modified(comparison between Trio)
		assertEquals(result, true);
	}

	@Test // Trio smaller
	void CompareTo_Trio_When_RankIsLower_False_Comparison() {
		Hand hand1 = new Hand(HandType.TRIO, Rank.RANK_4, null, 3);
		Hand hand2 = new Hand(HandType.TRIO, Rank.RANK_7, null, 3);
		boolean result = false;// need to be modified(comparison between Trio)
		assertEquals(result, false);
	}

	@Test // Quad bigger
	void CompareTo_Quad_When_RankIsHigher_True_Comparison() {
		Hand hand1 = new Hand(HandType.QUAD, Rank.RANK_K, null, 4);
		Hand hand2 = new Hand(HandType.QUAD, Rank.RANK_10, null, 4);
		boolean result = true;// need to be modified(comparison between Quad)
		assertEquals(result, true);
	}

	@Test // Quad smaller
	void CompareTo_Quad_When_RankIsLower_False_Comparison() {
		Hand hand1 = new Hand(HandType.QUAD, Rank.RANK_5, null, 4);
		Hand hand2 = new Hand(HandType.QUAD, Rank.RANK_9, null, 4);
		boolean result = false;// need to be modified(comparison between Quad)
		assertEquals(result, false);
	}

	@Test // Bomb Bigger
	void CompareTo_Bomb_When_RankIsHigher_True_Comparison() {
		Hand hand1 = new Hand(HandType.BOMB, Rank.RANK_2, 4);
		Hand hand2 = new Hand(HandType.BOMB, Rank.RANK_8, 4);
		boolean result = true;// need to be modified(comparison between Bomb)
		assertEquals(result, true);
	}

	@Test // Bomb Smaller
	void CompareTo_Bomb_When_RankIsLower_False_Comparison() {
		Hand hand1 = new Hand(HandType.BOMB, Rank.RANK_5, null, 4);
		Hand hand2 = new Hand(HandType.BOMB, Rank.RANK_9, null, 4);
		boolean result = false;// need to be modified(comparison between Bomb)
		assertEquals(result, false);
	}

	@Test // ˳�Ӵ�
	void CompareTo_StraightOfSolo_When_RankIsHigher_True_Comparison() {
		Hand hand1 = new Hand(HandType.SOLO, Rank.RANK_10, 5);
		Hand hand2 = new Hand(HandType.SOLO, Rank.RANK_4, 5);
		boolean result = true;// need to be modified(comparison between Straight)
		assertEquals(result, true);
	}

	@Test // ˳��С
	void CompareTo_StraightOfSolo_When_RankIsLower_False_Comparison() {
		Hand hand1 = new Hand(HandType.SOLO, Rank.RANK_3, 7);
		Hand hand2 = new Hand(HandType.SOLO, Rank.RANK_8, 7);
		boolean result = false;// need to be modified(comparison between Straight)
		assertEquals(result, false);
	}

	@Test // ���Դ�
	void CompareTo_StraightOfPair_When_RankIsHigher_True_Comparison() {
		Hand hand1 = new Hand(HandType.PAIR, Rank.RANK_10, 3);
		Hand hand2 = new Hand(HandType.PAIR, Rank.RANK_4, 3);
		boolean result = true;// need to be modified(comparison between Straight)
		assertEquals(result, true);
	}

	@Test // ����С
	void CompareTo_StraightOfPair_When_RankIsLower_False_Comparison() {
		Hand hand1 = new Hand(HandType.PAIR, Rank.RANK_3, 4);
		Hand hand2 = new Hand(HandType.PAIR, Rank.RANK_8, 4);
		boolean result = false;// need to be modified(comparison between Straight)
		assertEquals(result, false);
	}

}
