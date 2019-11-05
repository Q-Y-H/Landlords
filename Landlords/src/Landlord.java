import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import entities.Card;
import entities.CardCase;
import entities.Player;
import enums.HandType;
import enums.PlayerRole;
import helpers.Helper;
import helpers.Messenger;
import entities.CardRoom;
import entities.Hand;

public class Landlord {
	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);

		/*
		 * Initialize the card room and the players
		 */
		CardRoom room = new CardRoom();
		LinkedList<List<Card>> previousCardsList = room.getPreviousCardsList();
		List<Player> players = room.getPlayers();
		Helper.shuffleCards(room.getCardCase());
		// Cut the base cards to 4 folders;
		List<List<Card>> cardLists = Helper.cutCards(room.getCardCase());
		// the last one folder for the landlord
		room.setLandlordCards(cardLists.get(3));

		for (int i = 0; i < 3; ++i) { // TODO: room.distributeCards() ?
			String nickname = Messenger.printAskForInput(in, "name",
					"Player " + (i+1) + ": Please Set Your Nickname >> ");
			players.add(new Player(nickname, PlayerRole.PEASANT));
			players.get(i).setCards(cardLists.get(i));
			Helper.sortCards(players.get(i).getCards());
		}

		/*
		 * Landlord election
		 */
		Random rand = new Random();
		int cursor = rand.nextInt(3);
		int first = cursor;
		int landlordID = 0;
		List<Boolean> choices = new ArrayList<Boolean>();
		int nWaive = 0;

		for (int i = 0; i < 4; ++i) {
			if(i == 3)
				if (nWaive == 3) { // all waive
					landlordID = rand.nextInt(3);
					break;
				}
				else if(nWaive == 2) // two players waive
					break;
				else if (nWaive == 1) // one player waives
					while(!choices.get((cursor + 3 - first)%3))
						cursor = (cursor+1)%3;
				//no player waives

			Player player = players.get(cursor);
			Messenger.handleRunForLandlord(players, cursor, choices, first);
			String cmd = Messenger.printAskForInput(in, "landlord", "Player " + player.getNickname() +
					": Do you want to run for landlord? [y/n] ");
			if (cmd.equals("Y")) { // TODO: check invalid input like 'ilsdhcvi'
				choices.add(true);
				landlordID = cursor;
			}
			else {
				choices.add(false);
				nWaive++;
			}
			cursor = (cursor + 1) % 3;
		}

		/* ******************** Default landlord, modify it later */
		room.setLandlordID(landlordID);
		players.get(landlordID).setRole(PlayerRole.LANDLORD);
		players.get(landlordID).getCards().addAll(room.getLandlordCards());
		Helper.sortCards(players.get(landlordID).getCards());
		Messenger.print("The landlord is Player " + players.get(landlordID).getNickname());
		Messenger.print("Landlord cards:");
		Messenger.print(Messenger.printCards(room.getLandlordCards()));
		/* ******************** */


		/*
		 * Game start
		 */
		cursor = landlordID;
		Messenger.clear();
		Messenger.print("Game Start!\n");
		boolean finishFlag = false;

		while (!finishFlag) {

			/* ******************** Display refresh part */
			Player player = players.get(cursor);
			List<Card> playerCards = player.getCards();
			Messenger.waitForPlayer(player);
			Messenger.clear();
			Messenger.print(Messenger.playersInfo(players, cursor, previousCardsList));
			/* ******************** */

			do {
				Messenger.print("Please choose the cards to play. Input 'help' for example inputs.\n");

				// Input Processing
				String cmd = Messenger.printAskForInput(in,"play",
						"[" + player.getRole() + "] " + player.getNickname() + " >> ");

				if (cmd.equals("HELP")) {
					Messenger.print(Messenger.inputHelp(player, previousCardsList.getLast())); // TODO: need to be implemented
					continue;
				}

				if (cmd.equals("PASS")) {
					// TODO: Landlord cannot pass in the first round? or cannot pass in the
					// winning round?
					if(previousCardsList.isEmpty()||room.getLastHandPlayer() == player) {
						Messenger.print("Cannot pass.");
						continue;
					}

					previousCardsList.add(new ArrayList<Card>());
					if (previousCardsList.size() >= 3)
						previousCardsList.remove();
					break;
				}

				ArrayList<String> inputCardNames = new ArrayList<String>();
				Scanner cmdScanner = new Scanner(cmd);
				while (cmdScanner.hasNext()) // TODO: exception handle
					inputCardNames.add(cmdScanner.next());
				cmdScanner.close();

				// TODO: if input nothing, PASS or re-input?
				if (inputCardNames.size() == 0 || !Helper.isValidInputCardNames(inputCardNames)) {
					System.out.println(Messenger.inputErrorMessage());
					continue;
				}

				List<Card> selectedCards = player.checkCardsOnHand(inputCardNames); // check if cards are on hand
				if (selectedCards == null) {
					System.out.println(Messenger.cardsNotOnHandError());
					continue;
				}

				Hand currHand = Hand.cards2hand(selectedCards);
				if(currHand.getType() ==HandType.ILLEGAL) {
					System.out.println(Messenger.disobeyRulesError());
					continue;
				}
				Hand lastHand = room.getLastHand();
				if (room.getLastHandPlayer() == null || room.getLastHandPlayer() == player
						|| lastHand.isSmallerThan(currHand)==true) {
					player.removeCards(selectedCards);
					room.setLastHand(currHand);
					room.setLastHandPlayer(player);
					previousCardsList.add(selectedCards);
					if (previousCardsList.size() >= 3)
						previousCardsList.remove();
					break;
				} else {
					System.out.println(Messenger.disobeyRulesError());
					continue;
				}
			} while (true);

			if (!previousCardsList.getLast().isEmpty())
				Messenger.print(Messenger.printCards(previousCardsList.getLast()));

			Messenger.waiting();
			Messenger.clear();

			// check finish
			if (player.getCards().size() == 0)
				finishFlag = true;

			// Room info update
			cursor = (cursor + 1) % 3;
		}

		// Check winner
		if (room.getLastHandPlayer().getRole() == PlayerRole.LANDLORD)
			System.out.println("Landlord wins!");
		else
			System.out.println("Peasants win!");

		System.out.println("Press ENTER ...");
		in.next();

		in.close();
	}
}
