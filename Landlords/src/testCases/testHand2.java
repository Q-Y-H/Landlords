package testCases;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entities.Card;
import entities.Hand;
import enums.Rank;
import enums.Suit;

class testHand2 {
	private Card[] c = new Card[20];
	

	@BeforeEach
 	//initial 17 cards
    public void setUp() {
	 	for(int i=3;i<18;i++)
	 		c[i] = new Card(Rank.getRankByValue(i),Suit.BLANK);
    }

	@Test
	void test1() {
		List<Card> cards = Arrays.asList(c[16],c[17]);
		Hand h = Hand.cards2hand(cards);
		System.out.println(h.getWeight());
	}
	
	@Test
	void test2() {
		List<Card> cards = Arrays.asList(c[3],c[4],c[5],c[6],c[7]);
		Hand h = Hand.cards2hand(cards);
		System.out.println(h.getWeight());
	}

	void test3() {
		List<Card> cards = Arrays.asList(c[3]);
		Hand h = Hand.cards2hand(cards);
		System.out.println(h.getWeight());
	}

	@Test
	void test4() {
		List<Card> cards = Arrays.asList(c[3],c[3]);
		Hand h = Hand.cards2hand(cards);
		System.out.println(h.getWeight());
	}
	
	@Test
	void test5() {
		List<Card> cards = Arrays.asList(c[3],c[3],c[3],c[3]);
		Hand h = Hand.cards2hand(cards);
		System.out.println(h.getWeight());
	}
	
	@Test
	void test6() {
		List<Card> cards = Arrays.asList(c[3],c[3],c[4],c[5],c[5],c[5]);
		Hand h = Hand.cards2hand(cards);
		System.out.println(h.getWeight());
	}
	
	@Test
	void test7() {
		List<Card> cards = Arrays.asList(c[8],c[8],c[8]);
		Hand h = Hand.cards2hand(cards);
		System.out.println(h.getWeight());
	}
	
	@Test
	void test8() {
		List<Card> cards = Arrays.asList(c[8],c[8],c[8],c[6],c[7]);
		Hand h = Hand.cards2hand(cards);
		System.out.println(h.getWeight());
	}
	
	@Test
	void test9() {
		List<Card> cards = Arrays.asList(c[8],c[8],c[8],c[6]);
		Hand h = Hand.cards2hand(cards);
		System.out.println(h.getWeight());
	}
	
	@Test
	void test10() {
		List<Card> cards = Arrays.asList(c[8],c[8],c[8],c[6],c[6]);
		Hand h = Hand.cards2hand(cards);
		System.out.println(h.getWeight());
	}
	
	@Test
	void test11() {
		List<Card> cards = Arrays.asList(c[10],c[10],c[10],c[10],c[6],c[7]);
		Hand h = Hand.cards2hand(cards);
		System.out.println(h.getWeight());
	}
	
	@Test
	void test12() {
		List<Card> cards = Arrays.asList(c[10],c[10],c[10],c[10],c[6],c[6],c[7],c[7]);
		Hand h = Hand.cards2hand(cards);
		System.out.println(h.getWeight());
	}
	
	@Test
	void test13() {
		List<Card> cards = Arrays.asList(c[15],c[15],c[15],c[15]);
		Hand h = Hand.cards2hand(cards);
		System.out.println(h.getWeight());
	}
}
