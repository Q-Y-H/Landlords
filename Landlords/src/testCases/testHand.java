package testCases;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.Test;
import entities.*;
import enums.*;

class testHand { 
	/*
	 * test method card2Hand(List<Card>)
	 *
	 */
	@Test //5
	void test01() {
		Card c1 = new Card(Rank.RANK_5, Suit.BLANK);
		List<Card> cards = Arrays.asList(c1);
		Hand h = Hand.cards2hand(cards);
		System.out.println(h.toString());
	}
	@Test //55
	void test02() {
		Card c1 = new Card(Rank.RANK_5, Suit.BLANK);
		Card c2 = new Card(Rank.RANK_5, Suit.BLANK);
		List<Card> cards = Arrays.asList(c1,c2);
		Hand h = Hand.cards2hand(cards);
		System.out.println(h.toString());
	}
	@Test //555
	void test03() {
		Card c1 = new Card(Rank.RANK_5, Suit.BLANK);
		Card c2 = new Card(Rank.RANK_5, Suit.BLANK);
		Card c3 = new Card(Rank.RANK_5, Suit.BLANK);
		List<Card> cards = Arrays.asList(c1,c2,c3);
		Hand h = Hand.cards2hand(cards);
		System.out.println(h.toString());
	}
	@Test //5555
	void test04() {
		Card c1 = new Card(Rank.RANK_5, Suit.BLANK);
		Card c2 = new Card(Rank.RANK_5, Suit.BLANK);
		Card c3 = new Card(Rank.RANK_5, Suit.BLANK);
		Card c4 = new Card(Rank.RANK_5, Suit.BLANK);
		List<Card> cards = Arrays.asList(c1,c2,c3,c4);
		Hand h = Hand.cards2hand(cards);
		System.out.println(h.toString());
	}
	
	@Test //34567
	void test05() {
		Card c1 = new Card(Rank.RANK_3, Suit.BLANK);
		Card c2 = new Card(Rank.RANK_4, Suit.BLANK);
		Card c3 = new Card(Rank.RANK_5, Suit.BLANK);
		Card c4 = new Card(Rank.RANK_6, Suit.BLANK);
		Card c5 = new Card(Rank.RANK_7, Suit.BLANK);
		List<Card> cards = Arrays.asList(c1,c2,c3,c4,c5);
		Hand h = Hand.cards2hand(cards);
		System.out.println(h.toString());
	}
	
	@Test //445566
	void test06() {
		Card c1 = new Card(Rank.RANK_4, Suit.BLANK);
		Card c2 = new Card(Rank.RANK_4, Suit.BLANK);
		Card c3 = new Card(Rank.RANK_5, Suit.BLANK);
		Card c4 = new Card(Rank.RANK_5, Suit.BLANK);
		Card c5 = new Card(Rank.RANK_6, Suit.BLANK);
		Card c6 = new Card(Rank.RANK_6, Suit.BLANK);
		List<Card> cards = Arrays.asList(c1,c2,c3,c4,c5,c6);
		Hand h = Hand.cards2hand(cards);
		System.out.println(h.toString());
	}
	
	@Test //555666
	void test07() {
		Card c1 = new Card(Rank.RANK_5, Suit.BLANK);
		Card c2 = new Card(Rank.RANK_5, Suit.BLANK);
		Card c3 = new Card(Rank.RANK_5, Suit.BLANK);
		Card c4 = new Card(Rank.RANK_6, Suit.BLANK);
		Card c5 = new Card(Rank.RANK_6, Suit.BLANK);
		Card c6 = new Card(Rank.RANK_6, Suit.BLANK);
		List<Card> cards = Arrays.asList(c1,c2,c3,c4,c5,c6);
		Hand h = Hand.cards2hand(cards);
		System.out.println(h.toString());
	}
	
	
	@Test //5553
	void test08() {
		Card c1 = new Card(Rank.RANK_5, Suit.BLANK);
		Card c2 = new Card(Rank.RANK_5, Suit.BLANK);
		Card c3 = new Card(Rank.RANK_5, Suit.BLANK);
		Card c4 = new Card(Rank.RANK_3, Suit.BLANK);
		List<Card> cards = Arrays.asList(c1,c2,c3,c4);
		Hand h = Hand.cards2hand(cards);
		System.out.println(h.toString());
	}
	
	@Test //555666+34
	void test09() {
		Card c1 = new Card(Rank.RANK_5, Suit.BLANK);
		Card c2 = new Card(Rank.RANK_5, Suit.BLANK);
		Card c3 = new Card(Rank.RANK_5, Suit.BLANK);
		Card c4 = new Card(Rank.RANK_3, Suit.BLANK);
		Card c5 = new Card(Rank.RANK_6, Suit.BLANK);
		Card c6 = new Card(Rank.RANK_6, Suit.BLANK);
		Card c7 = new Card(Rank.RANK_6, Suit.BLANK);
		Card c8 = new Card(Rank.RANK_4, Suit.BLANK);
		List<Card> cards = Arrays.asList(c1,c2,c3,c4,c5,c6,c7,c8);
		Hand h = Hand.cards2hand(cards);
		System.out.println(h.toString());
	}
	
	@Test //55533
	void test10() {
		Card c1 = new Card(Rank.RANK_5, Suit.BLANK);
		Card c2 = new Card(Rank.RANK_5, Suit.BLANK);
		Card c3 = new Card(Rank.RANK_5, Suit.BLANK);
		Card c4 = new Card(Rank.RANK_3, Suit.BLANK);
		Card c5 = new Card(Rank.RANK_3, Suit.BLANK);
		List<Card> cards = Arrays.asList(c1,c2,c3,c4,c5);
		Hand h = Hand.cards2hand(cards);
		System.out.println(h.toString());
	}
	
	@Test //555666+3344
	void test11() {
		Card c1 = new Card(Rank.RANK_5, Suit.BLANK);
		Card c2 = new Card(Rank.RANK_5, Suit.BLANK);
		Card c3 = new Card(Rank.RANK_5, Suit.BLANK);
		Card c4 = new Card(Rank.RANK_3, Suit.BLANK);
		Card c5 = new Card(Rank.RANK_6, Suit.BLANK);
		Card c6 = new Card(Rank.RANK_6, Suit.BLANK);
		Card c7 = new Card(Rank.RANK_6, Suit.BLANK);
		Card c8 = new Card(Rank.RANK_4, Suit.BLANK);
		Card c9 = new Card(Rank.RANK_3, Suit.BLANK);
		Card c10 = new Card(Rank.RANK_4, Suit.BLANK);
		List<Card> cards = Arrays.asList(c1,c2,c3,c4,c5,c6,c7,c8,c9,c10);
		Hand h = Hand.cards2hand(cards);
		System.out.println(h.toString());
	}
	
	@Test //5555+34
	void test12() {
		Card c1 = new Card(Rank.RANK_5, Suit.BLANK);
		Card c2 = new Card(Rank.RANK_5, Suit.BLANK);
		Card c3 = new Card(Rank.RANK_5, Suit.BLANK);
		Card c4 = new Card(Rank.RANK_5, Suit.BLANK);
		Card c5 = new Card(Rank.RANK_4, Suit.BLANK);
		Card c6 = new Card(Rank.RANK_3, Suit.BLANK);
		List<Card> cards = Arrays.asList(c1,c2,c3,c4,c5,c6);
		Hand h = Hand.cards2hand(cards);
		System.out.println(h.toString());
	}
	
	@Test //5555+3344
	void test13() {
		Card c1 = new Card(Rank.RANK_5, Suit.BLANK);
		Card c2 = new Card(Rank.RANK_5, Suit.BLANK);
		Card c3 = new Card(Rank.RANK_5, Suit.BLANK);
		Card c4 = new Card(Rank.RANK_5, Suit.BLANK);
		Card c5 = new Card(Rank.RANK_4, Suit.BLANK);
		Card c6 = new Card(Rank.RANK_3, Suit.BLANK);
		Card c7 = new Card(Rank.RANK_4, Suit.BLANK);
		Card c8 = new Card(Rank.RANK_3, Suit.BLANK);
		List<Card> cards = Arrays.asList(c1,c2,c3,c4,c5,c6,c7,c8);
		Hand h = Hand.cards2hand(cards);
		System.out.println(h.toString());
	}

	@Test //55556666+789 10
	void test14() {
		Card c1 = new Card(Rank.RANK_5, Suit.BLANK);
		Card c2 = new Card(Rank.RANK_5, Suit.BLANK);
		Card c3 = new Card(Rank.RANK_5, Suit.BLANK);
		Card c4 = new Card(Rank.RANK_5, Suit.BLANK);
		Card c5 = new Card(Rank.RANK_10, Suit.BLANK);
		Card c6 = new Card(Rank.RANK_9, Suit.BLANK);
		Card c7 = new Card(Rank.RANK_6, Suit.BLANK);
		Card c8 = new Card(Rank.RANK_6, Suit.BLANK);
		Card c9 = new Card(Rank.RANK_6, Suit.BLANK);
		Card c10 = new Card(Rank.RANK_6, Suit.BLANK);
		Card c11 = new Card(Rank.RANK_7, Suit.BLANK);
		Card c12 = new Card(Rank.RANK_8, Suit.BLANK);
		List<Card> cards = Arrays.asList(c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12);
		Hand h = Hand.cards2hand(cards);
		System.out.println(h.toString());
	}
	
	@Test //55556666+33447788
	void test15() {
		Card c1 = new Card(Rank.RANK_5, Suit.BLANK);
		Card c2 = new Card(Rank.RANK_5, Suit.BLANK);
		Card c3 = new Card(Rank.RANK_5, Suit.BLANK);
		Card c4 = new Card(Rank.RANK_5, Suit.BLANK);
		Card c5 = new Card(Rank.RANK_3, Suit.BLANK);
		Card c6 = new Card(Rank.RANK_4, Suit.BLANK);
		Card c7 = new Card(Rank.RANK_6, Suit.BLANK);
		Card c8 = new Card(Rank.RANK_6, Suit.BLANK);
		Card c9 = new Card(Rank.RANK_6, Suit.BLANK);
		Card c10 = new Card(Rank.RANK_6, Suit.BLANK);
		Card c11 = new Card(Rank.RANK_7, Suit.BLANK);
		Card c12 = new Card(Rank.RANK_8, Suit.BLANK);
		Card c13 = new Card(Rank.RANK_3, Suit.BLANK);
		Card c14 = new Card(Rank.RANK_4, Suit.BLANK);
		Card c15 = new Card(Rank.RANK_7, Suit.BLANK);
		Card c16 = new Card(Rank.RANK_8, Suit.BLANK);
		List<Card> cards = Arrays.asList(c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12,c13,c14,c15,c16);
		Hand h = Hand.cards2hand(cards);
		System.out.println(h.toString());
	}
	
	@Test //Jj
	void test16() {
		Card c1 = new Card(Rank.RANK_BLACK_JOKER, Suit.BLANK);
		Card c2 = new Card(Rank.RANK_RED_JOKER, Suit.BLANK);
		List<Card> cards = Arrays.asList(c1,c2);
		Hand h = Hand.cards2hand(cards);
		System.out.println(h.toString());
	}
	

	@Test //Illegal 333555
	void test17() {
		Card c1 = new Card(Rank.RANK_5, Suit.BLANK);
		Card c2 = new Card(Rank.RANK_5, Suit.BLANK);
		Card c3 = new Card(Rank.RANK_5, Suit.BLANK);
		Card c4 = new Card(Rank.RANK_3, Suit.BLANK);
		Card c5 = new Card(Rank.RANK_3, Suit.BLANK);
		Card c6 = new Card(Rank.RANK_3, Suit.BLANK);
		List<Card> cards = Arrays.asList(c1,c2,c3,c4,c5,c6);
		Hand h = Hand.cards2hand(cards);
		System.out.println(h.toString());
	}
}
