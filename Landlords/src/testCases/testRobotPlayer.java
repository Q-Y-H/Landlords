package testCases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entities.Card;
import entities.CardRoom;
import entities.Hand;
import entities.RobotPlayer;
import enums.Rank;
import enums.RoomType;
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
		robot = new RobotPlayer();
	}
	
	@Test //test askForNickame
	public void askForNickname_1() {
		robot.askForNickname();
		assertEquals(robot.getNickname(), new String("Robot 0"));
	}
	
	@Test
	public void test1() {
		LinkedList<Hand> handHistory=new LinkedList<Hand>();
		handHistory.add(Hand.cards2hand(new ArrayList<Card>(Arrays.asList(c[3], c[3]))));
		RobotPlayer robotplayer1=new RobotPlayer(null,null,handHistory);
		robotplayer1.setCards(new ArrayList<Card>(Arrays.asList(c[5],c[5],c[3])));
		System.out.println(robotplayer1.getPlayChoice());
	}
	
	@Test
	public void test2() {
		LinkedList<Hand> handHistory=new LinkedList<Hand>();
		RobotPlayer robotplayer1=new RobotPlayer(null,null,handHistory);
		robotplayer1.setCards(new ArrayList<Card>(Arrays.asList(c[5],c[5],c[3])));
		System.out.println(robotplayer1.getPlayChoice());
	}
	
	@Test
	public void test100() {
		CardRoom cardRoom=new CardRoom();
		cardRoom.setType(RoomType.PVE);
		cardRoom.setup();
		System.out.println(cardRoom.getPlayers().get(0).getCards());
	}
}
