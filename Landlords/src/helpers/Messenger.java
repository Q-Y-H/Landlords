package helpers;

import entities.Player;
import enums.Rank;
import entities.Card;
import entities.Hand;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Messenger {

	public static void print(String msg) {
		System.out.println(msg);
	}

	public static String printAskForInput(Scanner sc, String type, String prompt) {
		System.out.print(prompt);
		String input;

		while(true) {
			input=sc.nextLine().toUpperCase();
			switch(type){
			case "name":
				return input;
			case "landlord":
				if(input.equals("Y") || input.equals("N"))
					return input;
				else {
					System.out.print(prompt);
					break;
				}
			case "play":
				return input;
			}
			System.out.print("Please input correctly: ");
		}

	}

	public static void waitForPlayer(Player player) {
		String msg = "It's player " + player.getNickname() + "'s turn!\n";
		print(msg);
		waiting();
	}

	public static void waiting() {
		print("Press ENTER to continue ...");
		try {
			while(System.in.read() != '\n');
			//Helper.clearInputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void RunforLandlordMsg(Player player) {
		print("\nRunning for the LANDLORD position!\n");
		waitForPlayer(player);
	}

	public static String printCards(List<Card> cards) {
		String message = "┌";
		int len = cards.size();
		for (int i = 0; i < len; i++) {
			message += "──┐";
		}
		message += "\n|";
		for (Card poker : cards) {
			if (poker.getRank().getName().equals("10"))
				message += (poker.getRank().getName() + "|");
			else
				message += (poker.getRank().getName() + " |");
		}
		message += "\n|";
		for (Card poker : cards) {
			message += (poker.getSuit().getName() + " |");
		}
		message += "\n└";
		for (int i = 0; i < len; i++) {
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

	public static String printPreviousPokers(List<Card> nextPokers, List<Card> previousPokers, Player nextP,
			Player previousP) {
		String message = "";
		if (nextPokers.size() == 0)
			message += (nextP.getNickname() + " passes.\n");
		else {
			message += (nextP.getNickname() + " plays:\n");
			printCards(nextPokers);
		}
		if (previousPokers.size() == 0)
			message += (previousP.getNickname() + " passes.\n");
		else {
			message += (nextP.getNickname() + " plays:\n");
			printCards(nextPokers);
		}
		return message;
	}

	public static void clear() {
		clear(300);
	}

	public static void clear(int times) {
		String msg = "";
		for (int i = 0; i < times; ++i)
			msg += "\n";
		print(msg);
	}

	public static String inputHelp(Player p,List<Card> prev) {
		// TODO Auto-generated method stub
		List<Card> selectCards=new ArrayList<Card>();
		selectCards=Helper.hintCards(p.getCards(),Hand.cards2hand(prev),prev.size() );
		String message ="";
		if(selectCards!=null) {
			message+="We suggest you play: \n";
			message+=printCards(selectCards);
		}
		else {
			message+="We suggest you pass\n";
		}
		return message;
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

	@SuppressWarnings("unchecked")
	// TODO
	public static String previousInfo(String infoType, List<Player> players, int cursor, List info) {
		if (infoType.equals("RunForLandlord"))
			//previousRunForLandlordInfo(players, cursor, info, first);
			;
//		else if (infoType.equals("Play"))
//			previousPlayInfo(players, cursor, info);
		return infoType;
	}

	public static String previousRunForLandlordInfo(List<Player> players, int cursor, List<Boolean> choices, int first) {
		String msg = "Round "+(choices.size()+1)+":\n";
		int size = choices.size();

		for (int i = 0; i < size; i++) {
			int index = (first + i ) % 3;
			msg += ("Player " + players.get(index).getNickname() + ": ");
			if (choices.get(i))
				msg += ("Running for LANDLORD.\n\n");
			else
				msg += ("Waived.\n\n");
		}

		msg += ("It's your turn. Your cards are as follows:\n");
		msg += (printCards(players.get(cursor).getCards()) + "\n");

		return msg;
	}

	// TODO: As for information security, the whole players list should not be sent into the method
	public static String playersInfo(List<Player> players, int cursor, LinkedList<List<Card>> previousCardsList) {
		String msg = "";
		int size = previousCardsList.size();

		for (int i = 2; i >= 1; --i) {
			if (size >= i) {
				Player player = players.get((cursor + 3 - i) % 3);
				int remainCards = player.getCards().size();
				msg += ("[" + player.getRole() + "] " + player.getNickname() + ": " + remainCards
						+ " cards remaining.\n");
				if (previousCardsList.get(size - i).size() != 0)
					msg += (printCards(previousCardsList.get(size - i)));
				else
					msg += ("PASS\n");
			}
		}

		msg += ("\nIt's your turn. Your cards are as follows:\n");
		msg += (printCards(players.get(cursor).getCards()) + "\n");

		return msg;
	}

	public static void handleRunForLandlord(List<Player> players, int cursor, List<Boolean> choices, int first) {
		Player player=players.get(cursor);

		clear();
		print("\nRound "+ (choices.size()+1) +": Running for the LANDLORD position!\n");
		waitForPlayer(player);
		clear();
		print(Messenger.previousRunForLandlordInfo(players, cursor, choices, first));
	}

	/*This method is hard to implement because besides the token,
	 * some other parameters, like players, cursor, etc,
	 * are needed to generate the message
	 */
	public static String getMessageByToken(String token) {
		String msg = "";
		switch(token) {
		case "RunForLandlord":
			clear();

		}
		return msg;
	}
}
