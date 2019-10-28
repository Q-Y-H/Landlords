import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import entities.Card;
import entities.CardCase;
import entities.Player;
import enums.PlayerRole;
import helpers.Helper;
import helpers.Messager;
import entities.CardRoom;
import entities.Hand;

public class Main {
	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);

		/*
		 * Initialize the card room and the players 
		 */
		CardRoom room = new CardRoom();
		LinkedList<List<Card>> previousCardsList = room.getPreviousCardsList();
		List<Player> players = room.getPlayers();
		Helper.shuffleCards(room.getCardCase());
		List<List<Card>> cardLists = Helper.cutCards(room.getCardCase()); // Cut the base cards to 4 folders;
		room.setLandlordCards(cardLists.get(3)); // the last one folder for the landlord
		
		for (int i=0; i<3; ++i) {
			System.out.print("Player " + i + ": Please Set Your Nickname >> ");
			String nickname = in.nextLine();
			players.add(new Player(nickname, PlayerRole.PEASANT));
			players.get(i).setCards(cardLists.get(i));
			Helper.sortCards(players.get(i).getCards());
		}
		
		/*
		 * Landlord election
		 */
		// TODO: Here I only implement the basic idea of running for landlord. Need to be further implemented.
		Random rand=new Random();
		int cursor = rand.nextInt(3);
		int landlordID = 0;
		boolean[] forfeited= {false,false,false};
		int nForfeited=0;
		System.out.println("Running for the landlord! Player " + players.get(cursor).getNickname() + " first!");
		for (int i=0; i<3; ++i) {
			Player currPlayer = players.get(cursor);
			String msg = Messager.printCards(currPlayer.getCards());
			System.out.print(msg);
			System.out.print("Player " + currPlayer.getNickname() + ": Do you want to run for landlord? [y/n]");
			String choice = in.nextLine();
			if (choice.equals("y") || choice.equals("Y")) {
				landlordID=cursor;
			}
			else {
				forfeited[cursor]=true;
				nForfeited++;
			}
			// ...
			cursor = (cursor+1)%3;
		}
		if(nForfeited==3)
			landlordID=rand.nextInt(3);
		else if(nForfeited==1) {
			while(forfeited[cursor%3])
				cursor++;
			Player currPlayer = players.get(cursor);
			String msg = Messager.printCards(currPlayer.getCards());
			System.out.print(msg);
			System.out.print("Player " + currPlayer.getNickname() + ": Do you want to run for landlord? [y/n]");
			String choice = in.nextLine();
			if (choice.equals("y") || choice.equals("Y")) {
				landlordID=cursor;
			}
		}
		else if(nForfeited==0) {
			Player currPlayer = players.get(cursor);
			String msg = Messager.printCards(currPlayer.getCards());
			System.out.print(msg);
			System.out.print("Player " + currPlayer.getNickname() + ": Do you want to run for landlord? [y/n]");
			String choice = in.nextLine();
			if (choice.equals("y") || choice.equals("Y")) {
				landlordID=cursor;
			}
		}
		
		/* ******************** Default landlord, modify it later */
		room.setLandlordID(landlordID);
		players.get(landlordID).setRole(PlayerRole.LANDLORD);
		players.get(landlordID).getCards().addAll(room.getLandlordCards());
		Helper.sortCards(players.get(landlordID).getCards());
		System.out.println("The landlords is Player " + players.get(landlordID).getNickname());
		System.out.println("Landlord cards:");
		String msg = Messager.printCards(room.getLandlordCards());
		System.out.println(msg);
		/* ******************** */

		/*
		 * Game start
		 */
		cursor = landlordID;
		System.out.println("Game Start! Player " + players.get(cursor).getNickname() + " first!");
		boolean finishFlag = false;
		
		while(!finishFlag) {
			Player player = players.get(cursor);
			List<Card> playerCards = players.get(cursor).getCards();
			
			do {
				/* ******************** Display refresh part */
				Messager.clear();
				System.out.println(Messager.printOtherPlayersInfo(players.get((cursor+1)%3), players.get((cursor+2)%3)));
				System.out.println(Messager.printCards(playerCards));
				System.out.println("Please choose the cards to play. Input 'help' for example inputs. \n");
				System.out.print("[" + player.getRole().toString() + "]" + "Player " + player.getNickname() + " >> ");
//				System.out.println("Example 1: 3 3 3 4");
//				System.out.println("Example 2: pass");
//				System.out.println("Example 3: help\n");
				/* ******************** */
				
				String cmd = in.nextLine();
				if (cmd.equals("help")) {
					msg = Messager.inputHelp(); // TODO
					System.out.println(msg);
					continue;
				}
				else if (cmd.equals("pass")) { // TODO: Landlord cannot pass in the first round? or cannot pass in the winning round?
					previousCardsList.add(new ArrayList<Card>());
					if (previousCardsList.size() >= 3)
						previousCardsList.remove();
					break;
				}
				// TODO: handle invalid input like 'jgjkdkas'
				ArrayList<String> cardNames = new ArrayList<String>();
				Scanner cmdScanner = new Scanner(cmd);
				while(cmdScanner.hasNext()) { // TODO: exception handle
					cardNames.add(cmdScanner.next());
				}
				cmdScanner.close();
				
				if (!Helper.checkValidCards(cardNames)) { // check if valid string input
					System.out.println(Messager.inputErrorMessage());
					continue;
				}
				List<Card> selectedCards = player.checkCardsOnHand(cardNames); // check if cards are on hand
				if (selectedCards == null)  {
					System.out.println(Messager.cardsNotOnHandError());
					continue;
				}
				
				Hand currHand = Hand.cards2hand(selectedCards);
				Hand lastHand = room.getLastHand();
				if(room.getLastHandPlayer() == null || room.getLastHandPlayer() == player || lastHand.compareTo(currHand) == -1) {
					player.removeCards(selectedCards);
					room.setLastHand(currHand);
					room.setLastHandPlayer(player);
					previousCardsList.add(selectedCards);
					if (previousCardsList.size() >= 3)
						previousCardsList.remove();
					break;
				}
				else {
					System.out.println(Messager.disobeyRulesError());
					continue;
				}
			} while(true);	

			msg = Messager.printCards(previousCardsList.getLast());
			System.out.println(msg);
			
			// check finish
			if (player.getCards().size() == 0) {
				finishFlag = true;
			}
			
			// Room info update
			cursor = (cursor+1)%3;
		}
		
		// Check winner
		if (room.getLastHandPlayer().getRole() == PlayerRole.LANDLORD) {
			System.out.println("Landlord wins!");
		}
		else {
			System.out.println("Peasants win!");
		}
		
		System.out.println("Press ENTER ...");
		in.next();
		
		in.close();
	}
}
