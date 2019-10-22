import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entities.Card;
import entities.CardCase;
import entities.Player;
import enums.HandType;
import enums.PlayerRole;
import helpers.Helper;
import helpers.TextPrinter;
import entities.CardRoom;
import entities.Hand;

public class Main {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in); 
		
		// Initialize the card room and the players 
		CardRoom room = new CardRoom();
		for (int i=0; i<3; ++i) {
			System.out.print("Player " + i + ": Please Enter Your Nickname > ");
			String nickname = sc.nextLine();
			room.getPlayers().add(new Player(nickname, PlayerRole.PEASANT));
		}
		
		List<Player> players = room.getPlayers();
		
		
		// Cut and distribute cards 
		CardCase cardCase = new CardCase();
		List<List<Card>> cardLists = Helper.cutCards(cardCase);
		Helper.shuffle(cardCase.getBaseCards());
		
		for (Player player: players) {
			player.setPokers(cardLists.get(player.getId()));
		}
		room.setLandlordCards(cardLists.get(3));
		
		// System.out.println("Game Start! Player " + players.get(0).getNickname() + " first!");
		// System.out.println("Press ENTER ...");
		// sc.next();
		
		
		/* ******************** */
		// TODO: Print Players' cards on hand one by one in their own windows
		//
		// Yes, I know, really weird to play on a single cmd ORZ
		// Maybe use robot in release 2
		//
		//
		/* ******************** */
		
		
		/* ******************** */
		// TODO: Elect the landlord
		//
		int landlordID = 0;
		room.setLandlordID(landlordID);
		players.get(landlordID).setRole(PlayerRole.LANDLORD);
		players.get(landlordID).getPokers().addAll(room.getLandlordCards());
		
		for (Player player: players) {
			Helper.sortPokers(player.getPokers());
		}
		//
		// TODO: Display the three landlord cards
		/* ******************** */
		
		// Game Start
		System.out.println("Game Start! Player " + players.get(0).getNickname() + " first!");
		boolean finishFlag = false;
		boolean firstIn = true;
		int playerCursor = landlordID;
		
		while(!finishFlag) {
			Player player = players.get(playerCursor);
			
			System.out.println("Please choose the cards to play.");
			if (firstIn) { // TODO: here is actually the help (example 3)
				System.out.println("Example 1: 3 3 3 4");
				System.out.println("Example 2: pass");
				System.out.println("Example 3: help");
				firstIn = false;
			}
			
			// get player input
			System.out.print("Player " + player.getNickname() + " > ");
			String input = sc.nextLine();
			// TODO: extract check inputs methods: checkInput(String input)
			if (input.equals("help")) TextPrinter.helpInfo(); // TODO
			if (input.equals("pass")) ; // TODO
			ArrayList<String> cardNames = new ArrayList<String>();
			// ArrayList<Character> cardNames = new ArrayList<Character>();
			Scanner in = new Scanner(input);
			while(in.hasNext()) { // TODO: exception handle
				cardNames.add(in.next());
			}
			
			// check if input valid
			Helper.checkValidCards(cardNames);
			List<Card> cards = player.checkCardsOnHand(cardNames); // return null if player doesn't has those cards
			if (cards == null) ; // TODO: ask for re-choose cards
			Hand currHand = Hand.cards2hand(cards);
			if (currHand.getType() == HandType.ILLEGAL) ; // TODO: ask for re-choose cards
			if (room.getLastHandPlayer().equals(player) || room.getLastHandPlayer().equals(null) || !currHand.over(room.getLastHand())) ;  // TODO: ask for re-choose cards
			
			// play cards (how to translate "chu pai" ?)
			player.playCards(cards); // delete cards from player's cards
			TextPrinter.printCards(cards);
			
			// check finish
			if (player.getPokers().size() == 0) {
				finishFlag = true;
			}
			
			// Room info update
			room.setLastHand(currHand);
			room.setLastHandPlayer(player);
			playerCursor = (playerCursor+1)%3;
		}
		
		// Check winner
		if (room.getLastHandPlayer().getRole() == PlayerRole.LANDLORD) {
			System.out.println("Landlord wins!");
		}
		else {
			System.out.println("Peasants win!");
		}
		
		System.out.println("Press ENTER ...");
		sc.next();
	}
}
