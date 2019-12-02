package testCases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import entities.Card;
import entities.Hand;
import entities.RobotPlayer;
import enums.Rank;
import enums.Suit;
import junit.framework.TestCase;

public class testRobot extends TestCase{
	private Card[] c = new Card[20];

	@BeforeEach
	// initial 17 cards
	public void setUp() {
		for (int i = 3; i < 18; i++)
			c[i] = new Card(Rank.getRankByValue(i), Suit.BLANK);
	}
	
	@Test
	public void test1() {
		LinkedList<Hand> handHistory=new LinkedList<Hand>();
		handHistory.add(Hand.cards2hand(new ArrayList<Card>(Arrays.asList(c[3], c[3]))));
		RobotPlayer robotplayer1=new RobotPlayer(null,null,handHistory);
		robotplayer1.setCards(new ArrayList<Card>(Arrays.asList(c[5],c[5],c[3])));
		robotplayer1.getPlayChoice();
	}
	@Test
	public void test2() {
		LinkedList<Hand> handHistory=new LinkedList<Hand>();
		handHistory.add(Hand.cards2hand(new ArrayList<Card>(Arrays.asList(c[3], c[3]))));
		RobotPlayer robotplayer1=new RobotPlayer(null,null,handHistory);
		robotplayer1.setCards(new ArrayList<Card>(Arrays.asList(c[5],c[5],c[3])));
		robotplayer1.getPlayChoice();
	}
	
	// Test for SparseCard
//	@Test
//	public void test3() {
//		LinkedList<Hand> handHistory=new LinkedList<Hand>();
//		RobotPlayer robotplayer1=new RobotPlayer(null,null,handHistory);
//		robotplayer1.setCards(new ArrayList<Card>(Arrays.asList(c[5],c[5],c[3],c[2])));
//		robotplayer1.sparseCards();
//		System.out.println(robotplayer1.getSparsedList());
//	}
}
