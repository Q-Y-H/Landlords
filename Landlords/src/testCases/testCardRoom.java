package testCases;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entities.Card;
import entities.CardRoom;
import entities.Hand;
import enums.Rank;
import enums.RoomType;
import enums.Suit;

public class testCardRoom {
	private CardRoom cr;
	private Card[] c;
	private List<Card> cards;
	
	@BeforeEach
	public void setUp() {
		c = new Card[18];
		for (int i = 3; i < 18; i++)
			c[i] = new Card(Rank.getRankByValue(i), Suit.BLANK);
	}
	
	@Test // test setup()
	public void setup_PVP(){
		CardRoom cr = new CardRoom();
		cr.setType(RoomType.PVP);
		cr.setup();
		assertEquals(cr.getPlayers().size(), 3);
		assertEquals(cr.getPlayers().get(0).getCards().size(), 17);
		assertEquals(cr.getPlayers().get(1).getCards().size(), 17);
		assertEquals(cr.getPlayers().get(2).getCards().size(), 17);
	}
	
	@Test // test setup()
	public void setup_PVE(){
		CardRoom cr = new CardRoom();
		cr.setType(RoomType.PVE);
		cr.setup();
		assertEquals(cr.getPlayers().size(), 3);
		assertEquals(cr.getPlayers().get(0).getCards().size(), 17);
		assertEquals(cr.getPlayers().get(1).getCards().size(), 17);
		assertEquals(cr.getPlayers().get(2).getCards().size(), 17);
	}
	
	@Test //cutCards
	public void cutCards() {
		cr = new CardRoom();
		cr.shuffleCards();
		List<List<Card>> cardGroups = cr.cutCards();
		assertEquals(cardGroups.size(),4);
		assertEquals(cardGroups.get(0).size(),17);
		assertEquals(cardGroups.get(1).size(),17);
		assertEquals(cardGroups.get(2).size(),17);
		assertEquals(cardGroups.get(3).size(),3);
	}
	
	@Test // hintCards
	public void hintCards_34567_5789XJQKAAA() {
		cards = Arrays.asList(c[3],c[4],c[5],c[6],c[7]);
		List<Card> actul = Arrays.asList(c[5],c[7],c[8],c[9],c[10],c[11],c[12],c[13],c[14],c[14],c[14]);
		List<Card> expected=CardRoom.hintCards(actul,Hand.cards2hand(cards),5);
		
		assertEquals(Hand.cards2hand(cards).isSmallerThan(Hand.cards2hand(expected)),true);
	}
	@Test // hintCards
	public void hintCards_34567_5789JQKAAAAB() {
		cards = Arrays.asList(c[3],c[4],c[5],c[6],c[7]);
		List<Card> actul = Arrays.asList(c[5],c[7],c[8],c[9],c[11],c[12],c[13],c[14],c[14],c[14],c[14],c[16]);
		List<Card> expected=CardRoom.hintCards(actul,Hand.cards2hand(cards),5);
		
		assertEquals(Hand.cards2hand(cards).isSmallerThan(Hand.cards2hand(expected)),true);
	}
	@Test // hintCards
	public void hintCards_34567_5789JQKBR() {
		cards = Arrays.asList(c[3],c[4],c[5],c[6],c[7]);
		List<Card> actul = Arrays.asList(c[5],c[7],c[8],c[9],c[11],c[12],c[13],c[16],c[17]);
		List<Card> expected=CardRoom.hintCards(actul,Hand.cards2hand(cards),5);
		
		assertEquals(Hand.cards2hand(cards).isSmallerThan(Hand.cards2hand(expected)),true);
	}
	@Test // hintCards
	public void hintCards_34567_5789XQKR() {
		cards = Arrays.asList(c[3],c[4],c[5],c[6],c[7]);
		List<Card> actul = Arrays.asList(c[5],c[7],c[8],c[9],c[10],c[12],c[13],c[17]);
		List<Card> expected=CardRoom.hintCards(actul,Hand.cards2hand(cards),5);
		
		assertEquals(Hand.cards2hand(cards).isSmallerThan(Hand.cards2hand(expected)),false);
	}
}
