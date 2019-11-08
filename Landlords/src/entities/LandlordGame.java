package entities;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import javax.swing.Spring;

import entities.Card;
import entities.CardCase;
import entities.Player;
import entities.RobotPlayer;
import enums.HandType;
import enums.PlayerRole;
import helpers.Helper;
import helpers.Messenger;
import entities.CardRoom;
import entities.Hand;

public class LandlordGame {
	private CardRoom room;
	public void initialize(){
		/*
		 * Initialize the card room and the players
		 */
		Scanner in = new Scanner(System.in);
		String input="";
		do {
			Messenger.print("Do you want to play mutiplayer or solo? M for Multiplayer/ S for solo");
			input=in.nextLine().toUpperCase();
		}
		while(!(input.equals("M")||input.equals("S")));
		CardRoom room = new CardRoom();
		LinkedList<List<Card>> previousCardsList = room.getPreviousCardsList();
		List<Player> players = room.getPlayers();
		Helper.shuffleCards(room.getCardCase());
		// Cut the base cards to 4 folders;
		List<List<Card>> cardLists = Helper.cutCards(room.getCardCase());
		// the last one folder for the landlord
		room.setLandlordCards(cardLists.get(3));

		room.selectLandlord();


	}
	
	public void run() {
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
			
	//test			
//			RobotPlayer Robot =new RobotPlayer("test");
//			Robot.setCards(playerCards);
//			String msg =Messenger.printCards(Robot.getCards());
//			
//			Messenger.print(msg+"\n");
//			Robot.sparseCards();
//			Messenger.print(Messenger.printCards(Robot.getCards())+"\n");			
			
			
			
			
			
			
			
			
			
			
			
	//*****************************************************************			
			
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
					Messenger.print(Messenger.inputHelp()); // TODO: need to be implemented
					continue;
				}
				
				if (cmd.equals("PASS")) { 
					// TODO: Landlord cannot pass in the first round? or cannot pass in the
					// winning round?
					if(previousCardsList.isEmpty()) {
						Messenger.print("Cannot pass in first round");
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
						|| lastHand.compareTo(currHand) < 0) {
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
