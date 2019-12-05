package entities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import enums.HandType;

public final class Messenger {

	private static final Messenger INSTANCE = new Messenger();
	private ArrayList<String> history = new ArrayList<String>();

	final Scanner in = new Scanner(System.in);

	private Messenger() {
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				in.close();
			}
		});
	}

	public static Messenger getInstance() {
		return INSTANCE;
	}

	public void print(String msg) {
		history.add(msg);
		System.out.print(msg);
	}
	
	public void println(String msg) {
		history.add(msg + "\n");
		System.out.println(msg);
	}

//	public  String printAskForInput(Scanner sc, String type, String prompt) {
//		System.out.print(prompt);
//		String input;
//
//		while (true) {
//			input = sc.nextLine().toUpperCase();
//			switch (type) {
//			case "name":
//				return input;
//			case "landlord":
//				if (input.equals("Y") || input.equals("N"))
//					return input;
//				else {
//					System.out.print(prompt);
//					break;
//				}
//			case "play":
//				return input;
//			}
//			System.out.print("Please input correctly: ");
//		}
//
//	}

	public String askForInput(String prompt, String[] inputSet, boolean isCaseSensitive) {
		String input = "";
		boolean hasInput = false;

		while (!hasInput) {
			print(prompt);
			input = in.nextLine();
			if (input.equals("")) {
				hasInput = true;
			} else if (inputSet.length == 0) {
				hasInput = true;
			} else {
				for (String s : inputSet) {
					if (isCaseSensitive) {
						if (input.equals(s)) {
							hasInput = true;
						}
					} else {
						if (input.toUpperCase().equals(s.toUpperCase())) {
							hasInput = true;
						}
					}
				}
			}
			if (!hasInput) {
				print("Invalid input.\n");
			}
		}

		return input;
	}

	public void waitForPlayer(Player player) {
		String msg = "It's player " + player.getNickname() + "'s turn!\n";
		print(msg);
		waiting();
	}

	public void waiting() {
		print("Press ENTER to continue ...");
		try {
			while (System.in.read() != '\n')
				;
			// Helper.clearInputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void RunforLandlordMsg(Player player) {
		print("\nRunning for the LANDLORD position!\n");
		waitForPlayer(player);
	}

	public String printCards(List<Card> cards) {
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

	public String printPreviousPokers(List<Card> nextPokers, List<Card> previousPokers, Player nextP,
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

	public void clear() {
		clear(300);
	}

	public void clear(int times) {
		String msg = "";
		for (int i = 0; i < times; ++i)
			msg += "\n";
		print(msg);
	}

	public String inputErrorMessage() {
		return "Input should only contain numbers from 2 to 10 and J, Q, K, A, B, R!";
	}

	public String cardsNotOnHandError() {
		return "You should select the cards in your hand!";
	}

	public String disobeyRulesError() {
		return "Your input doesn't meet the rules!";
	}

	@SuppressWarnings("unchecked")
	// TODO
	public String previousInfo(String infoType, List<Player> players, int cursor, List info) {
		if (infoType.equals("RunForLandlord"))
			// previousRunForLandlordInfo(players, cursor, info, first);
			;
		// else if (infoType.equals("Play"))
		// previousPlayInfo(players, cursor, info);
		return infoType;
	}

	public String previousRunForLandlordInfo(List<Player> players, int cursor, List<Boolean> choices, int first) {
		String msg = "Round " + (choices.size() + 1) + ":\n";
		int size = choices.size();

		for (int i = 0; i < size; i++) {
			int index = (first + i) % 3;
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

	public String playersInfo(int cursor, CardRoom room) {
		List<Player> players = room.getPlayers();
		List<Hand> handHistory = room.getRecentHands();

		int size = handHistory.size();

		String msg = "";
		for (int i = 2; i >= 1; --i) {
			if (size >= i) {
				Player player = players.get((cursor + 3 - i) % 3);
				int remainCards = player.getCards().size();
				msg += ("[" + player.getRole() + "] " + player.getNickname() + ": " + remainCards
						+ " cards remaining.\n");
				if (handHistory.get(size - i).getCards().size() != 0)
					msg += (printCards(handHistory.get(size - i).getCards()));
				else
					msg += ("PASS\n");
			}
		}

		msg += ("\nIt's your turn. Your cards are as follows:\n");
		msg += (printCards(players.get(cursor).getCards()) + "\n");

		return msg;
	}

	public void handleRunForLandlord(List<Player> players, int cursor, List<Boolean> choices, int first) {
		Player player = players.get(cursor);

		clear();
		print("\nRound " + (choices.size() + 1) + ": Running for the LANDLORD position!\n");
		waitForPlayer(player);
		clear();
		print(Messenger.getInstance().previousRunForLandlordInfo(players, cursor, choices, first));
	}

	public void clearInputStream() {
		in.nextLine();
	}

	public String inputHelpInfo() {
		String msg = "Please refer to the following input instruction.\n";
		msg += "\n";
		msg += "To play cards:\n";
		msg += "\t3 3 3 4\n";
		msg += "To pass the round:\n";
		msg += "\tpass\n";
		msg += "Ask for a suggestion:\n";
		msg += "\tsuggest\n";
		return msg;
	}

	public String inputSuggest(Player p, Hand prev) {
		if (prev == null) {
			return printCards(p.getCards().subList(0, 1));
		}

		List<Card> suggestCards = CardRoom.hintCards(p.getCards(), prev);
		if (suggestCards != null) {
			return "Suggestion:\n" + printCards(suggestCards);
		} else {
			return "Suggestion:\n\tpass\n";
		}
	}
}
