package testCases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import entities.Card;
import entities.CardCase;
import entities.CardRoom;
import entities.Hand;
import entities.RobotPlayer;
import enums.Rank;
import enums.Suit;
import junit.framework.TestCase;

public class testRobot extends TestCase{
	private Card[] c = new Card[18];

	@BeforeEach
	// initial 17 cards
	public void setUp() {
		for (int i = 3; i < 18; i++)
			c[i] = new Card(Rank.getRankByValue(i), Suit.BLANK);
		RobotPlayer testStubPlayer=new RobotPlayer();
		testStubPlayer.askForNickname();
		RobotPlayer testStubPlayer2=new RobotPlayer("Bro");
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
	public void test3() {
		CardCase cardCase = new CardCase();
		Collections.shuffle(cardCase.getBaseCards());
		List<List<Card>> cardLists = Helper.cutCards(cardCase);
		LinkedList<Hand> handHistory=new LinkedList<Hand>();
		RobotPlayer robotplayer1=new RobotPlayer(null,null,handHistory);
		robotplayer1.setCards(cardLists.get(0));
		System.out.println(cardLists.get(0));
		System.out.println(robotplayer1.decideRunForLandlord());
		System.out.println(robotplayer1.getPlayChoice());
	}
	
	@Test
	public void test4() {
		CardCase cardCase = new CardCase();
		Helper.shuffleCards(cardCase);
		List<List<Card>> cardLists = Helper.cutCards(cardCase);
		LinkedList<Hand> handHistory=new LinkedList<Hand>();
		RobotPlayer robotplayer1=new RobotPlayer(null,null,handHistory);
		robotplayer1.setCards(cardLists.get(0));
		System.out.println(cardLists.get(0));
		System.out.println(robotplayer1.decideRunForLandlord());
		System.out.println(robotplayer1.getPlayChoice());
	}
	
	@Test
	public void test5() {
		CardCase cardCase = new CardCase();
		Helper.shuffleCards(cardCase);
		List<List<Card>> cardLists = Helper.cutCards(cardCase);
		LinkedList<Hand> handHistory=new LinkedList<Hand>();
		RobotPlayer robotplayer1=new RobotPlayer(null,null,handHistory);
		robotplayer1.setCards(cardLists.get(0));
		System.out.println(cardLists.get(0));
		System.out.println(robotplayer1.decideRunForLandlord());
		System.out.println(robotplayer1.getPlayChoice());
	}
	
	@Test
	public void test6() {
		CardCase cardCase = new CardCase();
		Helper.shuffleCards(cardCase);
		List<List<Card>> cardLists = Helper.cutCards(cardCase);
		LinkedList<Hand> handHistory=new LinkedList<Hand>();
		RobotPlayer robotplayer1=new RobotPlayer(null,null,handHistory);
		robotplayer1.setCards(cardLists.get(0));
		System.out.println(cardLists.get(0));
		System.out.println(robotplayer1.decideRunForLandlord());
		System.out.println(robotplayer1.getPlayChoice());
	}
	
	@Test
	public void test7() {
		CardCase cardCase = new CardCase();
		Helper.shuffleCards(cardCase);
		List<List<Card>> cardLists = Helper.cutCards(cardCase);
		LinkedList<Hand> handHistory=new LinkedList<Hand>();
		RobotPlayer robotplayer1=new RobotPlayer(null,null,handHistory);
		robotplayer1.setCards(cardLists.get(0));
		System.out.println(cardLists.get(0));
		System.out.println(robotplayer1.decideRunForLandlord());
		System.out.println(robotplayer1.getPlayChoice());
	}
	
	@Test
	public void test8() {
		CardCase cardCase = new CardCase();
		Helper.shuffleCards(cardCase);
		List<List<Card>> cardLists = Helper.cutCards(cardCase);
		LinkedList<Hand> handHistory=new LinkedList<Hand>();
		RobotPlayer robotplayer1=new RobotPlayer(null,null,handHistory);
		robotplayer1.setCards(cardLists.get(0));
		System.out.println(cardLists.get(0));
		System.out.println(robotplayer1.decideRunForLandlord());
		System.out.println(robotplayer1.getPlayChoice());
	}
	
	@Test
	public void test9() {
		CardCase cardCase = new CardCase();
		Helper.shuffleCards(cardCase);
		List<List<Card>> cardLists = Helper.cutCards(cardCase);
		LinkedList<Hand> handHistory=new LinkedList<Hand>();
		RobotPlayer robotplayer1=new RobotPlayer(null,null,handHistory);
		robotplayer1.setCards(cardLists.get(0));
		System.out.println(cardLists.get(0));
		System.out.println(robotplayer1.decideRunForLandlord());
		System.out.println(robotplayer1.getPlayChoice());
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
	
	//Test for landlord Selection
//	@Test
//	public void test100() {
//		CardRoom cardRoom=new CardRoom();
//		cardRoom.setup();
//		System.out.println(cardRoom.getPlayers().get(0).getCards());
//	}
}
