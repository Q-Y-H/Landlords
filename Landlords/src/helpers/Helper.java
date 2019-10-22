package helpers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import entities.Card;
import entities.CardCase;

public class Helper {
	
	public static void sortPokers(List<Card> cards){
		Collections.sort(cards, Card.cardComparator);
	}
	
	public static void shuffle(List<Card> baseCards) {
		Collections.shuffle(baseCards);
	}
	
	public static List<List<Card>> cutCards(CardCase cardCase){
		List<List<Card>> cardGroups =new ArrayList<List<Card>>(4);
		shuffle(cardCase.getBaseCards());
		
		for (int i=0; i<3; i++) {
			cardGroups.add(new ArrayList<Card>(17));
		}
		
		for(int i=0; i<51; i++) {
			cardGroups.get(i%3).add(cardCase.getBaseCards().get(i));
		}
		
		List<Card> landloadCards = new ArrayList<Card>(3);
		landloadCards.addAll(cardCase.getBaseCards().subList(51, 54));
		cardGroups.add(landloadCards);
		
		return cardGroups;
	}

	public static void checkValidCards(ArrayList<String> cardNames) {
		// TODO Auto-generated method stub
		
	}
}
