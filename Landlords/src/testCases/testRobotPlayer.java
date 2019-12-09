package testCases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.TestMethodOrder;

import Strategies.MediumStrategy;
import Strategies.SimpleStrategy;

import entities.Card;
import entities.Hand;
import entities.RobotPlayer;
import enums.Rank;
import enums.Suit;

@TestMethodOrder(MethodOrderer.Alphanumeric.class)
public class testRobotPlayer {
	private Card[] c;
	private RobotPlayer robot;

	@BeforeEach
	// initial 17 cards
	public void setUp() {
		c = new Card[18];
		for (int i = 3; i < 18; i++)
			c[i] = new Card(Rank.getRankByValue(i), Suit.BLANK);
	}

	/*Test for getPlayerChoice
	 * Annotation xx vs yy
	 * xx stands for previous played cards
	 * yy stands for cards on hand*/
  @Test
	public void test0() {	//33 vs 543 No cards could match
		LinkedList<Hand> handHistory=new LinkedList<Hand>();
		handHistory.add(Hand.cards2hand(new ArrayList<Card>(Arrays.asList(c[3], c[3]))));
		RobotPlayer robotplayer1=new RobotPlayer(null,null,handHistory,new MediumStrategy());
		robotplayer1.setCards(new ArrayList<Card>(Arrays.asList(c[5],c[4],c[3])));
		Assert.assertEquals("pass",robotplayer1.getPlayChoice());
	}


	@Test // getPlayChoice
	public void test1() {	//33 vs 553
		LinkedList<Hand> handHistory=new LinkedList<Hand>();
		handHistory.add(Hand.cards2hand(new ArrayList<Card>(Arrays.asList(c[3], c[3]))));
		RobotPlayer robotplayer1=new RobotPlayer(null,null,handHistory,new MediumStrategy());
		robotplayer1.setCards(new ArrayList<Card>(Arrays.asList(c[5],c[5],c[3])));
		System.out.println("Test1: "+robotplayer1.getPlayChoice());
		Assert.assertEquals("5 5 ",robotplayer1.getPlayChoice());
	}

	@Test
	public void test2() {	//1617 vs 553
		LinkedList<Hand> handHistory=new LinkedList<Hand>();
		handHistory.add(Hand.cards2hand(new ArrayList<Card>(Arrays.asList(c[16], c[17]))));
		RobotPlayer robotplayer1=new RobotPlayer(null,null,handHistory,new MediumStrategy());
		robotplayer1.setCards(new ArrayList<Card>(Arrays.asList(c[5],c[5],c[3])));
		Assert.assertEquals("pass",robotplayer1.getPlayChoice());
	}

	@Test
	public void test3() {	//null vs 553
		LinkedList<Hand> handHistory=new LinkedList<Hand>();
		RobotPlayer robotplayer1=new RobotPlayer(null,null,handHistory,new MediumStrategy());
		robotplayer1.setCards(new ArrayList<Card>(Arrays.asList(c[5],c[5],c[3])));
		Assert.assertEquals("3 ",robotplayer1.getPlayChoice());
	}

	@Test
	public void test4() {	//null vs 34567910111213
		LinkedList<Hand> handHistory=new LinkedList<Hand>();
		RobotPlayer robotplayer1=new RobotPlayer(null,null,handHistory,new MediumStrategy());
		robotplayer1.setCards(new ArrayList<Card>(Arrays.asList(c[3],c[4],c[5],c[6],c[7],c[9],c[10],c[11],c[12],c[13])));
		Assert.assertEquals("3 4 5 6 7 ",robotplayer1.getPlayChoice());
	}

	@Test
	public void test5() {	//null vs 55
		LinkedList<Hand> handHistory=new LinkedList<Hand>();
		RobotPlayer robotplayer1=new RobotPlayer(null,null,handHistory,new MediumStrategy());
		robotplayer1.setCards(new ArrayList<Card>(Arrays.asList(c[5],c[5])));
		Assert.assertEquals("5 5 ",robotplayer1.getPlayChoice());
	}

	@Test
	public void test6() {	//3333 vs 554444
		LinkedList<Hand> handHistory=new LinkedList<Hand>();
		handHistory.add(Hand.cards2hand(new ArrayList<Card>(Arrays.asList(c[3], c[3],c[3],c[3]))));
		RobotPlayer robotplayer1=new RobotPlayer(null,null,handHistory,new MediumStrategy());
		robotplayer1.setCards(new ArrayList<Card>(Arrays.asList(c[5],c[5],c[4],c[4],c[4],c[4])));
		Assert.assertEquals("4 4 4 4 ",robotplayer1.getPlayChoice());
	}

	@Test
	public void test7() {	//null vs 553
		LinkedList<Hand> handHistory=new LinkedList<Hand>();
		robot = new RobotPlayer(null,null,handHistory,new MediumStrategy());
		robot.setCards(new ArrayList<Card>(Arrays.asList(c[5],c[5],c[3])));
		System.out.println("test11"+robot.getPlayChoice());
		assertEquals(robot.getPlayChoice(), new String("3 "));
	}

	@Test
	public void test8() {	//3333 vs 554444
		LinkedList<Hand> handHistory=new LinkedList<Hand>();
		handHistory.add(Hand.cards2hand(new ArrayList<Card>(Arrays.asList(c[3], c[3],c[3],c[3]))));
		RobotPlayer robotplayer1=new RobotPlayer(null,null,handHistory,new MediumStrategy());
		robotplayer1.setCards(new ArrayList<Card>(Arrays.asList(c[5],c[5],c[4],c[4],c[4],c[4])));
		robotplayer1.parseCards();
		System.out.println("Test8"+robotplayer1.getHandList());
		Assert.assertEquals("4 4 4 4 ",robotplayer1.getPlayChoice());
	}

	@Test
	public void test9() {	//3333 vs 5544441515776399101113
		LinkedList<Hand> handHistory=new LinkedList<Hand>();
		handHistory.add(Hand.cards2hand(new ArrayList<Card>(Arrays.asList(c[3], c[3],c[3],c[3]))));
		RobotPlayer robotplayer1=new RobotPlayer(null,null,handHistory,new MediumStrategy());
		robotplayer1.setCards(new ArrayList<Card>(Arrays.asList(c[5],c[5],c[4],c[4],c[4],c[4],c[15],c[15],c[7],c[7],c[6],c[3],c[9],c[9],c[10],c[11],c[13])));
		robotplayer1.parseCards();
		System.out.println("Test8"+robotplayer1.getHandList());
		Assert.assertEquals("4 4 4 4 ",robotplayer1.getPlayChoice());
	}

	@Test
	public void test10() {	//none vs 5544441515776399101113
		LinkedList<Hand> handHistory=new LinkedList<Hand>();
		handHistory.add(Hand.cards2hand(new ArrayList<Card>()));
		handHistory.add(Hand.cards2hand(new ArrayList<Card>()));
		RobotPlayer robotplayer1=new RobotPlayer(null,null,handHistory,new MediumStrategy());
		robotplayer1.setCards(new ArrayList<Card>(Arrays.asList(c[5],c[5],c[4],c[4],c[4],c[4],c[15],c[15],c[7],c[7],c[6],c[3],c[9],c[9],c[10],c[11],c[13])));
		robotplayer1.parseCards();
		System.out.println("Test8"+robotplayer1.getHandList());
		Assert.assertEquals("3 ",robotplayer1.getPlayChoice());
	}


	@Test
	public void test11() {	//none vs 5544441515776399101113
		LinkedList<Hand> handHistory=new LinkedList<Hand>();
		handHistory.add(Hand.cards2hand(new ArrayList<Card>(Arrays.asList(c[3]))));
		handHistory.add(Hand.cards2hand(new ArrayList<Card>()));
		RobotPlayer robotplayer1=new RobotPlayer(null,null,handHistory,new MediumStrategy());
		robotplayer1.setCards(new ArrayList<Card>(Arrays.asList(c[5],c[5],c[4],c[4],c[4],c[4],c[15],c[15],c[7],c[7],c[6],c[3],c[9],c[9],c[10],c[11],c[13])));
		robotplayer1.parseCards();
		System.out.println("Test8"+robotplayer1.getHandList());
		Assert.assertEquals("6 ",robotplayer1.getPlayChoice());
	}



	//simple strategy
	@Test
	public void test12() {	//none vs 5544441515776399101113
		LinkedList<Hand> handHistory=new LinkedList<Hand>();
		handHistory.add(Hand.cards2hand(new ArrayList<Card>()));
		handHistory.add(Hand.cards2hand(new ArrayList<Card>()));
		RobotPlayer robotplayer1=new RobotPlayer(null,null,handHistory,new SimpleStrategy());
		robotplayer1.setCards(new ArrayList<Card>(Arrays.asList(c[3],c[4],c[4],c[4],c[4],c[5],c[5],c[6],c[7],c[7],c[9],c[9],c[10],c[11],c[13],c[15],c[15])));
		robotplayer1.parseCards();
		System.out.println("Test8"+robotplayer1.getHandList());
		Assert.assertEquals("3 ",robotplayer1.getPlayChoice());
	}

	
	@Test
	public void test13() {	//3 vs 5544441515776399101113
		LinkedList<Hand> handHistory=new LinkedList<Hand>();
		handHistory.add(Hand.cards2hand(new ArrayList<Card>(Arrays.asList(c[3]))));
		handHistory.add(Hand.cards2hand(new ArrayList<Card>()));
		RobotPlayer robotplayer1=new RobotPlayer(null,null,handHistory,new SimpleStrategy());
		robotplayer1.setCards(new ArrayList<Card>(Arrays.asList(c[3],c[4],c[4],c[4],c[4],c[5],c[5],c[6],c[7],c[7],c[9],c[9],c[10],c[11],c[13],c[15],c[15])));
		robotplayer1.parseCards();
		System.out.println("Test8"+robotplayer1.getHandList());
		Assert.assertEquals("4 ",robotplayer1.getPlayChoice());
	}

	/*Test for parseCards*/
	@Test
	public void testparseCards_3333151617() {
		LinkedList<Hand> handHistory=new LinkedList<Hand>();
		RobotPlayer robotplayer1=new RobotPlayer(null,null,handHistory,new MediumStrategy());
		List<Card> cards1 = Arrays.asList(c[3], c[3], c[3], c[3], c[15], c[16], c[17]);
		robotplayer1.setCards(cards1);
		robotplayer1.parseCards();
		List<Hand> exceptedHands=new ArrayList<Hand>();
		exceptedHands.add(Hand.cards2hand(new ArrayList<Card>(Arrays.asList(c[15]))));
		exceptedHands.add(Hand.cards2hand(new ArrayList<Card>(Arrays.asList(c[3],c[3],c[3],c[3]))));
		exceptedHands.add(Hand.cards2hand(new ArrayList<Card>(Arrays.asList(c[16],c[17]))));
		String result="";
		for(Hand hand: exceptedHands) {
			result+=hand.toString();
		}
		Assert.assertEquals(result,robotplayer1.getHandList());
	}

	@Test
	public void testparseCards_4455678910() {
		LinkedList<Hand> handHistory=new LinkedList<Hand>();
		RobotPlayer robotplayer1=new RobotPlayer(null,null,handHistory,new MediumStrategy());
		List<Card> cards1 = Arrays.asList( c[4], c[4], c[5], c[5], c[6], c[7], c[8], c[9], c[10]);
		robotplayer1.setCards(cards1);
		robotplayer1.parseCards();
		List<Hand> exceptedHands=new ArrayList<Hand>();
		exceptedHands.add(Hand.cards2hand(new ArrayList<Card>(Arrays.asList(c[6],c[7],c[8],c[9],c[10]))));
		exceptedHands.add(Hand.cards2hand(new ArrayList<Card>(Arrays.asList(c[4],c[4]))));
		exceptedHands.add(Hand.cards2hand(new ArrayList<Card>(Arrays.asList(c[5],c[5]))));
		String result="";
		for(Hand hand: exceptedHands) {
			result+=hand.toString();
		}
		Assert.assertEquals(result,robotplayer1.getHandList());
	}

	@Test
	public void testparseCards_45678991010() {
		LinkedList<Hand> handHistory=new LinkedList<Hand>();
		RobotPlayer robotplayer1=new RobotPlayer(null,null,handHistory,new MediumStrategy());
		List<Card> cards1 = Arrays.asList( c[4], c[5], c[6], c[7], c[8], c[9], c[9], c[10], c[10]);
		robotplayer1.setCards(cards1);
		robotplayer1.parseCards();
		List<Hand> exceptedHands=new ArrayList<Hand>();
		exceptedHands.add(Hand.cards2hand(new ArrayList<Card>(Arrays.asList(c[4],c[5],c[6],c[7],c[8]))));
		exceptedHands.add(Hand.cards2hand(new ArrayList<Card>(Arrays.asList(c[9],c[9]))));
		exceptedHands.add(Hand.cards2hand(new ArrayList<Card>(Arrays.asList(c[10],c[10]))));
		String result="";
		for(Hand hand: exceptedHands) {
			result+=hand.toString();
		}
		Assert.assertEquals(result,robotplayer1.getHandList());
	}
	@Test
	public void testparseCards_44456789101010() {
		LinkedList<Hand> handHistory=new LinkedList<Hand>();
		RobotPlayer robotplayer1=new RobotPlayer(null,null,handHistory,new MediumStrategy());
		List<Card> cards1 = Arrays.asList( c[4],c[4],c[4],c[5], c[6], c[7], c[8], c[9], c[10], c[10], c[10]);
		robotplayer1.setCards(cards1);
		robotplayer1.parseCards();
		List<Hand> exceptedHands=new ArrayList<Hand>();
		exceptedHands.add(Hand.cards2hand(new ArrayList<Card>(Arrays.asList(c[6],c[7],c[8],c[9],c[5]))));
		exceptedHands.add(Hand.cards2hand(new ArrayList<Card>(Arrays.asList(c[4],c[4],c[4]))));
		exceptedHands.add(Hand.cards2hand(new ArrayList<Card>(Arrays.asList(c[10],c[10],c[10]))));
		String result="";
		for(Hand hand: exceptedHands) {
			result+=hand.toString();
		}
		Assert.assertEquals(result,robotplayer1.getHandList());
	}
	@Test
	public void testparseCards_333444678910() {
		LinkedList<Hand> handHistory=new LinkedList<Hand>();
		RobotPlayer robotplayer1=new RobotPlayer(null,null,handHistory,new MediumStrategy());
		List<Card> cards1 = Arrays.asList(c[3], c[3], c[3], c[4], c[4], c[4], c[6], c[7], c[8], c[9], c[10]);
		robotplayer1.setCards(cards1);
		robotplayer1.parseCards();
		List<Hand> exceptedHands=new ArrayList<Hand>();
		exceptedHands.add(Hand.cards2hand(new ArrayList<Card>(Arrays.asList(c[4],c[4],c[4],c[3],c[3],c[3]))));
		exceptedHands.add(Hand.cards2hand(new ArrayList<Card>(Arrays.asList(c[6],c[7],c[8],c[9],c[10]))));
		String result="";
		for(Hand hand: exceptedHands) {
			result+=hand.toString();
		}
		Assert.assertEquals(result,robotplayer1.getHandList());
	}

	@Test
	public void testparseCards_334455678910() {
		LinkedList<Hand> handHistory=new LinkedList<Hand>();
		RobotPlayer robotplayer1=new RobotPlayer(null,null,handHistory,new MediumStrategy());
		List<Card> cards1 = Arrays.asList(c[3], c[3], c[4], c[4], c[5], c[5], c[6], c[7], c[8], c[9], c[10]);
		robotplayer1.setCards(cards1);
		robotplayer1.parseCards();
		List<Hand> exceptedHands=new ArrayList<Hand>();
		exceptedHands.add(Hand.cards2hand(new ArrayList<Card>(Arrays.asList(c[3],c[3],c[4],c[4],c[5],c[5]))));
		exceptedHands.add(Hand.cards2hand(new ArrayList<Card>(Arrays.asList(c[6],c[7],c[8],c[9],c[10]))));
		String result="";
		for(Hand hand: exceptedHands) {
			result+=hand.toString();
		}
		Assert.assertEquals(result,robotplayer1.getHandList());
	}

	@Test
	public void testparseCards_345678891011121317() {
		LinkedList<Hand> handHistory=new LinkedList<Hand>();
		RobotPlayer robotplayer1=new RobotPlayer(null,null,handHistory,new MediumStrategy());
		List<Card> cards1 = Arrays.asList(c[3], c[4], c[5], c[6], c[7], c[8],c[8] ,c[9], c[10], c[11], c[12] ,c[13], c[17]);
		robotplayer1.setCards(cards1);
		robotplayer1.parseCards();
		List<Hand> exceptedHands=new ArrayList<Hand>();
		exceptedHands.add(Hand.cards2hand(new ArrayList<Card>(Arrays.asList(c[3],c[4],c[5],c[6],c[7]))));
		exceptedHands.add(Hand.cards2hand(new ArrayList<Card>(Arrays.asList(c[11],c[12],c[13],c[8],c[9],c[10]))));
		exceptedHands.add(Hand.cards2hand(new ArrayList<Card>(Arrays.asList(c[17]))));
		String result="";
		for(Hand hand: exceptedHands) {
			result+=hand.toString();
		}
		Assert.assertEquals(result,robotplayer1.getHandList());
	}

	@Test
	public void testparseCards_456789910101111() {
		LinkedList<Hand> handHistory=new LinkedList<Hand>();
		RobotPlayer robotplayer1=new RobotPlayer(null,null,handHistory,new MediumStrategy());
		List<Card> cards1 = Arrays.asList(c[4], c[5], c[6], c[7], c[8], c[9], c[9], c[10], c[10], c[11] ,c[11]);
		robotplayer1.setCards(cards1);
		robotplayer1.parseCards();
		List<Hand> exceptedHands=new ArrayList<Hand>();
		exceptedHands.add(Hand.cards2hand(new ArrayList<Card>(Arrays.asList(c[4],c[5],c[6],c[7],c[8]))));
		exceptedHands.add(Hand.cards2hand(new ArrayList<Card>(Arrays.asList(c[11],c[11],c[10],c[9],c[9],c[10]))));
		String result="";
		for(Hand hand: exceptedHands) {
			result+=hand.toString();
		}
		Assert.assertEquals(result,robotplayer1.getHandList());
	}

	/*Test for function decideRunForLandLord*/
	@Test
	public void test_decideRunForLandLord_weightSumGreaterThan0() {
		RobotPlayer robotplayer1=new RobotPlayer("R2", null);
		List<Card> cards1 = Arrays.asList(c[3], c[4], c[5], c[6], c[7], c[8],c[8] ,c[9], c[10], c[11], c[12] ,c[13],c[16], c[17]);
		robotplayer1.setCards(cards1);
		robotplayer1.parseCards();
		Assert.assertEquals(robotplayer1.decideRunForLandlord(),true);
	}

	@Test
	public void test_decideRunForLandLord_weightSumGreaterLessThan0() {
		RobotPlayer robotplayer1=new RobotPlayer("R2", null);
		List<Card> cards1 = Arrays.asList(c[3], c[4], c[5], c[6], c[7], c[8],c[8] ,c[9], c[10], c[11], c[12] ,c[13],c[13], c[13]);
		robotplayer1.setCards(cards1);
		robotplayer1.parseCards();
		Assert.assertEquals(robotplayer1.decideRunForLandlord(),false);
	}

}
