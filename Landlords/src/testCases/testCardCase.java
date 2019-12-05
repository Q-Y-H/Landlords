package testCases;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entities.Card;
import entities.CardCase;
import enums.Rank;
import enums.Suit;

public class testCardCase {
	private CardCase cc;
	private Card[] c;
	private List<Card> cards;
	
	@BeforeEach
	public void setUp() {
		cc = new CardCase();
		c =  new Card[18];
		for (int i = 3; i < 18; i++)
			c[i] = new Card(Rank.getRankByValue(i), Suit.BLANK);
	}
	
	@Test //sortCards
	public void sortCards_837() {
		cards = Arrays.asList(c[8],c[3],c[7]);
		List<Card> expected = Arrays.asList(c[3],c[7],c[8]);
		CardCase.sortCards(cards);
		int i = 0;
		while(i < expected.size() && expected.get(i) == cards.get(i))
			i++;
		assertEquals(i, expected.size());
	}
	
	@Test //sortCards
	public void sortCards_8B3710R() {
		cards = Arrays.asList(c[8],c[16],c[3],c[7],c[10],c[17]);
		List<Card> expected = Arrays.asList(c[3],c[7],c[8],c[10],c[16],c[17]);
		CardCase.sortCards(cards);
		int i = 0;
		while(i < expected.size() && expected.get(i) == cards.get(i))
			i++;
		assertEquals(i, expected.size());
	}
	
	@Test //sortCards
	public void sortCards_897456() {
		cards = Arrays.asList(c[8],c[9],c[7],c[4],c[5],c[6]);
		List<Card> expected = Arrays.asList(c[4],c[5],c[6],c[7],c[8],c[9]);
		CardCase.sortCards(cards);
		int i = 0;
		while(i < expected.size() && expected.get(i) == cards.get(i))
			i++;
		assertEquals(i, expected.size());
	}
	
	@Test //sortCards
	public void sortCards_A234() {
		cards = Arrays.asList(c[14],c[15],c[3],c[4]);
		List<Card> expected = Arrays.asList(c[3],c[4],c[14],c[15]);
		CardCase.sortCards(cards);
		int i = 0;
		while(i < expected.size() && expected.get(i) == cards.get(i))
			i++;
		assertEquals(i, expected.size());
	}
	
	@Test //sortCards
	public void sortCards_J3769() {
		cards = Arrays.asList(c[11],c[3],c[7],c[6],c[9]);
		List<Card> expected = Arrays.asList(c[3],c[6],c[7],c[9],c[11]);
		CardCase.sortCards(cards);
		int i = 0;
		while(i < expected.size() && expected.get(i) == cards.get(i))
			i++;
		assertEquals(i, expected.size());
	}
	
	@Test //sortCards
	public void sortCards_JQKJQK() {
		cards = Arrays.asList(c[11],c[12],c[13],c[11],c[12],c[13]);
		List<Card> expected = Arrays.asList(c[11],c[11],c[12],c[12],c[13],c[13]);
		CardCase.sortCards(cards);
		int i = 0;
		while(i < expected.size() && expected.get(i) == cards.get(i))
			i++;
		assertEquals(i, expected.size());
	}
	
	@Test //sortCards
	public void sortCards_A10KQJ() {
		cards = Arrays.asList(c[14],c[10],c[13],c[12],c[11]);
		List<Card> expected = Arrays.asList(c[10],c[11],c[12],c[13],c[14]);
		CardCase.sortCards(cards);
		int i = 0;
		while(i < expected.size() && expected.get(i) == cards.get(i))
			i++;
		assertEquals(i, expected.size());
	}
	
	@Test //sortCards
	public void sortCards_2RBA() {
		cards = Arrays.asList(c[15],c[16],c[17],c[14]);
		List<Card> expected = Arrays.asList(c[14],c[15],c[16],c[17]);
		CardCase.sortCards(cards);
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
		CardCase.sortCards(cards);
		int i = 0;
		while(i < expected.size() && expected.get(i) == cards.get(i))
			i++;
		assertEquals(i, expected.size());
	}
	
	@Test //sortCards
	public void sortCards_3456787() {
		cards = Arrays.asList(c[3],c[4],c[5],c[6],c[7],c[8],c[7]);
		List<Card> expected = Arrays.asList(c[3],c[4],c[5],c[6],c[7],c[7],c[8]);
		CardCase.sortCards(cards);
		int i = 0;
		while(i < expected.size() && expected.get(i) == cards.get(i))
			i++;
		assertEquals(i, expected.size());
	}
}
