package testCases;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import entities.Card;
import entities.CardCase;
import entities.Hand;
import enums.Rank;
import enums.Suit;
import helpers.Helper;

public class testHelper {
	private List<Card> cards;
	private Card[] all;
	private CardCase cc;

	@BeforeEach
	public void setUp() {
		all = new Card[18];
		for (int i = 3; i < 18; i++)
			all[i] = new Card(Rank.getRankByValue(i), Suit.BLANK);
		cc = new CardCase();
	}

	@Test // sortCards
	public void sortCards_837() {
		cards = Arrays.asList(all[8], all[3], all[7]);
		List<Card> expected = Arrays.asList(all[3], all[7], all[8]);
		Helper.sortCards(cards);
		int i = 0;
		while (i < expected.size() && expected.get(i) == cards.get(i))
			i++;
		assertEquals(i, expected.size());
	}

	@Test // sortCards
	public void sortCards_8B3710R() {
		cards = Arrays.asList(all[8], all[16], all[3], all[7], all[10], all[17]);
		List<Card> expected = Arrays.asList(all[3], all[7], all[8], all[10], all[16], all[17]);
		Helper.sortCards(cards);
		int i = 0;
		while (i < expected.size() && expected.get(i) == cards.get(i))
			i++;
		assertEquals(i, expected.size());
	}

	@Test // sortCards
	public void sortCards_897456() {
		cards = Arrays.asList(all[8], all[9], all[7], all[4], all[5], all[6]);
		List<Card> expected = Arrays.asList(all[4], all[5], all[6], all[7], all[8], all[9]);
		Helper.sortCards(cards);
		int i = 0;
		while (i < expected.size() && expected.get(i) == cards.get(i))
			i++;
		assertEquals(i, expected.size());
	}

	@Test // sortCards
	public void sortCards_A234() {
		cards = Arrays.asList(all[14], all[15], all[3], all[4]);
		List<Card> expected = Arrays.asList(all[3], all[4], all[14], all[15]);
		Helper.sortCards(cards);
		int i = 0;
		while (i < expected.size() && expected.get(i) == cards.get(i))
			i++;
		assertEquals(i, expected.size());
	}

	@Test // sortCards
	public void sortCards_J3769() {
		cards = Arrays.asList(all[11], all[3], all[7], all[6], all[9]);
		List<Card> expected = Arrays.asList(all[3], all[6], all[7], all[9], all[11]);
		Helper.sortCards(cards);
		int i = 0;
		while (i < expected.size() && expected.get(i) == cards.get(i))
			i++;
		assertEquals(i, expected.size());
	}

	@Test // sortCards
	public void sortCards_JQKJQK() {
		cards = Arrays.asList(all[11], all[12], all[13], all[11], all[12], all[13]);
		List<Card> expected = Arrays.asList(all[11], all[11], all[12], all[12], all[13], all[13]);
		Helper.sortCards(cards);
		int i = 0;
		while (i < expected.size() && expected.get(i) == cards.get(i))
			i++;
		assertEquals(i, expected.size());
	}

	@Test // sortCards
	public void sortCards_A10KQJ() {
		cards = Arrays.asList(all[14], all[10], all[13], all[12], all[11]);
		List<Card> expected = Arrays.asList(all[10], all[11], all[12], all[13], all[14]);
		Helper.sortCards(cards);
		int i = 0;
		while (i < expected.size() && expected.get(i) == cards.get(i))
			i++;
		assertEquals(i, expected.size());
	}

	@Test // sortCards
	public void sortCards_2RBA() {
		cards = Arrays.asList(all[15], all[16], all[17], all[14]);
		List<Card> expected = Arrays.asList(all[14], all[15], all[16], all[17]);
		Helper.sortCards(cards);
		int i = 0;
		while (i < expected.size() && expected.get(i) == cards.get(i))
			i++;
		assertEquals(i, expected.size());
	}

	@Test // sortCards
	public void sortCards_KQJ1098765432A() {
		cards = Arrays.asList(all[13], all[12], all[11], all[10], all[9], all[8], all[7], all[6], all[5], all[4],
				all[3], all[15], all[14]);
		List<Card> expected = Arrays.asList(all[3], all[4], all[5], all[6], all[7], all[8], all[9], all[10], all[11],
				all[12], all[13], all[14], all[15]);
		Helper.sortCards(cards);
		int i = 0;
		while (i < expected.size() && expected.get(i) == cards.get(i))
			i++;
		assertEquals(i, expected.size());
	}

	@Test // sortCards
	public void sortCards_3456787() {
		cards = Arrays.asList(all[3], all[4], all[5], all[6], all[7], all[8], all[7]);
		List<Card> expected = Arrays.asList(all[3], all[4], all[5], all[6], all[7], all[7], all[8]);
		Helper.sortCards(cards);
		int i = 0;
		while (i < expected.size() && expected.get(i) == cards.get(i))
			i++;
		assertEquals(i, expected.size());
	}

	@Test // cutCards
	public void cutCards() {
		Helper.shuffleCards(cc);
		List<List<Card>> cardGroups = Helper.cutCards(cc);
		assertEquals(cardGroups.size(), 4);
		assertEquals(cardGroups.get(0).size(), 17);
		assertEquals(cardGroups.get(1).size(), 17);
		assertEquals(cardGroups.get(2).size(), 17);
		assertEquals(cardGroups.get(3).size(), 3);
	}

	@Test // isValidInputCardNames
	public void isValidInputCardNames_789() {
		ArrayList<String> cardnames = new ArrayList<String>();
		cardnames.add("7");
		cardnames.add("8");
		cardnames.add("9");

		assertEquals(Helper.isValidInputCardNames(cardnames), true);
	}

	@Test // isValidInputCardNames
	public void isValidInputCardNames_t() {
		ArrayList<String> cardnames = new ArrayList<String>();
		cardnames.add("t");

		assertEquals(Helper.isValidInputCardNames(cardnames), false);
	}

	@Test // isValidInputCardNames
	public void isValidInputCardNames_xa() {
		ArrayList<String> cardnames = new ArrayList<String>();
		cardnames.add("x");
		cardnames.add("a");

		assertEquals(Helper.isValidInputCardNames(cardnames), true);
	}

	@Test // isValidInputCardNames
	public void isValidInputCardNames_jQk() {
		ArrayList<String> cardnames = new ArrayList<String>();
		cardnames.add("j");
		cardnames.add("Q");
		cardnames.add("k");

		assertEquals(Helper.isValidInputCardNames(cardnames), true);
	}

	@Test // hintCards
	public void hintCards_34567_5789XJQKAAA() {
		cards = Arrays.asList(all[3], all[4], all[5], all[6], all[7]);
		List<Card> actul = Arrays.asList(all[5], all[7], all[8], all[9], all[10], all[11], all[12], all[13], all[14],
				all[14], all[14]);
		List<Card> excepted = Helper.hintCards(actul, Hand.cards2hand(cards), 5);

		assertEquals(Hand.cards2hand(cards).isSmallerThan(Hand.cards2hand(excepted)), true);
	}

	@Test // hintCards
	public void hintCards_34567_5789JQKAAAAB() {
		cards = Arrays.asList(all[3], all[4], all[5], all[6], all[7]);
		List<Card> actul = Arrays.asList(all[5], all[7], all[8], all[9], all[11], all[12], all[13], all[14], all[14],
				all[14], all[14], all[16]);
		List<Card> excepted = Helper.hintCards(actul, Hand.cards2hand(cards), 5);

		assertEquals(Hand.cards2hand(cards).isSmallerThan(Hand.cards2hand(excepted)), true);
	}

	@Test // hintCards
	public void hintCards_34567_5789JQKBR() {
		cards = Arrays.asList(all[3], all[4], all[5], all[6], all[7]);
		List<Card> actul = Arrays.asList(all[5], all[7], all[8], all[9], all[11], all[12], all[13], all[16], all[17]);
		List<Card> excepted = Helper.hintCards(actul, Hand.cards2hand(cards), 5);

		assertEquals(Hand.cards2hand(cards).isSmallerThan(Hand.cards2hand(excepted)), true);
	}

	@Test // hintCards
	public void hintCards_34567_5789XQKR() {
		cards = Arrays.asList(all[3], all[4], all[5], all[6], all[7]);
		List<Card> actul = Arrays.asList(all[5], all[7], all[8], all[9], all[10], all[12], all[13], all[17]);
		List<Card> excepted = Helper.hintCards(actul, Hand.cards2hand(cards), 5);

		assertEquals(Hand.cards2hand(cards).isSmallerThan(Hand.cards2hand(excepted)), false);
	}
}
