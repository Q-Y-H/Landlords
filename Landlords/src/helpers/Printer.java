package helpers;

import entities.Player;
import entities.Card;
import java.util.ArrayList;
public class Printer {

	public String printCards(Player p) {
		String message = "┌";
		ArrayList<Card> pokers = p.getPokerOnHand();
		
		int len = pokers.size();
		for(int i = 0; i < len; i++) {
			message += "──┐";
		}
		message += "\n|";
		for(Card poker: pokers) {
			message += (poker.getLevel().getName()+" |");
		}
		message += "\n|";
		for(Card poker: pokers) {
			message += (poker.getType().getName()+" |");
		}
		message += "\n└";
		for(int i = 0; i < len; i++) {
			message += "──┘";
		}
		return message;
	}
}

