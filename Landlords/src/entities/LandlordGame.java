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
	Scanner in = new Scanner(System.in);
	
	public void initialize(){
		setRoomType();
		room.initialize();
	}
	
	public void run() {
		/*
		 * Game start
		 */
		LinkedList<List<Card>> previousCardsList = room.getPreviousCardsList();
		List<Player> players = room.getPlayers();
		int cursor = room.getLandlordID();
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
				ArrayList<String> anwser=player.playCards(room.getLastHand().getCards());
				if (anwser.equals("HELP")) {
					Messenger.print(Messenger.inputHelp()); // TODO: need to be implemented
					continue;
				}
				
				if (anwser.equals("PASS")) { 
					// TODO: Landlord  cannot pass in the winning round?
					if(room.getLastHandPlayer() == null ) {
						Messenger.print("Cannot pass in first round");
						continue;
					}
						
					previousCardsList.add(new ArrayList<Card>());
					if (previousCardsList.size() >= 3)
						previousCardsList.remove();
					break;
				}
				
				if (anwser.size() == 0 || !Helper.isValidInputCardNames(anwser)) {
					System.out.println(Messenger.inputErrorMessage());
					continue;
				}
				
				List<Card> selectedCards = player.checkCardsOnHand(anwser); // check if cards are on hand
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
	
	public void setRoomType() {
		/*
		 * Prompt for user type
		 */		
		String input="";
		do {
			Messenger.print("Do you want to play mutiplayer or solo?[M/S]");
			input=in.nextLine().toUpperCase();
		}
		while(!(input.equals("M")||input.equals("S")));
		if(input.equals("M")) {
			this.room = new MultiPlayerRoom();
		}
		else {
			this.room=new SinglePlayerRoom();
		}
	}
	
	

}
