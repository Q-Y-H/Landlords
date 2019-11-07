package helpers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import entities.Card;
import entities.CardCase;
import entities.Hand;
import entities.Player;
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
	
	public static List<Card> hintCards(List<Card> cards, List<Card> selectCards, Hand prev,int startPoint, int length){
		if( cards.size()-startPoint<length)
			return null;
		if(length==0)
			return null;
		List<Card> TempCards=new ArrayList<Card>();
		
		if(selectCards!=null&& prev.compareTo(Hand.cards2hand(selectCards))==-1) {
			return selectCards;
		}
		
		for(int i=startPoint;i<cards.size();i++){
			TempCards.add(cards.get(i));
			selectCards=hintCards(cards, TempCards, prev, i+1, length-1);
			if(selectCards!=null) {
				return selectCards;
			}
			TempCards.remove(TempCards.size()-1);
		}
		return null;
		
	}
	
	public static void clearInputStream() {
		Scanner in = new Scanner(System.in);
		in.nextLine();
	}	
}
