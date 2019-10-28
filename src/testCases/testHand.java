package testCases;

import org.junit.Assert;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import entities.*;
import enums.*;
import junit.framework.TestCase;

class testHand extends TestCase { 
	private Card[] c = new Card[20];
	

	@BeforeEach
 	//initial 17 cards
    public void setUp() {
	 	for(int i=3;i<18;i++)
	 		c[i] = new Card(Rank.getRankByValue(i),Suit.BLANK);
    }

	
	/*
	 * test method card2Hand(List<Card>)
	 *
	 */
	@Test //5
	void test01() {
		List<Card> cards = Arrays.asList(c[5]);
		Hand h = Hand.cards2hand(cards);
		System.out.println(h.toString());
	}
	@Test //55
	void test02() {
		List<Card> cards = Arrays.asList(c[5],c[5]);
		Hand h = Hand.cards2hand(cards);
		System.out.println(h.toString());
	}
	@Test //555
	void test03() {
		List<Card> cards = Arrays.asList(c[5],c[5],c[5]);
		Hand h = Hand.cards2hand(cards);
		System.out.println(h.toString());
	}
	@Test //5555
	void test04() {
		List<Card> cards = Arrays.asList(c[5],c[5],c[5],c[5]);
		Hand h = Hand.cards2hand(cards);
		System.out.println(h.toString());
	}
	
	@Test //34567
	void test05() {
		List<Card> cards = Arrays.asList(c[3],c[4],c[5],c[6],c[7]);
		Hand h = Hand.cards2hand(cards);
		System.out.println(h.toString());
	}
	
	@Test //445566
	void test06() {
		List<Card> cards = Arrays.asList(c[4],c[5],c[6],c[4],c[5],c[6]);
		Hand h = Hand.cards2hand(cards);
		System.out.println(h.toString());
	}
	
	@Test //555666
	void test07() {
		List<Card> cards = Arrays.asList(c[5],c[6],c[5],c[6],c[5],c[6]);
		Hand h = Hand.cards2hand(cards);
		System.out.println(h.toString());
	}
	
	
	@Test //5553
	void test08() {
		List<Card> cards = Arrays.asList(c[5],c[5],c[5],c[3]);
		Hand h = Hand.cards2hand(cards);
		System.out.println(h.toString());
	}
	
	@Test //555666+34
	void test09() {
		List<Card> cards = Arrays.asList(c[5],c[6],c[5],c[6],c[5],c[6],c[3],c[4]);
		Hand h = Hand.cards2hand(cards);
		System.out.println(h.toString());
	}
	
	@Test //55533
	void test10() {
		Card c1 = new Card(Rank.RANK_5, Suit.BLANK);
		Card c2 = new Card(Rank.RANK_3, Suit.BLANK);
		List<Card> cards = Arrays.asList(c[5],c[5],c[5],c[3],c[3]);
		Hand h = Hand.cards2hand(cards);
		System.out.println(h.toString());
	}
	
	@Test //555666+3344
	void test11() {
		List<Card> cards = Arrays.asList(c[5],c[6],c[5],c[6],c[5],c[6],c[3],c[4],c[3],c[4]);
		Hand h = Hand.cards2hand(cards);
		System.out.println(h.toString());
	}
	
	@Test //5555+34
	void test12() {
		List<Card> cards = Arrays.asList(c[5],c[5],c[5],c[5],c[3],c[4]);
		Hand h = Hand.cards2hand(cards);
		System.out.println(h.toString());
	}
	
	@Test //5555+3344
	void test13() {
		List<Card> cards = Arrays.asList(c[5],c[5],c[5],c[5],c[3],c[4],c[3],c[4]);
		Hand h = Hand.cards2hand(cards);
		System.out.println(h.toString());
	}

	@Test //55556666+789 10
	void test14() {
		List<Card> cards = Arrays.asList(c[5],c[6],c[5],c[6],c[5],c[6],c[5],c[6],c[7],c[8],c[9],c[10]);
		Hand h = Hand.cards2hand(cards);
		System.out.println(h.toString());
	}
	
	@Test //55556666+33447788
	void test15() {
		List<Card> cards = Arrays.asList(c[5],c[6],c[5],c[6],c[5],c[6],c[5],c[6],c[7],c[8],c[7],c[8],c[3],c[4],c[3],c[4]);
		Hand h = Hand.cards2hand(cards);
		System.out.println(h.toString());
	}
	
	@Test //Jj
	void test16() {
		List<Card> cards = Arrays.asList(c[16],c[17]);
		Hand h = Hand.cards2hand(cards);
		System.out.println(h.toString());
	}
	

	@Test //Illegal 333555
	void test17() {
		List<Card> cards = Arrays.asList();
		Hand h = Hand.cards2hand(cards);
		System.out.println(h.toString());
	}
	
	/*
	 * test method compareTo(Hand)
	 *
	 */
	
	@Test //3 vs 5
	void test100() {
		List<Card> cards1 = Arrays.asList(c[3]);
		Hand h1 = Hand.cards2hand(cards1);
		List<Card> cards2 = Arrays.asList(c[5]);
		Hand h2 = Hand.cards2hand(cards2);
		int result = h1.compareTo(h2);
		Assert.assertEquals(-1, result);
	}
	
	@Test //33 vs 55
	void test101() {
		List<Card> cards1 = Arrays.asList(c[3],c[3]);
		Hand h1 = Hand.cards2hand(cards1);
		List<Card> cards2 = Arrays.asList(c[5],c[5]);
		Hand h2 = Hand.cards2hand(cards2);
		int result = h1.compareTo(h2);
		Assert.assertEquals(-1, result);
	}
	@Test //3333 vs Rocket
	void test102() {
		List<Card> cards1 = Arrays.asList(c[3],c[3],c[3],c[3]);
		Hand h1 = Hand.cards2hand(cards1);
		List<Card> cards2 = Arrays.asList(c[16],c[17]);
		Hand h2 = Hand.cards2hand(cards2);
		int result = h1.compareTo(h2);
		Assert.assertEquals(-1, result);
	}
	
	@Test //34567 vs 78910J
	void test103() {
		List<Card> cards1 = Arrays.asList(c[3],c[4],c[5],c[6],c[7]);
		Hand h1 = Hand.cards2hand(cards1);
		List<Card> cards2 = Arrays.asList(c[7],c[8],c[9],c[10],c[11]);
		Hand h2 = Hand.cards2hand(cards2);
		int result = h1.compareTo(h2);
		Assert.assertEquals(-1, result);
	}
	@Test //34567 vs 78910JQ
	void test104() {
		List<Card> cards1 = Arrays.asList(c[3],c[4],c[5],c[6],c[7]);
		Hand h1 = Hand.cards2hand(cards1);
		List<Card> cards2 = Arrays.asList(c[7],c[8],c[9],c[10],c[11],c[12]);
		Hand h2 = Hand.cards2hand(cards2);
		int result = h1.compareTo(h2);
		Assert.assertEquals(1, result);
	}
	@Test //3 vs Illegal
	void test105() {
		List<Card> cards1 = Arrays.asList(c[3]);
		Hand h1 = Hand.cards2hand(cards1);
		List<Card> cards2 = Arrays.asList(c[3],c[5]);
		Hand h2 = Hand.cards2hand(cards2);
		int result = h1.compareTo(h2);
		Assert.assertEquals(1, result);
	}
	
	@Test //3 vs Illegal
	void test106() {
		List<Card> cards1 = Arrays.asList(c[3]);
		Hand h1 = Hand.cards2hand(cards1);
		List<Card> cards2 = Arrays.asList(c[3],c[5]);
		Hand h2 = Hand.cards2hand(cards2);
		int result = h1.compareTo(h2);
		Assert.assertEquals(1, result);
	}
	@Test //Illegal vs 3
	void test107() {
		List<Card> cards1 = Arrays.asList(c[3],c[5]);
		Hand h1 = Hand.cards2hand(cards1);
		List<Card> cards2 = Arrays.asList(c[3]);
		Hand h2 = Hand.cards2hand(cards2);
		int result = h1.compareTo(h2);
		Assert.assertEquals(-1, result);
	}
	
}
