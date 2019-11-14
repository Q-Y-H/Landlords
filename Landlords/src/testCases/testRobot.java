package testCases;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import entities.Card;
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
		RobotPlayer robotplayer1=new RobotPlayer();
		robotplayer1.setCards(Arrays.asList(c[5],c[5]));
		List<Card> cards = Arrays.asList(c[3], c[3]);
		System.out.println(robotplayer1.getPlayChoice(cards));
	}
}
