package helpers;

import entities.Player;
import entities.Card;
import java.util.List;

public class Messager {
	
	
	
	public static String printCards(List<Card> cards) {
		
		String message = "┌";		
		int len = cards.size();
		for(int i = 0; i < len; i++) {
			message += "──┐";
		}
		message += "\n|";
		for(Card poker: cards) {
			if(poker.getRank().getName().equals("10"))message += (poker.getRank().getName()+"|");
			else message += (poker.getRank().getName()+" |");
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
		String message = "It's your turn to play. Your pokers are as follows:\n";
		List<Card> pokers = p.getCards();
		message += printCards(pokers);
		message += "Please enter the pokers you want to play:(You can enter [pass] to skip this round)\n";
		return message; 
	}
	
	public static String printPreviousPokers(List<Card> nextPokers, List<Card> previousPokers, Player nextP, Player previousP) {
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
	
	public static String printOtherPlayersInfo(Player nextP, Player previousP) {
		String message = "Everyone's current status:\n";
		int len1 = nextP.getCards().size();
		int len2 = previousP.getCards().size();
		message += (nextP.getNickname()+":	["+nextP.getRole()+"] "+len1+" pokers remaining\n");
		message += (previousP.getNickname()+":	["+previousP.getRole()+"] "+len2+" pokers remaining\n\n");
		return message;
	}

	public static void clear() {
		System.out.print("\033[H\033[2J");  
	    System.out.flush(); 
	}
	
	public static String inputHelp() {
		// TODO Auto-generated method stub
		return "";
	}
	
	public static String inputErrorMessage() {
		return "Input should only contain numbers from 2 to 10 and J, Q, K, A, B, R!";
	}
	
	public static String cardsNotOnHandError() {
		return "You should select the cards in your hand!";
	}
	
	public static String disobeyRulesError() {
		return "Your input doesn't meet the rules!";
	}
}

