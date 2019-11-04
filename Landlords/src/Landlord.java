import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import entities.Card;
import entities.CardCase;
import entities.Player;
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
			Messenger.printAskForInput("Player " + (i+1) + ": Please Set Your Nickname >> ");
			String nickname = in.nextLine();
			players.add(new Player(nickname, PlayerRole.PEASANT));
			players.get(i).setCards(cardLists.get(i));
			Helper.sortCards(players.get(i).getCards());
		}

		/*
		 * Landlord election
		 */
		Random rand = new Random();
		int cursor = rand.nextInt(3);
		int landlordID = 0;
		List<Boolean> choices = new ArrayList<Boolean>();
		int nWaive = 0;

		for (int i = 0; i < 3; ++i) {
			Player player = players.get(cursor);
			Messenger.clear();
			Messenger.waitForPlayer(player, "\nRunning for the LANDLORD position!\n");
			Messenger.print(Messenger.previousRunForLandlordInfo(players, cursor, choices));

			Messenger.printAskForInput("Player " + player.getNickname() + ": Do you want to run for landlord? [y/n] ");
			String cmd = in.nextLine();
			if (cmd.equals("y") || cmd.equals("Y")) { // TODO: check invalid input like 'ilsdhcvi'
				choices.add(true);
				landlordID = cursor;
			} else {
				choices.add(false);
				nWaive++;
			}
			cursor = (cursor + 1) % 3;
		}
		
		if (nWaive == 3) { // All waive
			landlordID = rand.nextInt(3);
			
		// nWaive == 2 => Landlord confirmed.
			
		} else if (nWaive == 1) { // one player waives
				// TODO: 2 players run for landlord
//			while (choices.get(cursor % 3))
//				cursor++;
		} else if (nWaive == 0) { // All run for landlord
			// TODO: 
		}

		
		/* ******************** Default landlord, modify it later */
		room.setLandlordID(landlordID);
		players.get(landlordID).setRole(PlayerRole.LANDLORD);
		players.get(landlordID).getCards().addAll(room.getLandlordCards());
		Helper.sortCards(players.get(landlordID).getCards());
		Messenger.print("The landlords is Player " + players.get(landlordID).getNickname());
		Messenger.print("Landlord cards:");
		Messenger.print(Messenger.printCards(room.getLandlordCards()));
		/* ******************** */

		
		/*
		 * Game start
		 */
		cursor = landlordID;
		//Messenger.clear();
		Messenger.print("Game Start!\n");
		boolean finishFlag = false;

		while (!finishFlag) {
			
			/* ******************** Display refresh part */
			Player player = players.get(cursor);
			List<Card> playerCards = player.getCards();
			Messenger.waitForPlayer(player);
			//Messenger.clear();
			Messenger.print(Messenger.playersInfo(players, cursor, previousCardsList));
			/* ******************** */
			
			do {
				Messenger.print("Please choose the cards to play. Input 'help' for example inputs.\n");
				Messenger.printAskForInput("[" + player.getRole() + "] " + player.getNickname() + " >> ");
				
				// Input Processing
				String cmd = in.nextLine();
				if (cmd.toUpperCase().equals("HELP")) {
					Messenger.print(Messenger.inputHelp()); // TODO: need to be implemented
					continue;
				}
				
				if (cmd.toUpperCase().equals("PASS")) { 
					// TODO: Landlord cannot pass in the first round? or cannot pass in the
					// winning round?
					if(room.getLastHandPlayer()==null) {
						System.out.println(Messenger.disobeyRulesError());
						continue;
					}
					previousCardsList.add(new ArrayList<Card>());
					if (previousCardsList.size() >= 3) {
						previousCardsList.remove();
						System.out.println(Messenger.disobeyRulesError());
						continue;
					}
					break;
				}
				
				ArrayList<String> inputCardNames = new ArrayList<String>();
				Scanner cmdScanner = new Scanner(cmd);
				while (cmdScanner.hasNext()) { // TODO: exception handle
					inputCardNames.add(cmdScanner.next());
				}
				cmdScanner.close();

				// TODO: if input nothing, PASS or re-input?
				if (inputCardNames.size() == 0) {
					System.out.println(Messenger.inputErrorMessage());
					continue;
				}
				
				if (!Helper.isValidInputCardNames(inputCardNames)) { // check if valid string input
					System.out.println(Messenger.inputErrorMessage());
					continue;
				}
				
				List<Card> selectedCards = player.checkCardsOnHand(inputCardNames); // check if cards are on hand
				if (selectedCards == null) {
					System.out.println(Messenger.cardsNotOnHandError());
					continue;
				}

				Hand currHand = Hand.cards2hand(selectedCards);
				Hand lastHand = room.getLastHand();
				if (lastHand.compareTo(currHand) < 0
						|| room.getLastHandPlayer() == player) {
					player.removeCards(selectedCards);
					room.setLastHand(currHand);
					room.setLastHandPlayer(player);
					previousCardsList.add(selectedCards);
					if (previousCardsList.size() >= 3)
						previousCardsList.clear();
					break;
				} else {
					System.out.println(Messenger.disobeyRulesError());
					continue; 
				}
			} while (true);

			Messenger.print(Messenger.printCards(previousCardsList.getLast()));
			Messenger.waiting();
			//Messenger.clear();

			// check finish
			if (player.getCards().size() == 0) {
				finishFlag = true;
			}

			// Room info update
			cursor = (cursor + 1) % 3;
		}

		// Check winner
		if (room.getLastHandPlayer().getRole() == PlayerRole.LANDLORD) {
			System.out.println("Landlord wins!");
		} else {
			System.out.println("Peasants win!");
		}

		System.out.println("Press ENTER ...");
		in.next();

		in.close();
	}
}
