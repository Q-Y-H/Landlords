/*
package testCases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import entities.Card;
import entities.Hand;
import entities.HumanPlayer;
import entities.Player;
import enums.Rank;
import enums.Suit;

@TestMethodOrder(MethodOrderer.Alphanumeric.class)
public class testPlayer {
	private Card[] c;
	private Player player;
	
	@BeforeEach
	// initial 17 cards
	public void setUp() {
		c = new Card[18];
		player = new HumanPlayer("nick");
		for (int i = 3; i < 18; i++)
			c[i] = new Card(Rank.getRankByValue(i), Suit.BLANK);
		List<Card> cards = Arrays.asList(c[3],c[3],c[4],c[4],c[5],c[5],c[7],c[7],c[7],
				c[8],c[8],c[8],c[8],c[9],c[10],c[11],c[12]);
		player.setCards(cards);
	}
	
	@Test // checkCardsOnHand
	public void checkCardsOnHand_33() {
		ArrayList<String> input = new ArrayList<String>();
		input.add("3");
		input.add("3");
		List<Card> expected = Arrays.asList(c[3],c[3]);
		List<Card> actual = player.checkCardsOnHand(input);
		int i = 0;
		while(i < actual.size() && actual.get(i) == expected.get(i)) 
			++i;
		assertEquals(i, expected.size());
	}
	
	@Test // checkCardsOnHand
	public void checkCardsOnHand_333() {
		ArrayList<String> input = new ArrayList<String>();
		input.add("3");
		input.add("3");
		input.add("3");
		List<Card> actual = player.checkCardsOnHand(input);
		assertEquals(actual, null);
	}
	
	@Test // checkCardsOnHand
	public void checkCardsOnHand_8888() {
		ArrayList<String> input = new ArrayList<String>();
		input.add("8");
		input.add("8");
		input.add("8");
		input.add("8");
		List<Card> expected = Arrays.asList(c[8],c[8],c[8],c[8]);
		List<Card> actual = player.checkCardsOnHand(input);
		int i = 0;
		while(i < actual.size() && actual.get(i) == expected.get(i)) 
			++i;
		assertEquals(i, expected.size());
	}
	
	@Test // checkCardsOnHand
	public void checkCardsOnHand_78910J() {
		ArrayList<String> input = new ArrayList<String>();
		input.add("7");
		input.add("8");
		input.add("9");
		input.add("10");
		input.add("J");
		List<Card> expected = Arrays.asList(c[7],c[8],c[9],c[10],c[11]);
		List<Card> actual = player.checkCardsOnHand(input);
		int i = 0;
		while(i < actual.size() && actual.get(i) == expected.get(i)) 
			++i;
		assertEquals(i, expected.size());
	}
	
	@Test // checkCardsOnHand
	public void checkCardsOnHand_K() {
		ArrayList<String> input = new ArrayList<String>();
		input.add("K");
		List<Card> actual = player.checkCardsOnHand(input);
		assertEquals(actual, null);
	}
	
	@Test // checkCardsOnHand
	public void checkCardsOnHand_JJ() {
		ArrayList<String> input = new ArrayList<String>();
		input.add("J");
		input.add("J");
		List<Card> actual = player.checkCardsOnHand(input);
		assertEquals(actual, null);
	}
	
	@Test // checkCardsOnHand
	public void checkCardsOnHand_777Q() {
		ArrayList<String> input = new ArrayList<String>();
		input.add("7");
		input.add("7");
		input.add("7");
		input.add("Q");
		List<Card> expected = Arrays.asList(c[7],c[7],c[7],c[12]);
		List<Card> actual = player.checkCardsOnHand(input);
		int i = 0;
		while(i < actual.size() && actual.get(i) == expected.get(i)) 
			++i;
		assertEquals(i, expected.size());
	}
	
	@Test // checkCardsOnHand
	public void checkCardsOnHand_BR() {
		ArrayList<String> input = new ArrayList<String>();
		input.add("B");
		input.add("R");
		List<Card> actual = player.checkCardsOnHand(input);
		assertEquals(actual, null);
	}
	
	@Test // checkCardsOnHand
	public void checkCardsOnHand_456() {
		ArrayList<String> input = new ArrayList<String>();
		input.add("4");
		input.add("5");
		input.add("6");
		List<Card> actual = player.checkCardsOnHand(input);
		assertEquals(actual, null);
	}
	
	@Test // checkCardsOnHand
	public void checkCardsOnHand_777888() {
		ArrayList<String> input = new ArrayList<String>();
		input.add("7");
		input.add("7");
		input.add("7");
		input.add("8");
		input.add("8");
		input.add("8");
		List<Card> expected = Arrays.asList(c[7],c[7],c[7],c[8],c[8],c[8]);
		List<Card> actual = player.checkCardsOnHand(input);
		int i = 0;
		while(i < actual.size() && actual.get(i) == expected.get(i)) 
			++i;
		assertEquals(i, expected.size());
	}
	
	@Test // checkCardsOnHand
	public void checkCardsOnHand_345() {
		ArrayList<String> input = new ArrayList<String>();
		input.add("3");
		input.add("4");
		input.add("5");
		List<Card> expected = Arrays.asList(c[3],c[4],c[5]);
		List<Card> actual = player.checkCardsOnHand(input);
		int i = 0;
		while(i < actual.size() && actual.get(i) == expected.get(i)) 
			++i;
		assertEquals(i, expected.size());
	}
	
	@Test // checkCardsOnHand
	public void checkCardsOnHand_788() {
		ArrayList<String> input = new ArrayList<String>();
		input.add("7");
		input.add("8");
		input.add("8");
		List<Card> expected = Arrays.asList(c[7],c[8],c[8]);
		List<Card> actual = player.checkCardsOnHand(input);
		int i = 0;
		while(i < actual.size() && actual.get(i) == expected.get(i)) 
			++i;
		assertEquals(i, expected.size());
	}
	
	@Test // removeCards
	public void removeCards_4from3456() {
		List<Card> cards = new ArrayList<Card>(Arrays.asList(c[3],c[4],c[5],c[6]));
		player.setCards(cards);
		player.removeCards(Hand.cards2hand(new ArrayList<Card>(Arrays.asList(c[4]))));
		List<Card> expected = Arrays.asList(c[3],c[5],c[6]);
		List<Card> actual = player.getCards();
		int i = 0;
		while(i < actual.size() && actual.get(i) == expected.get(i)) 
			++i;
		assertEquals(i, expected.size());
	}
	
	@Test // setNickname
	public void setNickname_abc() {
		player.askForNickname();
		assertEquals(player.getNickname(), new String("abc"));
	}
	
	@Test // decideRunForLandlord()
	public void decideRunForLandlord_n() {
		assertEquals(player.decideRunForLandlord(), false);
	}
	
	@Test // decideRunForLandlord()
	public void decideRunForLandlord_y() {
		assertEquals(player.decideRunForLandlord(), true);
	}
	
	@AfterEach
	public void endUp() {
		System.setIn(System.in);
	}
}
*/

