package entities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public final class Messenger {

	private static final Messenger INSTANCE = new Messenger();
	private ArrayList<String> history = new ArrayList<String>();
	private CardRoom cardRoom;

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
	
	public void setCardRoom(CardRoom cardRoom) {
		this.cardRoom = cardRoom;
	}

	public void print(String msg) {
		history.add(msg);
		System.out.print(msg);
	}

	public void println(String msg) {
		history.add(msg + "\n");
		System.out.println(msg);
	}

	public void printBetweenBanner(String msg) {
		int length = msg.length();
		String banner = "";
		for (int i = 0; i < length; ++i) {
			banner += "=";
		}
		println(banner);
		println(msg);
		println(banner);
	}

	public String askForInput(String prompt, String[] inputSet, boolean isCaseSensitive) {
		String input = "";
		boolean hasInput = false;

		while (!hasInput) {
			print(prompt);
			input = in.nextLine();
			if (input.equals("")) {
				// hasInput = true;
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
				println("Invalid input");
			}
		}
		return input;
	}

	public void waiting() {
		print("Press ENTER to continue ...");
		try {
			while (System.in.read() != '\n')
				;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void waitForPlayer(Player player) {
		String msg = "It's player " + player.getNickname() + "'s turn!\n";
		print(msg);
		waiting();
	}

	public void clearInputStream() {
		in.nextLine();
	}

	public void clear(int times) {
		String msg = "";
		for (int i = 0; i < times; ++i)
			msg += "\n";
		print(msg);
	}

	public void clear() {
		clear(300);
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

	public String prevPlayersInfo(int cursor, CardRoom room) {
		List<Player> players = room.getPlayers();
		List<Hand> recenHands = room.getRecentHands();

		int size = recenHands.size();

		String msg = "";
		for (int i = 2; i >= 1; --i) {
			if (size >= i) {
				Player player = players.get((cursor + 3 - i) % 3);
				int remainCards = player.getCards().size();
				msg += "[" + player.getRole() + "] " + player.getNickname() + ": " + remainCards
						+ " cards remaining.\n";
				if (recenHands.get(size - i).getCards().size() != 0)
					msg += printCards(recenHands.get(size - i).getCards());
				else
					msg += "PASS\n";
			}
		}
		msg += "\n";
		return msg;
	}
	
	public void showCurrentCards(Player player) {
		println("");
		println("It's your turn. Your cards are as follows:\n");
		println(printCards(player.getCards()));
		println("");
	}

	public void handleClaimLandlord(List<Boolean> choices, List<Player> players, int currCursor, int initCursor) {
		Player player = players.get(currCursor);
		int size = choices.size();

		clear();
		printBetweenBanner("Round " + (choices.size() + 1) + ": Claiming the LANDLORD position!");
		println("");
		waitForPlayer(player);
		clear();
		println("Round " + (choices.size() + 1) + ":\n");

		for (int i = 0; i < size; i++) {
			int index = (initCursor + i) % 3;
			println("Player " + players.get(index).getNickname() + ": ");
			if (choices.get(i))
				println("Claiming LANDLORD.\n");
			else
				println("Waived.\n");
		}

		println("It's your turn. Your cards are as follows:");
		println(printCards(players.get(currCursor).getCards()) + "\n");
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
		if (suggestCards != null && suggestCards.size() != 0) {
			return "Suggestion:\n" + printCards(suggestCards);
		} else {
			return "Suggestion:\n\tpass\n";
		}
	}
	
	public void showLandlordInfo() {
		int landlordID = this.cardRoom.getLandlordID();
		clear();
		println("The landlord is Player " + this.cardRoom.getPlayers().get(landlordID).getNickname());
		println("Landlord cards:");
		println(printCards(this.cardRoom.getLandlordCards()));
		waiting();
	}
}