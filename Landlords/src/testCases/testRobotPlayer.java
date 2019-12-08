package testCases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entities.Card;
import entities.Hand;
import entities.RobotPlayer;
import enums.Rank;
import enums.Suit;
import junit.framework.TestCase;


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
	
  @Test
	public void test0() {	//No cards could match
		LinkedList<Hand> handHistory=new LinkedList<Hand>();
		handHistory.add(Hand.cards2hand(new ArrayList<Card>(Arrays.asList(c[3], c[3]))));
		RobotPlayer robotplayer1=new RobotPlayer(null,null,handHistory);
		robotplayer1.setCards(new ArrayList<Card>(Arrays.asList(c[5],c[4],c[3])));
		Assert.assertEquals("pass",robotplayer1.getPlayChoice());
	}
	
  
	@Test // getPlayChoice
	public void getPlayChoice_33_553() {
		LinkedList<Hand> handHistory=new LinkedList<Hand>();
		handHistory.add(Hand.cards2hand(new ArrayList<Card>(Arrays.asList(c[3], c[3]))));
		RobotPlayer robotplayer1=new RobotPlayer(null,null,handHistory);
		robotplayer1.setCards(new ArrayList<Card>(Arrays.asList(c[5],c[5],c[3])));
		System.out.println("Test1: "+robotplayer1.getPlayChoice());
		Assert.assertEquals("5 5 ",robotplayer1.getPlayChoice());
	}
	
	@Test
	public void test2() {
		LinkedList<Hand> handHistory=new LinkedList<Hand>();
		handHistory.add(Hand.cards2hand(new ArrayList<Card>(Arrays.asList(c[16], c[17]))));
		RobotPlayer robotplayer1=new RobotPlayer(null,null,handHistory);
		robotplayer1.setCards(new ArrayList<Card>(Arrays.asList(c[5],c[5],c[3])));
		Assert.assertEquals("pass",robotplayer1.getPlayChoice());
	}
	
	@Test
	public void test3() {
		LinkedList<Hand> handHistory=new LinkedList<Hand>();
		RobotPlayer robotplayer1=new RobotPlayer(null,null,handHistory);
		robotplayer1.setCards(new ArrayList<Card>(Arrays.asList(c[5],c[5],c[3])));
		Assert.assertEquals("3 ",robotplayer1.getPlayChoice());
	}
	
	@Test
	public void test4() {
	}
	
	@Test
	public void test5() {
		LinkedList<Hand> handHistory=new LinkedList<Hand>();
		RobotPlayer robotplayer1=new RobotPlayer(null,null,handHistory);
		robotplayer1.setCards(new ArrayList<Card>(Arrays.asList(c[5],c[5])));
		Assert.assertEquals("5 5 ",robotplayer1.getPlayChoice());
	}
	
	@Test
	public void test6() {
		LinkedList<Hand> handHistory=new LinkedList<Hand>();
		handHistory.add(Hand.cards2hand(new ArrayList<Card>(Arrays.asList(c[3], c[3],c[3],c[3]))));
		RobotPlayer robotplayer1=new RobotPlayer(null,null,handHistory);
		robotplayer1.setCards(new ArrayList<Card>(Arrays.asList(c[5],c[5],c[4],c[4],c[4],c[4])));
		Assert.assertEquals("4 4 4 4 ",robotplayer1.getPlayChoice());
	}
	
	@Test
	public void test10() {
		LinkedList<Hand> handHistory=new LinkedList<Hand>();
		RobotPlayer robotplayer1=new RobotPlayer(null,null,handHistory);
		List<Card> cards1 = Arrays.asList(c[3], c[3], c[3], c[3], c[15], c[16], c[17]);
		robotplayer1.setCards(cards1);
		robotplayer1.sparseCards();
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
	public void test11() {
		LinkedList<Hand> handHistory=new LinkedList<Hand>();
		robot = new RobotPlayer(null,null,handHistory);
		robot.setCards(new ArrayList<Card>(Arrays.asList(c[5],c[5],c[3])));
		System.out.println("test11"+robot.getPlayChoice());
		assertEquals(robot.getPlayChoice(), new String("3 "));
	}
	
	@Test // getPlayChoice
	public void getPlayChoice_empty_553() {
		LinkedList<Hand> handHistory=new LinkedList<Hand>();
		robot = new RobotPlayer(null,null,handHistory);
		robot.setCards(new ArrayList<Card>(Arrays.asList(c[5],c[5],c[3])));
		assertEquals(robot.getPlayChoice(), new String("3 "));
	}
	
	@Test
	public void test15() {
		LinkedList<Hand> handHistory=new LinkedList<Hand>();
		RobotPlayer robotplayer1=new RobotPlayer(null,null,handHistory);
		List<Card> cards1 = Arrays.asList( c[4], c[4], c[5], c[5], c[6], c[7], c[8], c[9], c[10]);
		robotplayer1.setCards(cards1);
		robotplayer1.sparseCards();
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
	public void test16() {
		LinkedList<Hand> handHistory=new LinkedList<Hand>();
		RobotPlayer robotplayer1=new RobotPlayer(null,null,handHistory);
		List<Card> cards1 = Arrays.asList( c[4], c[5], c[6], c[7], c[8], c[9], c[9], c[10], c[10]);
		robotplayer1.setCards(cards1);
		robotplayer1.sparseCards();
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
	public void test17() {
		LinkedList<Hand> handHistory=new LinkedList<Hand>();
		RobotPlayer robotplayer1=new RobotPlayer(null,null,handHistory);
		List<Card> cards1 = Arrays.asList( c[4],c[4],c[4],c[5], c[6], c[7], c[8], c[9], c[10], c[10], c[10]);
		robotplayer1.setCards(cards1);
		robotplayer1.sparseCards();
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
	public void test18() {
		LinkedList<Hand> handHistory=new LinkedList<Hand>();
		RobotPlayer robotplayer1=new RobotPlayer(null,null,handHistory);
		List<Card> cards1 = Arrays.asList(c[3], c[3], c[3], c[4], c[4], c[4], c[6], c[7], c[8], c[9], c[10]);
		robotplayer1.setCards(cards1);
		robotplayer1.sparseCards();
		List<Hand> exceptedHands=new ArrayList<Hand>();
		exceptedHands.add(Hand.cards2hand(new ArrayList<Card>(Arrays.asList(c[6],c[7],c[8],c[9],c[10]))));
		exceptedHands.add(Hand.cards2hand(new ArrayList<Card>(Arrays.asList(c[4],c[4],c[4],c[3],c[3],c[3]))));
		String result="";
		for(Hand hand: exceptedHands) {
			result+=hand.toString();
		}
		Assert.assertEquals(result,robotplayer1.getHandList());
	}
	@Test
	public void test19() {
		LinkedList<Hand> handHistory=new LinkedList<Hand>();
		RobotPlayer robotplayer1=new RobotPlayer(null,null,handHistory);
		List<Card> cards1 = Arrays.asList(c[3], c[3], c[4], c[4], c[5], c[5], c[6], c[7], c[8], c[9], c[10]);
		robotplayer1.setCards(cards1);
		robotplayer1.sparseCards();
		List<Hand> exceptedHands=new ArrayList<Hand>();
		exceptedHands.add(Hand.cards2hand(new ArrayList<Card>(Arrays.asList(c[6],c[7],c[8],c[9],c[10]))));
		exceptedHands.add(Hand.cards2hand(new ArrayList<Card>(Arrays.asList(c[3],c[3],c[4],c[4],c[5],c[5]))));
		String result="";
		for(Hand hand: exceptedHands) {
			result+=hand.toString();
		}
		Assert.assertEquals(result,robotplayer1.getHandList());
	}

	@Test
	public void test22() {
		LinkedList<Hand> handHistory=new LinkedList<Hand>();
		RobotPlayer robotplayer1=new RobotPlayer(null,null,handHistory);
		List<Card> cards1 = Arrays.asList(c[3], c[4], c[5], c[6], c[7], c[8],c[8] ,c[9], c[10], c[11], c[12] ,c[13], c[17]);
		robotplayer1.setCards(cards1);
		System.out.println(cards1);
		System.out.println(robotplayer1.decideRunForLandlord());
		robotplayer1.sparseCards();
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
	public void test23() {
		LinkedList<Hand> handHistory=new LinkedList<Hand>();
		RobotPlayer robotplayer1=new RobotPlayer(null,null,handHistory);
		List<Card> cards1 = Arrays.asList(c[4], c[5], c[6], c[7], c[8], c[9], c[9], c[10], c[10], c[11] ,c[11]);
		robotplayer1.setCards(cards1);
		robotplayer1.sparseCards();
		List<Hand> exceptedHands=new ArrayList<Hand>();
		exceptedHands.add(Hand.cards2hand(new ArrayList<Card>(Arrays.asList(c[4],c[5],c[6],c[7],c[8]))));
		exceptedHands.add(Hand.cards2hand(new ArrayList<Card>(Arrays.asList(c[11],c[11],c[10],c[9],c[9],c[10]))));
		String result="";
		for(Hand hand: exceptedHands) {
			result+=hand.toString();
		}
		Assert.assertEquals(result,robotplayer1.getHandList());
	}
}
