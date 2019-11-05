
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
import enums.HandType;
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
	//穷尽后比较，输出满足条件的第一个List.(单牌对子ok,三带一输出为null，怀疑是list至hand转换出现问题）
	//need to be modified. ^_^
	public static List<Card> hintCards(List<Card> cards, Hand prev, int length){

		List<Card> TempCards=new ArrayList<Card>();
	    List<List<Card>> workspace =new ArrayList<List<Card>>();
	    combinationSelect(workspace,cards,TempCards,length);
		for(List<Card> c: workspace) {
			Hand tempHand=Hand.cards2hand(c);
			if(prev.compareTo(tempHand)<0) {
				return c;
			}
		}
		return null;
	}

	private static void combinationSelect(List<List<Card>> workspace,List<Card> dataList, List<Card> resultList, int length) {
		List<Card> copyData;
		List<Card> copyResult;

		if(resultList.size() == length) {
			workspace.add(resultList);
		}

		for(int i = 0; i < dataList.size(); i++) {
			copyData = new ArrayList<Card>(dataList);
			copyResult = new ArrayList<Card>(resultList);

			copyResult.add(copyData.get(i));
			for(int j = i; j >=  0; j--)
				copyData.remove(j);
			combinationSelect(workspace,copyData, copyResult, length);
		}

	}

	public static void clearInputStream() {
		Scanner in = new Scanner(System.in);
		in.nextLine();
	}
}
