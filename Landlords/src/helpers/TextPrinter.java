package helpers;

import entities.Player;
import entities.Card;
import java.util.List;

public class TextPrinter {
	
	
	
	public static String printCards(List<Card> cards) {
		
		String message = "┌";		
		int len = cards.size();
		for(int i = 0; i < len; i++) {
			message += "──┐";
		}
		message += "\n|";
		for(Card poker: cards) {
			message += (poker.getRank().getName()+" |");
		}
		message += "\n|";
		for(Card poker: cards) {
			message += (poker.getSuit().getName()+" |");
		}
		message += "\n└";
		for(int i = 0; i < len; i++) {
			message += "──┘";
		}
		message += "\n";
		return message;
	}
	
	public String printCardsByPlayerName(Player p) {
		String message = "It’s your turn to play. Your pokers are as follows:\n";
		List<Card> pokers = p.getPokers();
		message += printCards(pokers);
		message += "Please enter the pokers you want to play:(You can enter [pass] to skip this round)\n";
		return message;
	}
	
	public String printPreviousPokers(List<Card> nextPokers, List<Card> previousPokers, Player nextP, Player previousP) {
		String message = "";
		if(nextPokers.size()==0)message+=(nextP.getNickname() + " passes.\n");
		else {
			message += (nextP.getNickname() + " plays:\n");
			printCards(nextPokers);
		}
		if(previousPokers.size()==0)message+=(previousP.getNickname() + " passes.\n");
		else {
			message += (nextP.getNickname() + " plays:\n");
			printCards(nextPokers);
		}
		return message;
	}
	
	public String printOtherPlayersInfo(Player nextP, Player previousP) {
		String message = "Everyone's current status:\n";
		int len1 = nextP.getPokers().size();
		int len2 = previousP.getPokers().size();
		message += (nextP.getNickname()+":	["+nextP.getRole()+"] "+len1+"pokers remaining\n");
		message += (previousP.getNickname()+":	["+previousP.getRole()+"] "+len2+"pokers remaining\n\n");
		return message;
	}

	public static void helpInfo() {
		// TODO Auto-generated method stub
		
	}
}

