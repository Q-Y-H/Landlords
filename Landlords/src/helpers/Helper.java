package helpers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import entities.Card;
import entities.CardCase;
import enums.Rank;

public class Helper {

	public static void sortCards(List<Card> cards) {
		Collections.sort(cards, Card.cardComparator);
	}

	public static void shuffleCards(CardCase cardCase) {
		Collections.shuffle(cardCase.getBaseCards());
	}

	public static List<List<Card>> cutCards(CardCase cardCase) {
		List<List<Card>> cardGroups = new ArrayList<List<Card>>(4);

		for (int i = 0; i < 3; i++) {
			cardGroups.add(new ArrayList<Card>(17));
		}

		for (int i = 0; i < 51; i++) {
			cardGroups.get(i % 3).add(cardCase.getBaseCards().get(i));
		}

		List<Card> landloadCards = new ArrayList<Card>(3);
		landloadCards.addAll(cardCase.getBaseCards().subList(51, 54));
		cardGroups.add(landloadCards);

		return cardGroups;
	}

	public static boolean isValidInputCardNames(ArrayList<String> cardNames) {
		for (String cardName : cardNames) {
			if (!Rank.aliasContains(cardName))
				return false;
		}
		return true;
	}
}
