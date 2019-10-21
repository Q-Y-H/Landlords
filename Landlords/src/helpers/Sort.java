package helpers;

import entities.Card;
import java.util.*;

public class Sort {
	private static Comparator<Card> pokerComparator = new Comparator<Card>() {
		@Override
		public int compare(Card o1, Card o2) {
			return o1.getLevel().getRank() - o2.getLevel().getRank();
		}
	};
	
	public static void sortPoker(ArrayList<Card> pokers){
		Collections.sort(pokers, pokerComparator);
	}
}
