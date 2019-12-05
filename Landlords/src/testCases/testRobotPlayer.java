package testCases;

import java.util.ArrayList;
import java.util.Arrays;
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
import helpers.Helper;
import junit.framework.TestCase;

public class testRobotPlayer extends TestCase{
	private Card[] c = new Card[20];

	@BeforeEach
	// initial 17 cards
	public void setUp() {
		for (int i = 3; i < 18; i++)
			c[i] = new Card(Rank.getRankByValue(i), Suit.BLANK);
			RobotPlayer testStubPlayer=new RobotPlayer("Robot Alex");
			testStubPlayer.askForNickname();
	}
	
	@Test
	public void test0() {	//No cards could match
		LinkedList<Hand> handHistory=new LinkedList<Hand>();
		handHistory.add(Hand.cards2hand(new ArrayList<Card>(Arrays.asList(c[3], c[3]))));
		RobotPlayer robotplayer1=new RobotPlayer(null,null,handHistory);
		robotplayer1.setCards(new ArrayList<Card>(Arrays.asList(c[5],c[4],c[3])));
		System.out.println(robotplayer1.getPlayChoice());
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
		LinkedList<Hand> handHistory=new LinkedList<Hand>();
		RobotPlayer robotplayer1=new RobotPlayer(null,null,handHistory);
		robotplayer1.setCards(new ArrayList<Card>(Arrays.asList(c[5],c[5],c[3])));
		System.out.println(robotplayer1.getPlayChoice());
	}
	
	@Test
	public void test4() {
		LinkedList<Hand> handHistory=new LinkedList<Hand>();
		RobotPlayer robotplayer1=new RobotPlayer(null,null,handHistory);
		robotplayer1.setCards(new ArrayList<Card>(Arrays.asList(c[5],c[5],c[3])));
		System.out.println(robotplayer1.getPlayChoice());
	}
	
	@Test
	public void test5() {
		LinkedList<Hand> handHistory=new LinkedList<Hand>();
		RobotPlayer robotplayer1=new RobotPlayer(null,null,handHistory);
		robotplayer1.setCards(new ArrayList<Card>(Arrays.asList(c[5],c[5])));
		System.out.println(robotplayer1.getPlayChoice());
	}
	
	@Test
	public void test6() {
		LinkedList<Hand> handHistory=new LinkedList<Hand>();
		handHistory.add(Hand.cards2hand(new ArrayList<Card>(Arrays.asList(c[3], c[3],c[3],c[3]))));
		RobotPlayer robotplayer1=new RobotPlayer(null,null,handHistory);
		robotplayer1.setCards(new ArrayList<Card>(Arrays.asList(c[5],c[5],c[4],c[4],c[4],c[4])));
		System.out.println(robotplayer1.getPlayChoice());
	}
	
	@Test
	public void test10() {
		LinkedList<Hand> handHistory=new LinkedList<Hand>();
		RobotPlayer robotplayer1=new RobotPlayer(null,null,handHistory);
		List<Card> cards1 = Arrays.asList(c[3], c[3], c[3], c[3], c[4], c[4], c[4], c[4], c[5], c[5], c[5], c[5], c[15], c[16], c[17]);
		robotplayer1.setCards(cards1);
		System.out.println(cards1);
		System.out.println(robotplayer1.decideRunForLandlord());
		robotplayer1.sparseCards();
		System.out.println(robotplayer1.getHandList());
	}
	@Test
	public void test11() {
		LinkedList<Hand> handHistory=new LinkedList<Hand>();
		RobotPlayer robotplayer1=new RobotPlayer(null,null,handHistory);
		List<Card> cards1 = Arrays.asList(c[3], c[3], c[3], c[4], c[4], c[4], c[5], c[5], c[5], c[7], c[13], c[16]);
		robotplayer1.setCards(cards1);
		System.out.println(cards1);
		robotplayer1.sparseCards();
		System.out.println(robotplayer1.getHandList());
	}
	@Test
	public void test12() {
		LinkedList<Hand> handHistory=new LinkedList<Hand>();
		RobotPlayer robotplayer1=new RobotPlayer(null,null,handHistory);
		List<Card> cards1 = Arrays.asList(c[3], c[3], c[4], c[5], c[6], c[7], c[17]);
		robotplayer1.setCards(cards1);
		System.out.println(cards1);
		robotplayer1.sparseCards();
		System.out.println(robotplayer1.getHandList());
	}
	
	@Test
	public void test14() {
		LinkedList<Hand> handHistory=new LinkedList<Hand>();
		RobotPlayer robotplayer1=new RobotPlayer(null,null,handHistory);
		List<Card> cards1 = Arrays.asList(c[3], c[4], c[5], c[6], c[7], c[7], c[8], c[9], c[10], c[11], c[12]);
		robotplayer1.setCards(cards1);
		System.out.println(cards1);
		System.out.println(robotplayer1.decideRunForLandlord());
		robotplayer1.sparseCards();
		System.out.println(robotplayer1.getHandList());
	}
	@Test
	public void test15() {
		LinkedList<Hand> handHistory=new LinkedList<Hand>();
		RobotPlayer robotplayer1=new RobotPlayer(null,null,handHistory);
		List<Card> cards1 = Arrays.asList(c[3], c[3], c[3], c[4], c[5], c[6], c[7], c[8], c[9],  c[10], c[10]);
		robotplayer1.setCards(cards1);
		System.out.println(cards1);
		System.out.println(robotplayer1.decideRunForLandlord());
		robotplayer1.sparseCards();
		System.out.println(robotplayer1.getHandList());
	}
	@Test
	public void test16() {
		LinkedList<Hand> handHistory=new LinkedList<Hand>();
		RobotPlayer robotplayer1=new RobotPlayer(null,null,handHistory);
		List<Card> cards1 = Arrays.asList(c[3], c[3], c[4], c[4], c[5], c[5], c[6], c[7], c[8], c[9], c[10], c[10], c[10]);
		robotplayer1.setCards(cards1);
		System.out.println(cards1);
		System.out.println(robotplayer1.decideRunForLandlord());
		robotplayer1.sparseCards();
		System.out.println(robotplayer1.getHandList());
	}
	@Test
	public void test17() {
		LinkedList<Hand> handHistory=new LinkedList<Hand>();
		RobotPlayer robotplayer1=new RobotPlayer(null,null,handHistory);
		List<Card> cards1 = Arrays.asList(c[3], c[4], c[5], c[5], c[6], c[7], c[8], c[9], c[10]);
		robotplayer1.setCards(cards1);
		System.out.println(cards1);
		System.out.println(robotplayer1.decideRunForLandlord());
		robotplayer1.sparseCards();
		System.out.println(robotplayer1.getHandList());
	}
	@Test
	public void test18() {
		LinkedList<Hand> handHistory=new LinkedList<Hand>();
		RobotPlayer robotplayer1=new RobotPlayer(null,null,handHistory);
		List<Card> cards1 = Arrays.asList(c[3],  c[4],  c[5],  c[6], c[7], c[8], c[9], c[10] ,c[15], c[15], c[16]);
		robotplayer1.setCards(cards1);
		System.out.println(cards1);
		System.out.println(robotplayer1.decideRunForLandlord());
		robotplayer1.sparseCards();
		System.out.println(robotplayer1.getHandList());
	}
	@Test
	public void test19() {
		LinkedList<Hand> handHistory=new LinkedList<Hand>();
		RobotPlayer robotplayer1=new RobotPlayer(null,null,handHistory);
		List<Card> cards1 = Arrays.asList(c[3], c[3], c[4], c[4], c[5], c[5], c[6], c[7], c[8], c[9], c[10]);
		robotplayer1.setCards(cards1);
		System.out.println(cards1);
		System.out.println(robotplayer1.decideRunForLandlord());
		robotplayer1.sparseCards();
		System.out.println(robotplayer1.getHandList());
	}
	@Test
	public void test20() {
		LinkedList<Hand> handHistory=new LinkedList<Hand>();
		RobotPlayer robotplayer1=new RobotPlayer(null,null,handHistory);
		List<Card> cards1 = Arrays.asList(c[6], c[7], c[8], c[9], c[10], c[10], c[10]);
		robotplayer1.setCards(cards1);
		System.out.println(cards1);
		System.out.println(robotplayer1.decideRunForLandlord());
		robotplayer1.sparseCards();
		System.out.println(robotplayer1.getHandList());
	}
	@Test
	public void test21() {
		LinkedList<Hand> handHistory=new LinkedList<Hand>();
		RobotPlayer robotplayer1=new RobotPlayer(null,null,handHistory);
		List<Card> cards1 = Arrays.asList(c[6], c[6], c[6], c[7], c[8], c[9], c[10]);
		robotplayer1.setCards(cards1);
		System.out.println(cards1);
		System.out.println(robotplayer1.decideRunForLandlord());
		robotplayer1.sparseCards();
		System.out.println(robotplayer1.getHandList());
	}
	@Test
	public void test22() {
		LinkedList<Hand> handHistory=new LinkedList<Hand>();
		RobotPlayer robotplayer1=new RobotPlayer(null,null,handHistory);
		List<Card> cards1 = Arrays.asList(c[3], c[3], c[4], c[4], c[5], c[5], c[6], c[7], c[7], c[8], c[9], c[10], c[11], c[11] ,c[13], c[17]);
		robotplayer1.setCards(cards1);
		System.out.println(cards1);
		System.out.println(robotplayer1.decideRunForLandlord());
		robotplayer1.sparseCards();
		System.out.println(robotplayer1.getHandList());
	}

	
	@Test
	public void test200() {
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
	public void test201() {
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
	public void test202() {
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
	public void test203() {
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
	public void test204() {
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
	public void test205() {
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
	public void test206() {
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
	public void test207() {
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
	public void test208() {
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
	public void test209() {
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
	public void test210() {
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
	public void test211() {
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
	public void test212() {
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
	public void test213() {
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
	public void test214() {
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
	public void test215() {
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
	public void test216() {
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
	public void test217() {
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
	public void test218() {
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
	public void test219() {
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
	public void test220() {
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
	public void test221() {
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
	public void test222() {
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
	public void test223() {
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
	public void test224() {
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
	public void test225() {
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
	public void test226() {
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
	public void test227() {
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
	public void test228() {
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
	public void test229() {
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
	public void test230() {
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
	public void test231() {
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
	public void test232() {
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
	public void test233() {
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
	public void test234() {
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
	public void test235() {
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
	public void test236() {
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
	public void test237() {
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
	public void test238() {
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
	public void test239() {
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
	public void test240() {
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
	public void test241() {
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
	public void test242() {
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
	public void test243() {
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
	public void test244() {
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
	public void test245() {
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
	public void test246() {
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
	public void test247() {
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
	public void test248() {
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
	public void test249() {
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


}
