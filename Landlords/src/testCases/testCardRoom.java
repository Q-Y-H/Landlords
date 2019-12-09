package testCases;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
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
	private Method method = null;
	
	@BeforeEach
	public void setUp() {
		String input = new String("pass\n" + "a\n" + "b\n" + "c\n" +
		
				"pass\n" + "n\n" + "y\n" + "abc\n" + "Foo\n" +
				"n\n" + "n\n" + "y\n" + "n\n" + "y\n" + "n\n" + 
				"n\n" + "y\n" + "y\n" + "y\n" + "y\n" + "n\n" + "y\n" + "n\n" +
				"abc\n" + "n\n" + "y\n" +
				"pass\n" + "abc\n");
		ByteArrayInputStream bais = new ByteArrayInputStream(input.getBytes());
		System.setIn(bais);
		// The binding to System.in is restored in testPlay.java
		
		cr = new CardRoom();
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
		List<Card> expected=CardRoom.hintCards(actul,Hand.cards2hand(cards));
		
		assertEquals(Hand.cards2hand(cards).isSmallerThan(Hand.cards2hand(expected)),true);
	}
	@Test // hintCards
	public void hintCards_34567_5789JQKAAAAB() {
		cards = Arrays.asList(c[3],c[4],c[5],c[6],c[7]);
		List<Card> actul = Arrays.asList(c[5],c[7],c[8],c[9],c[11],c[12],c[13],c[14],c[14],c[14],c[14],c[16]);
		List<Card> expected=CardRoom.hintCards(actul,Hand.cards2hand(cards));
		
		assertEquals(Hand.cards2hand(cards).isSmallerThan(Hand.cards2hand(expected)),true);
	}
	@Test // hintCards
	public void hintCards_34567_5789JQKBR() {
		cards = Arrays.asList(c[3],c[4],c[5],c[6],c[7]);
		List<Card> actul = Arrays.asList(c[5],c[7],c[8],c[9],c[11],c[12],c[13],c[16],c[17]);
		List<Card> expected=CardRoom.hintCards(actul,Hand.cards2hand(cards));
		
		assertEquals(Hand.cards2hand(cards).isSmallerThan(Hand.cards2hand(expected)),true);
	}
	@Test // hintCards
	public void hintCards_34567_5789XQKR() {
		cards = Arrays.asList(c[3],c[4],c[5],c[6],c[7]);
		List<Card> actul = Arrays.asList(c[5],c[7],c[8],c[9],c[10],c[12],c[13],c[17]);
		List<Card> expected=CardRoom.hintCards(actul,Hand.cards2hand(cards));
		
		assertEquals(Hand.cards2hand(cards).isSmallerThan(Hand.cards2hand(expected)),false);
	}
	@Test //sortCards
	public void sortCards_837() {
		cards = Arrays.asList(c[8],c[3],c[7]);
		List<Card> expected = Arrays.asList(c[3],c[7],c[8]);
		CardRoom.sortCards(cards);
		int i = 0;
		while(i < expected.size() && expected.get(i) == cards.get(i))
			i++;
		assertEquals(i, expected.size());
	}
	
	@Test //sortCards
	public void sortCards_8B3710R() {
		cards = Arrays.asList(c[8],c[16],c[3],c[7],c[10],c[17]);
		List<Card> expected = Arrays.asList(c[3],c[7],c[8],c[10],c[16],c[17]);
		CardRoom.sortCards(cards);
		int i = 0;
		while(i < expected.size() && expected.get(i) == cards.get(i))
			i++;
		assertEquals(i, expected.size());
	}
	
	@Test //sortCards
	public void sortCards_897456() {
		cards = Arrays.asList(c[8],c[9],c[7],c[4],c[5],c[6]);
		List<Card> expected = Arrays.asList(c[4],c[5],c[6],c[7],c[8],c[9]);
		CardRoom.sortCards(cards);
		int i = 0;
		while(i < expected.size() && expected.get(i) == cards.get(i))
			i++;
		assertEquals(i, expected.size());
	}
	
	@Test //sortCards
	public void sortCards_A234() {
		cards = Arrays.asList(c[14],c[15],c[3],c[4]);
		List<Card> expected = Arrays.asList(c[3],c[4],c[14],c[15]);
		CardRoom.sortCards(cards);
		int i = 0;
		while(i < expected.size() && expected.get(i) == cards.get(i))
			i++;
		assertEquals(i, expected.size());
	}
	
	@Test //sortCards
	public void sortCards_J3769() {
		cards = Arrays.asList(c[11],c[3],c[7],c[6],c[9]);
		List<Card> expected = Arrays.asList(c[3],c[6],c[7],c[9],c[11]);
		CardRoom.sortCards(cards);
		int i = 0;
		while(i < expected.size() && expected.get(i) == cards.get(i))
			i++;
		assertEquals(i, expected.size());
	}
	
	@Test //sortCards
	public void sortCards_JQKJQK() {
		cards = Arrays.asList(c[11],c[12],c[13],c[11],c[12],c[13]);
		List<Card> expected = Arrays.asList(c[11],c[11],c[12],c[12],c[13],c[13]);
		CardRoom.sortCards(cards);
		int i = 0;
		while(i < expected.size() && expected.get(i) == cards.get(i))
			i++;
		assertEquals(i, expected.size());
	}
	
	@Test //sortCards
	public void sortCards_A10KQJ() {
		cards = Arrays.asList(c[14],c[10],c[13],c[12],c[11]);
		List<Card> expected = Arrays.asList(c[10],c[11],c[12],c[13],c[14]);
		CardRoom.sortCards(cards);
		int i = 0;
		while(i < expected.size() && expected.get(i) == cards.get(i))
			i++;
		assertEquals(i, expected.size());
	}
	
	@Test //sortCards
	public void sortCards_2RBA() {
		cards = Arrays.asList(c[15],c[16],c[17],c[14]);
		List<Card> expected = Arrays.asList(c[14],c[15],c[16],c[17]);
		CardRoom.sortCards(cards);
		int i = 0;
		while(i < expected.size() && expected.get(i) == cards.get(i))
			i++;
		assertEquals(i, expected.size());
	}
	
	@Test //sortCards
	public void sortCards_KQJ1098765432A() {
		cards = Arrays.asList(c[13],c[12],c[11],c[10],c[9],c[8],c[7],c[6],
				c[5],c[4],c[3],c[15],c[14]);
		List<Card> expected = Arrays.asList(c[3],c[4],c[5],c[6],c[7],c[8],c[9],
				c[10],c[11],c[12],c[13],c[14],c[15]);
		CardRoom.sortCards(cards);
		int i = 0;
		while(i < expected.size() && expected.get(i) == cards.get(i))
			i++;
		assertEquals(i, expected.size());
	}
	
	@Test //sortCards
	public void sortCards_3456787() {
		cards = Arrays.asList(c[3],c[4],c[5],c[6],c[7],c[8],c[7]);
		List<Card> expected = Arrays.asList(c[3],c[4],c[5],c[6],c[7],c[7],c[8]);
		CardRoom.sortCards(cards);
		int i = 0;
		while(i < expected.size() && expected.get(i) == cards.get(i))
			i++;
		assertEquals(i, expected.size());
	}
	
	@Test // isValidInputCardNames
	public void isValidInputCardNames_789() {		
		ArrayList<String> cardnames = new ArrayList<String>();
		cardnames.add("7");
		cardnames.add("8");
		cardnames.add("9");
		
		try {
			method = CardRoom.class.getDeclaredMethod("isValidInputCardNames", ArrayList.class);
			method.setAccessible(true);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		
		try {
			assertEquals(method.invoke(cr, cardnames), true);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	@Test // isValidInputCardNames
	public void isValidInputCardNames_t() {
		ArrayList<String> cardnames = new ArrayList<String>();
		cardnames.add("t");
		
		try {
			method = CardRoom.class.getDeclaredMethod("isValidInputCardNames", ArrayList.class);
			method.setAccessible(true);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		
		try {
			assertEquals(method.invoke(cr, cardnames), false);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	@Test // isValidInputCardNames
	public void isValidInputCardNames_xa() {
		ArrayList<String> cardnames = new ArrayList<String>();
		cardnames.add("x");
		cardnames.add("a");
		
		try {
			method = CardRoom.class.getDeclaredMethod("isValidInputCardNames", ArrayList.class);
			method.setAccessible(true);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		
		try {
			assertEquals(method.invoke(cr, cardnames), true);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	@Test // isValidInputCardNames
	public void isValidInputCardNames_jQk() {
		ArrayList<String> cardnames = new ArrayList<String>();
		cardnames.add("j");
		cardnames.add("Q");
		cardnames.add("k");
		
		try {
			method = CardRoom.class.getDeclaredMethod("isValidInputCardNames", ArrayList.class);
			method.setAccessible(true);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		
		try {
			assertEquals(method.invoke(cr, cardnames), true);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	@Test // askForPlayChoice
	public void askForPlayChoice_0_pass(){
		cr.setup();
		assertEquals(cr.askForPlayChoice(0), new String("pass"));
	}
	
	@Test // askForNickNames
	public void askForNickname_a_b_c() {
		CardRoom crPVP = new CardRoom();
		crPVP.setType(RoomType.PVP);
		crPVP.setup();
		
		try {
			method = CardRoom.class.getDeclaredMethod("askForNicknames", null);
			method.setAccessible(true);
		} catch (NoSuchMethodException e1) {
			e1.printStackTrace();
		} catch (SecurityException e1) {
			e1.printStackTrace();
		}
	
		try {
			method.invoke(crPVP, null);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		assertEquals(crPVP.getPlayers().get(0).getNickname(), new String("a"));
		assertEquals(crPVP.getPlayers().get(1).getNickname(), new String("b"));
		assertEquals(crPVP.getPlayers().get(2).getNickname(), new String("c"));
	}
	
	@Test // processPlayChoice
	public void processPlayChoice_multiple() {
		CardRoom cr = new CardRoom();
		cr.setType(RoomType.PVP);
		cr.setup();
		
		assertEquals(cr.processPlayerChoice(0, "pass"), false);
		cards = new ArrayList<Card>();
		cards.add(c[3]);
		cards.add(c[3]);
		cards.add(c[4]);
		cr.getPlayers().get(0).setCards(cards);
		assertEquals(cr.processPlayerChoice(0, ""), false);
		assertEquals(cr.processPlayerChoice(0, "5"), false);
		assertEquals(cr.processPlayerChoice(0, "3 4"), false);
		assertEquals(cr.processPlayerChoice(0, "34"), false);
		assertEquals(cr.processPlayerChoice(0, "3 3"), true);
		cr.setLastHandPlayer(0);
		assertEquals(cr.processPlayerChoice(0, "pass"), false);
		cr.setLastHandPlayer(1);
		assertEquals(cr.processPlayerChoice(0, "pass"), true);
	}

}
