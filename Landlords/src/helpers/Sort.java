package helpers;

import entities.Poker;
import java.util.*;

public class Sort {
	private static Comparator<Poker> pokerComparator = new Comparator<Poker>() {
		@Override
		public int compare(Poker o1, Poker o2) {
			return o1.getLevel().getRank() - o2.getLevel().getRank();
		}
	};
	
	public static void sortPoker(ArrayList<Poker> pokers){
		Collections.sort(pokers, pokerComparator);
	}
}
