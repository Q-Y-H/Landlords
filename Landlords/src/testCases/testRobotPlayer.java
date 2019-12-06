package testCases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entities.Card;
import entities.Hand;
import entities.RobotPlayer;
import enums.Rank;
import enums.Suit;

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
	
	@Test // getPlayChoice
	public void getPlayChoice_33_553() {
		LinkedList<Hand> handHistory=new LinkedList<Hand>();
		handHistory.add(Hand.cards2hand(new ArrayList<Card>(Arrays.asList(c[3], c[3]))));
		robot = new RobotPlayer(null,null,handHistory);
		robot.setCards(new ArrayList<Card>(Arrays.asList(c[5],c[5],c[3])));
		assertEquals(robot.getPlayChoice(), new String("5 5 "));
	}
	
	@Test // getPlayChoice
	public void getPlayChoice_empty_553() {
		LinkedList<Hand> handHistory=new LinkedList<Hand>();
		robot = new RobotPlayer(null,null,handHistory);
		robot.setCards(new ArrayList<Card>(Arrays.asList(c[5],c[5],c[3])));
		assertEquals(robot.getPlayChoice(), new String("3 "));
	}
	
	@Test // askForNickame
	public void askForNickname_0() {
		robot = new RobotPlayer();
		// This is the fourth Player ever instantiated
		robot.askForNickname();
		assertEquals(robot.getNickname(), new String("Robot 0"));
	}
}
