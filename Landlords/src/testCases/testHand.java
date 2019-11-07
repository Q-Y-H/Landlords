package testCases;

import java.util.*;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import entities.*;
import enums.*;
import junit.framework.Assert;
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
		List<Card> cards = Arrays.asList(c[3],c[3],c[3],c[5],c[5],c[5]);
		Hand h = Hand.cards2hand(cards);
		System.out.println("Test 17: " +h.toString());
	}
	
	@Test //Illegal 31617
	void test18() {
		List<Card> cards = Arrays.asList(c[3],c[16],c[17]);
		Hand h = Hand.cards2hand(cards);
		System.out.println("Test 18: "+h.toString());
	}
	
	@Test //Illegal 44443
	void test19() {
		List<Card> cards = Arrays.asList(c[3],c[4],c[4],c[4],c[4]);
		Hand h = Hand.cards2hand(cards);
		System.out.println("Test 19: " +h.toString());
	}
	
	@Test //Illegal 34
	void test20() {
		List<Card> cards = Arrays.asList(c[3],c[4]);
		Hand h = Hand.cards2hand(cards);
		System.out.println("Test 20: "+h.toString());
	}
	
	@Test //Illegal 345
	void test21() {
		List<Card> cards = Arrays.asList(c[3],c[4],c[5]);
		Hand h = Hand.cards2hand(cards);
		System.out.println("Test 21: " +h.toString());
	}
	
	@Test //Illegal 443
	void test22() {
		List<Card> cards = Arrays.asList(c[3],c[4],c[4]);
		Hand h = Hand.cards2hand(cards);
		System.out.println("Test 22: "+h.toString());
	}
	
	@Test //Illegal 345
	void test23() {
		List<Card> cards = Arrays.asList(c[3],c[4],c[5],c[6]);
		Hand h = Hand.cards2hand(cards);
		System.out.println("Test 23: " +h.toString());
	}
	/*
	 * test method compareTo(Hand)
	 *
	 */
	
	@Test //SOLO(3) vs SOLO(5)
	void test100() {
		List<Card> cards1 = Arrays.asList(c[3]);
		Hand h1 = Hand.cards2hand(cards1);
		List<Card> cards2 = Arrays.asList(c[5]);
		Hand h2 = Hand.cards2hand(cards2);
		int result = h1.compareTo(h2);
		Assert.assertEquals(-1, result);
	}
	
	@Test //SOLO(4) vs SOLO(4)
	void test101() {
		List<Card> cards1 = Arrays.asList(c[4]);
		Hand h1 = Hand.cards2hand(cards1);
		List<Card> cards2 = Arrays.asList(c[4]);
		Hand h2 = Hand.cards2hand(cards2);
		int result = h1.compareTo(h2);
		Assert.assertEquals(0, result);
	}
	
	@Test //SOLO(9) vs SOLO(7)
	void test102() {
		List<Card> cards1 = Arrays.asList(c[9]);
		Hand h1 = Hand.cards2hand(cards1);
		List<Card> cards2 = Arrays.asList(c[7]);
		Hand h2 = Hand.cards2hand(cards2);
		int result = h1.compareTo(h2);
		Assert.assertEquals(1, result);
	}
	
	@Test //SOLO(3) vs PAIR(66)
	void test103() {
		List<Card> cards1 = Arrays.asList(c[3]);
		Hand h1 = Hand.cards2hand(cards1);
		List<Card> cards2 = Arrays.asList(c[6],c[6]);
		Hand h2 = Hand.cards2hand(cards2);
		int result = h1.compareTo(h2);
		Assert.assertEquals(1, result);
	}
	
	@Test //SOLO(3) vs TRIO(6663)
	void test104() {
		List<Card> cards1 = Arrays.asList(c[3]);
		Hand h1 = Hand.cards2hand(cards1);
		List<Card> cards2 = Arrays.asList(c[6],c[6],c[6],c[3]);
		Hand h2 = Hand.cards2hand(cards2);
		int result = h1.compareTo(h2);
		Assert.assertEquals(1, result);
	}
	
	@Test //SOLO(3) vs QUAD(666645)
	void test105() {
		List<Card> cards1 = Arrays.asList(c[3]);
		Hand h1 = Hand.cards2hand(cards1);
		List<Card> cards2 = Arrays.asList(c[6],c[6],c[6],c[6],c[5],c[4]);
		Hand h2 = Hand.cards2hand(cards2);
		int result = h1.compareTo(h2);
		Assert.assertEquals(1, result);
	}
	
	@Test //SOLO(3) vs BOMB(6666)
	void test106() {
		List<Card> cards1 = Arrays.asList(c[3]);
		Hand h1 = Hand.cards2hand(cards1);
		List<Card> cards2 = Arrays.asList(c[6],c[6],c[6],c[6]);
		Hand h2 = Hand.cards2hand(cards2);
		int result = h1.compareTo(h2);
		Assert.assertEquals(-1, result);
	}
	
	@Test //SOLO(3) vs ROCKET(1617)
	void test107() {
		List<Card> cards1 = Arrays.asList(c[3]);
		Hand h1 = Hand.cards2hand(cards1);
		List<Card> cards2 = Arrays.asList(c[16],c[17]);
		Hand h2 = Hand.cards2hand(cards2);
		int result = h1.compareTo(h2);
		Assert.assertEquals(-1, result);
	}
	
	@Test //PAIR(33) vs PAIR(55)
	void test108() {
		List<Card> cards1 = Arrays.asList(c[3],c[3]);
		Hand h1 = Hand.cards2hand(cards1);
		List<Card> cards2 = Arrays.asList(c[5],c[5]);
		Hand h2 = Hand.cards2hand(cards2);
		int result = h1.compareTo(h2);
		Assert.assertEquals(-1, result);
	}
	
	@Test //PAIR(44) vs PAIR(44)
	void test109() {
		List<Card> cards1 = Arrays.asList(c[4],c[4]);
		Hand h1 = Hand.cards2hand(cards1);
		List<Card> cards2 = Arrays.asList(c[4],c[4]);
		Hand h2 = Hand.cards2hand(cards2);
		int result = h1.compareTo(h2);
		Assert.assertEquals(0, result);
	}
	
	@Test //PAIR(77) vs PAIR(44)
	void test110() {
		List<Card> cards1 = Arrays.asList(c[7],c[7]);
		Hand h1 = Hand.cards2hand(cards1);
		List<Card> cards2 = Arrays.asList(c[4],c[4]);
		Hand h2 = Hand.cards2hand(cards2);
		int result = h1.compareTo(h2);
		Assert.assertEquals(1, result);
	}
	
	@Test //PAIR(77) vs TRIO(4445)
	void test111() {
		List<Card> cards1 = Arrays.asList(c[7],c[7]);
		Hand h1 = Hand.cards2hand(cards1);
		List<Card> cards2 = Arrays.asList(c[4],c[4],c[4],c[5]);
		Hand h2 = Hand.cards2hand(cards2);
		int result = h1.compareTo(h2);
		Assert.assertEquals(1, result);
	}
	
	@Test //PAIR(77) vs SQUAD(333345)
	void test112() {
		List<Card> cards1 = Arrays.asList(c[7],c[7]);
		Hand h1 = Hand.cards2hand(cards1);
		List<Card> cards2 = Arrays.asList(c[3],c[3],c[3],c[3],c[4],c[5]);
		Hand h2 = Hand.cards2hand(cards2);
		int result = h1.compareTo(h2);
		Assert.assertEquals(1, result);
	}
	
	@Test //PAIR(77) vs SOLO(10)
	void test113() {
		List<Card> cards1 = Arrays.asList(c[7],c[7]);
		Hand h1 = Hand.cards2hand(cards1);
		List<Card> cards2 = Arrays.asList(c[10]);
		Hand h2 = Hand.cards2hand(cards2);
		int result = h1.compareTo(h2);
		Assert.assertEquals(1, result);
	}
	
	@Test //TRIO(888) vs SOLO(10)
	void test114() {
		List<Card> cards1 = Arrays.asList(c[8],c[8],c[8]);
		Hand h1 = Hand.cards2hand(cards1);
		List<Card> cards2 = Arrays.asList(c[10]);
		Hand h2 = Hand.cards2hand(cards2);
		int result = h1.compareTo(h2);
		Assert.assertEquals(1, result);
	}
	
	@Test //TRIO(888) vs ILLEGAL(46)
	void test115() {
		List<Card> cards1 = Arrays.asList(c[8],c[8],c[8]);
		Hand h1 = Hand.cards2hand(cards1);
		List<Card> cards2 = Arrays.asList(c[4],c[6]);
		Hand h2 = Hand.cards2hand(cards2);
		int result = h1.compareTo(h2);
		Assert.assertEquals(1, result);
	}
	
	@Test //TRIO(888) vs SOLO(10)
	void test116() {
		List<Card> cards1 = Arrays.asList(c[8],c[8],c[8]);
		Hand h1 = Hand.cards2hand(cards1);
		List<Card> cards2 = Arrays.asList(c[10]);
		Hand h2 = Hand.cards2hand(cards2);
		int result = h1.compareTo(h2);
		Assert.assertEquals(1, result);
	}
	
	@Test //TRIO(888) vs PAIR(1010)
	void test117() {
		List<Card> cards1 = Arrays.asList(c[8],c[8],c[8]);
		Hand h1 = Hand.cards2hand(cards1);
		List<Card> cards2 = Arrays.asList(c[10],c[10]);
		Hand h2 = Hand.cards2hand(cards2);
		int result = h1.compareTo(h2);
		Assert.assertEquals(1, result);
	}
	
	@Test //TRIO(888) vs TRIO(101010)
	void test118() {
		List<Card> cards1 = Arrays.asList(c[8],c[8],c[8]);
		Hand h1 = Hand.cards2hand(cards1);
		List<Card> cards2 = Arrays.asList(c[10],c[10],c[10]);
		Hand h2 = Hand.cards2hand(cards2);
		int result = h1.compareTo(h2);
		Assert.assertEquals(-1, result);
	}
	
	@Test //TRIO(888) vs TRIO(888)
	void test119() {
		List<Card> cards1 = Arrays.asList(c[8],c[8],c[8]);
		Hand h1 = Hand.cards2hand(cards1);
		List<Card> cards2 = Arrays.asList(c[8],c[8],c[8]);
		Hand h2 = Hand.cards2hand(cards2);
		int result = h1.compareTo(h2);
		Assert.assertEquals(0, result);
	}		

	@Test //SQUAD(33334455) vs ILLEGAL(46)
	void test120() {
		List<Card> cards1 = Arrays.asList(c[3],c[3],c[3],c[3],c[4],c[4],c[5],c[5]);
		Hand h1 = Hand.cards2hand(cards1);
		List<Card> cards2 = Arrays.asList(c[4],c[6]);
		Hand h2 = Hand.cards2hand(cards2);
		int result = h1.compareTo(h2);
		Assert.assertEquals(1, result);
	}
	
	@Test //SQUAD(33334455) vs SOLO(4)
	void test121() {
		List<Card> cards1 = Arrays.asList(c[3],c[3],c[3],c[3],c[4],c[4],c[5],c[5]);
		Hand h1 = Hand.cards2hand(cards1);
		List<Card> cards2 = Arrays.asList(c[4]);
		Hand h2 = Hand.cards2hand(cards2);
		int result = h1.compareTo(h2);
		Assert.assertEquals(1, result);
	}
	
	@Test //SQUAD(33334455) vs PAIR(44)
	void test122() {
		List<Card> cards1 = Arrays.asList(c[3],c[3],c[3],c[3],c[4],c[4],c[5],c[5]);
		Hand h1 = Hand.cards2hand(cards1);
		List<Card> cards2 = Arrays.asList(c[4],c[4]);
		Hand h2 = Hand.cards2hand(cards2);
		int result = h1.compareTo(h2);
		Assert.assertEquals(1, result);
	}
	
	@Test //SQUAD(33334455) vs TRIO(4449)
	void test123() {
		List<Card> cards1 = Arrays.asList(c[3],c[3],c[3],c[3],c[4],c[4],c[5],c[5]);
		Hand h1 = Hand.cards2hand(cards1);
		List<Card> cards2 = Arrays.asList(c[4],c[4],c[4],c[9]);
		Hand h2 = Hand.cards2hand(cards2);
		int result = h1.compareTo(h2);
		Assert.assertEquals(1, result);
	}
	
	@Test //SQUAD(33334455) vs SQUAD(44445566)
	void test124() {
		List<Card> cards1 = Arrays.asList(c[3],c[3],c[3],c[3],c[4],c[4],c[5],c[5]);
		Hand h1 = Hand.cards2hand(cards1);
		List<Card> cards2 = Arrays.asList(c[4],c[4],c[4],c[9]);
		Hand h2 = Hand.cards2hand(cards2);
		int result = h1.compareTo(h2);
		Assert.assertEquals(1, result);
	}
	
	@Test //SQUAD(55556677) vs SQUAD(33334455)
	void test125() {
		List<Card> cards1 = Arrays.asList(c[5],c[5],c[5],c[5],c[6],c[6],c[7],c[7]);
		Hand h1 = Hand.cards2hand(cards1);
		List<Card> cards2 = Arrays.asList(c[3],c[3],c[3],c[3],c[4],c[4],c[5],c[5]);
		Hand h2 = Hand.cards2hand(cards2);
		int result = h1.compareTo(h2);
		Assert.assertEquals(1, result);
	}
	
	@Test //SQUAD(55556677) vs BOMB(3333)
	void test126() {
		List<Card> cards1 = Arrays.asList(c[5],c[5],c[5],c[5],c[6],c[6],c[7],c[7]);
		Hand h1 = Hand.cards2hand(cards1);
		List<Card> cards2 = Arrays.asList(c[3],c[3],c[3],c[3]);
		Hand h2 = Hand.cards2hand(cards2);
		int result = h1.compareTo(h2);
		Assert.assertEquals(-1, result);
	}
	
	@Test //SQUAD(55556677) vs ROCKET(1617)
	void test127() {
		List<Card> cards1 = Arrays.asList(c[5],c[5],c[5],c[5],c[6],c[6],c[7],c[7]);
		Hand h1 = Hand.cards2hand(cards1);
		List<Card> cards2 = Arrays.asList(c[16],c[17]);
		Hand h2 = Hand.cards2hand(cards2);
		int result = h1.compareTo(h2);
		Assert.assertEquals(-1, result);
	}

	@Test //BOMB(6666) vs ILLEGAL(163)
	void test128() {
		List<Card> cards1 = Arrays.asList(c[6],c[6],c[6],c[6]);
		Hand h1 = Hand.cards2hand(cards1);
		List<Card> cards2 = Arrays.asList(c[16],c[3]);
		Hand h2 = Hand.cards2hand(cards2);
		int result = h1.compareTo(h2);
		Assert.assertEquals(1, result);
	}
	
	@Test //BOMB(6666) vs SOLO(3)
	void test129() {
		List<Card> cards1 = Arrays.asList(c[6],c[6],c[6],c[6]);
		Hand h1 = Hand.cards2hand(cards1);
		List<Card> cards2 = Arrays.asList(c[3]);
		Hand h2 = Hand.cards2hand(cards2);
		int result = h1.compareTo(h2);
		Assert.assertEquals(1, result);
	}
	
	@Test //BOMB(6666) vs PAIR(1111)
	void test130() {
		List<Card> cards1 = Arrays.asList(c[6],c[6],c[6],c[6]);
		Hand h1 = Hand.cards2hand(cards1);
		List<Card> cards2 = Arrays.asList(c[11],c[11]);
		Hand h2 = Hand.cards2hand(cards2);
		int result = h1.compareTo(h2);
		Assert.assertEquals(1, result);
	}
	
	@Test //BOMB(6666) vs PAIR(1111)
	void test131() {
		List<Card> cards1 = Arrays.asList(c[6],c[6],c[6],c[6]);
		Hand h1 = Hand.cards2hand(cards1);
		List<Card> cards2 = Arrays.asList(c[11],c[11]);
		Hand h2 = Hand.cards2hand(cards2);
		int result = h1.compareTo(h2);
		Assert.assertEquals(1, result);
	}
	
	@Test //BOMB(6666) vs TRIO(1111117)
	void test132() {
		List<Card> cards1 = Arrays.asList(c[6],c[6],c[6],c[6]);
		Hand h1 = Hand.cards2hand(cards1);
		List<Card> cards2 = Arrays.asList(c[11],c[11],c[11],c[7]);
		Hand h2 = Hand.cards2hand(cards2);
		int result = h1.compareTo(h2);
		Assert.assertEquals(1, result);
	}
	
	@Test //BOMB(6666) vs SQUAD(1111111178)
	void test133() {
		List<Card> cards1 = Arrays.asList(c[6],c[6],c[6],c[6]);
		Hand h1 = Hand.cards2hand(cards1);
		List<Card> cards2 = Arrays.asList(c[11],c[11],c[11],c[11],c[7],c[8]);
		Hand h2 = Hand.cards2hand(cards2);
		int result = h1.compareTo(h2);
		Assert.assertEquals(1, result);
	}
	
	@Test //BOMB(6666) vs ROCKET(1617)
	void test134() {
		List<Card> cards1 = Arrays.asList(c[6],c[6],c[6],c[6]);
		Hand h1 = Hand.cards2hand(cards1);
		List<Card> cards2 = Arrays.asList(c[16],c[17]);
		Hand h2 = Hand.cards2hand(cards2);
		int result = h1.compareTo(h2);
		Assert.assertEquals(-1, result);
	}
	

	@Test //ROCKET(1617) vs ILLEGAL(810)
	void test135() {
		List<Card> cards1 = Arrays.asList(c[16],c[17]);
		Hand h1 = Hand.cards2hand(cards1);
		List<Card> cards2 = Arrays.asList(c[8],c[10]);
		Hand h2 = Hand.cards2hand(cards2);
		int result = h1.compareTo(h2);
		Assert.assertEquals(1, result);
	}
	

	@Test //ROCKET(1617) vs SOLO(810)
	void test136() {
		List<Card> cards1 = Arrays.asList(c[16],c[17]);
		Hand h1 = Hand.cards2hand(cards1);
		List<Card> cards2 = Arrays.asList(c[8]);
		Hand h2 = Hand.cards2hand(cards2);
		int result = h1.compareTo(h2);
		Assert.assertEquals(1, result);
	}
	
	@Test //ROCKET(1617) vs PAIR(1010)
	void test137() {
		List<Card> cards1 = Arrays.asList(c[16],c[17]);
		Hand h1 = Hand.cards2hand(cards1);
		List<Card> cards2 = Arrays.asList(c[10],c[10]);
		Hand h2 = Hand.cards2hand(cards2);
		int result = h1.compareTo(h2);
		Assert.assertEquals(1, result);
	}
	
	@Test //ROCKET(1617) vs TRIO(88810)
	void test138() {
		List<Card> cards1 = Arrays.asList(c[16],c[17]);
		Hand h1 = Hand.cards2hand(cards1);
		List<Card> cards2 = Arrays.asList(c[8],c[8],c[8],c[10]);
		Hand h2 = Hand.cards2hand(cards2);
		int result = h1.compareTo(h2);
		Assert.assertEquals(1, result);
	}
	
	@Test //ROCKET(1617) vs SQUAD(8888991010)
	void test139() {
		List<Card> cards1 = Arrays.asList(c[16],c[17]);
		Hand h1 = Hand.cards2hand(cards1);
		List<Card> cards2 = Arrays.asList(c[8],c[8],c[8],c[8],c[9],c[9],c[10],c[10]);
		Hand h2 = Hand.cards2hand(cards2);
		int result = h1.compareTo(h2);
		Assert.assertEquals(1, result);
	}
	
	@Test //ROCKET(1617) vs BOMB(8888)
	void test140() {
		List<Card> cards1 = Arrays.asList(c[16],c[17]);
		Hand h1 = Hand.cards2hand(cards1);
		List<Card> cards2 = Arrays.asList(c[8],c[8],c[8],c[8]);
		Hand h2 = Hand.cards2hand(cards2);
		int result = h1.compareTo(h2);
		Assert.assertEquals(1, result);
	}
	
	@Test //CHAINED-SOLO(34567) vs CHAINED-SOLO(78910J)
	void test141() {
		List<Card> cards1 = Arrays.asList(c[3],c[4],c[5],c[6],c[7]);
		Hand h1 = Hand.cards2hand(cards1);
		List<Card> cards2 = Arrays.asList(c[7],c[8],c[9],c[10],c[11]);
		Hand h2 = Hand.cards2hand(cards2);
		int result = h1.compareTo(h2);
		Assert.assertEquals(-1, result);
	}
	@Test //CHAINED-SOLO in different length
	void test142() {
		List<Card> cards1 = Arrays.asList(c[3],c[4],c[5],c[6],c[7]);
		Hand h1 = Hand.cards2hand(cards1);
		List<Card> cards2 = Arrays.asList(c[7],c[8],c[9],c[10],c[11],c[12]);
		Hand h2 = Hand.cards2hand(cards2);
		int result = h1.compareTo(h2);
		Assert.assertEquals(1, result);
	}
	
	@Test //TRIO(888) vs TRIO(777)
	void test143() {
		List<Card> cards1 = Arrays.asList(c[8],c[8],c[8]);
		Hand h1 = Hand.cards2hand(cards1);
		List<Card> cards2 = Arrays.asList(c[7],c[7],c[7]);
		Hand h2 = Hand.cards2hand(cards2);
		int result = h1.compareTo(h2);
		Assert.assertEquals(1, result);
	}

	@Test //TRIO(888) vs SQUAD(77771011)
	void test144() {
		List<Card> cards1 = Arrays.asList(c[8],c[8],c[8]);
		Hand h1 = Hand.cards2hand(cards1);
		List<Card> cards2 = Arrays.asList(c[7],c[7],c[7],c[7],c[10],c[11]);
		Hand h2 = Hand.cards2hand(cards2);
		int result = h1.compareTo(h2);
		Assert.assertEquals(1, result);
	}

	@Test //TRIO(888) vs BOMB(7777)
	void test145() {
		List<Card> cards1 = Arrays.asList(c[8],c[8],c[8]);
		Hand h1 = Hand.cards2hand(cards1);
		List<Card> cards2 = Arrays.asList(c[7],c[7],c[7],c[7]);
		Hand h2 = Hand.cards2hand(cards2);
		int result = h1.compareTo(h2);
		Assert.assertEquals(-1, result);
	}
	
	@Test //TRIO(888) vs ROCKET
	void test146() {
		List<Card> cards1 = Arrays.asList(c[8],c[8],c[8]);
		Hand h1 = Hand.cards2hand(cards1);
		List<Card> cards2 = Arrays.asList(c[17],c[16]);
		Hand h2 = Hand.cards2hand(cards2);
		int result = h1.compareTo(h2);
		Assert.assertEquals(-1, result);
	}
	
	@Test //PAIR(77) vs BOMB(10101010)
	void test147() {
		List<Card> cards1 = Arrays.asList(c[7],c[7]);
		Hand h1 = Hand.cards2hand(cards1);
		List<Card> cards2 = Arrays.asList(c[10],c[10],c[10],c[10]);
		Hand h2 = Hand.cards2hand(cards2);
		int result = h1.compareTo(h2);
		Assert.assertEquals(-1, result);
	}
	
	@Test //SOLO(7) vs	ILLEGAL(9 11)
	void test148() {
		List<Card> cards1 = Arrays.asList(c[7]);
		Hand h1 = Hand.cards2hand(cards1);
		List<Card> cards2 = Arrays.asList(c[9],c[11]);
		Hand h2 = Hand.cards2hand(cards2);
		int result = h1.compareTo(h2);
		Assert.assertEquals(1, result);
	}
	
	@Test //PAIR(7) vs	ILLEGAL(9 11)
	void test149() {
		List<Card> cards1 = Arrays.asList(c[7],c[7]);
		Hand h1 = Hand.cards2hand(cards1);
		List<Card> cards2 = Arrays.asList(c[9],c[11]);
		Hand h2 = Hand.cards2hand(cards2);
		int result = h1.compareTo(h2);
		Assert.assertEquals(1, result);
	}
	
	@Test //CHAINED-PAIR(778899) vs	CHAINED-PAIR(9910101111)
	void test150() {
		List<Card> cards1 = Arrays.asList(c[7],c[7],c[8],c[8],c[9],c[9]);
		Hand h1 = Hand.cards2hand(cards1);
		List<Card> cards2 = Arrays.asList(c[9],c[9],c[10],c[10],c[11],c[11]);
		Hand h2 = Hand.cards2hand(cards2);
		int result = h1.compareTo(h2);
		Assert.assertEquals(-1, result);
	}
	
	@Test //CHAINED-PAIR(778899) vs	CHAINED-PAIR(9910101111)
	void test151() {
		List<Card> cards1 = Arrays.asList(c[7],c[7],c[8],c[8],c[9],c[9]);
		Hand h1 = Hand.cards2hand(cards1);
		List<Card> cards2 = Arrays.asList(c[9],c[9],c[10],c[10],c[11],c[11]);
		Hand h2 = Hand.cards2hand(cards2);
		int result = h1.compareTo(h2);
		Assert.assertEquals(-1, result);
	}
	
	@Test //CHAINED-PAIR(121213131414) vs	CHAINED-PAIR(9910101111)
	void test152() {
		List<Card> cards1 = Arrays.asList(c[12],c[12],c[13],c[13],c[14],c[14]);
		Hand h1 = Hand.cards2hand(cards1);
		List<Card> cards2 = Arrays.asList(c[9],c[9],c[10],c[10],c[11],c[11]);
		Hand h2 = Hand.cards2hand(cards2);
		int result = h1.compareTo(h2);
		Assert.assertEquals(1, result);
	}
	
	@Test //AIRPLANE(12121213131345) vs	AIRPLANE(9991010101112)
	void test153() {
		List<Card> cards1 = Arrays.asList(c[12],c[12],c[12],c[13],c[13],c[13],c[4],c[5]);
		Hand h1 = Hand.cards2hand(cards1);
		List<Card> cards2 = Arrays.asList(c[9],c[9],c[9],c[10],c[10],c[10],c[11],c[12]);
		Hand h2 = Hand.cards2hand(cards2);
		int result = h1.compareTo(h2);
		Assert.assertEquals(1, result);
	}
	
	@Test //SQUAD(121212121314) vs	SQUAD(999910101111)
	void test154() {
		List<Card> cards1 = Arrays.asList(c[12],c[12],c[12],c[12],c[13],c[14]);
		Hand h1 = Hand.cards2hand(cards1);
		List<Card> cards2 = Arrays.asList(c[9],c[9],c[9],c[9],c[10],c[10],c[11],c[11]);
		Hand h2 = Hand.cards2hand(cards2);
		int result = h1.compareTo(h2);
		Assert.assertEquals(1, result);
	}
	
	
}
