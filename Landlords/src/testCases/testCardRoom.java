package testCases;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import Commands.Command;
import Commands.DecideRunForLandlordCommand;
import Commands.PlayChoiceCommand;
import Commands.SetNicknameCommand;
import Strategies.MediumStrategy;
import entities.Card;
import entities.CardRoom;
import entities.GameBoard;
import entities.Hand;
import entities.HumanPlayer;
import entities.Player;
import entities.RobotPlayer;
import enums.Rank;
import enums.RoomType;
import enums.Suit;

@TestMethodOrder(MethodOrderer.Alphanumeric.class)
public class testCardRoom {
	private CardRoom cr;
	private Card[] c;
	private List<Card> cards;
	private Method method = null;
	
	@BeforeEach
	public void setUp() {
		String input = new String("a\n" + "b\n" + "c\n" + "pass\n" +
				"n\n" + "y\n" + "abc\n" + "Foo\n" + "pass\n" +
				"n\n" + "n\n" + "y\n" + "n\n" + "y\n" + "n\n" + 
				"n\n" + "y\n" + "y\n" + "y\n" + "y\n" + "n\n" + "y\n" + "n\n" +
				"n\n" + "y\n" + "abc\n" +
				"pass\n" + "abc\n");
		ByteArrayInputStream bais = new ByteArrayInputStream(input.getBytes());
		System.setIn(bais);
		// The binding to System.in is restored in testPlay.java
		
		cr = new CardRoom();
		c = new Card[18];
		for (int i = 3; i < 18; i++)
			c[i] = new Card(Rank.getRankByValue(i), Suit.BLANK);
	}
	
	@Test // test setup()
	public void setup_PVP(){
		CardRoom cr = new CardRoom();
		cr.setType(RoomType.PVP);
		cr.setup();
		assertEquals(cr.getPlayers().size(), 3);
		assertEquals(cr.getPlayers().get(0).getCards().size(), 17);
		assertEquals(cr.getPlayers().get(1).getCards().size(), 17);
		assertEquals(cr.getPlayers().get(2).getCards().size(), 17);
	}
	
	@Test // test setup()
	public void setup_PVE(){
		CardRoom cr = new CardRoom();
		cr.setType(RoomType.PVE);
		cr.setup();
		assertEquals(cr.getPlayers().size(), 3);
		assertEquals(cr.getPlayers().get(0).getCards().size(), 17);
		assertEquals(cr.getPlayers().get(1).getCards().size(), 17);
		assertEquals(cr.getPlayers().get(2).getCards().size(), 17);
	}
	
	@Test //cutCards
	public void cutCards() {
		cr = new CardRoom();
		cr.shuffleCards();
		List<List<Card>> cardGroups = cr.cutCards();
		assertEquals(cardGroups.size(),4);
		assertEquals(cardGroups.get(0).size(),17);
		assertEquals(cardGroups.get(1).size(),17);
		assertEquals(cardGroups.get(2).size(),17);
		assertEquals(cardGroups.get(3).size(),3);
	}
	
	@Test // hintCards
	public void hintCards_34567_5789XJQKAAA() {
		cards = Arrays.asList(c[3],c[4],c[5],c[6],c[7]);
		List<Card> actul = Arrays.asList(c[5],c[7],c[8],c[9],c[10],c[11],c[12],c[13],c[14],c[14],c[14]);
		List<Card> expected=CardRoom.hintCards(actul,Hand.cards2hand(cards));
		
		assertEquals(Hand.cards2hand(cards).isSmallerThan(Hand.cards2hand(expected)),true);
	}
	@Test // hintCards
	public void hintCards_34567_5789JQKAAAAB() {
		cards = Arrays.asList(c[3],c[4],c[5],c[6],c[7]);
		List<Card> actul = Arrays.asList(c[5],c[7],c[8],c[9],c[11],c[12],c[13],c[14],c[14],c[14],c[14],c[16]);
		List<Card> expected=CardRoom.hintCards(actul,Hand.cards2hand(cards));
		
		assertEquals(Hand.cards2hand(cards).isSmallerThan(Hand.cards2hand(expected)),true);
	}
	@Test // hintCards
	public void hintCards_34567_5789JQKBR() {
		cards = Arrays.asList(c[3],c[4],c[5],c[6],c[7]);
		List<Card> actul = Arrays.asList(c[5],c[7],c[8],c[9],c[11],c[12],c[13],c[16],c[17]);
		List<Card> expected=CardRoom.hintCards(actul,Hand.cards2hand(cards));
		
		assertEquals(Hand.cards2hand(cards).isSmallerThan(Hand.cards2hand(expected)),true);
	}
	@Test // hintCards
	public void hintCards_34567_5789XQKR() {
		cards = Arrays.asList(c[3],c[4],c[5],c[6],c[7]);
		List<Card> actul = Arrays.asList(c[5],c[7],c[8],c[9],c[10],c[12],c[13],c[17]);
		List<Card> expected=CardRoom.hintCards(actul,Hand.cards2hand(cards));
		
		assertEquals(Hand.cards2hand(cards).isSmallerThan(Hand.cards2hand(expected)),false);
	}
	@Test //sortCards
	public void sortCards_837() {
		cards = Arrays.asList(c[8],c[3],c[7]);
		List<Card> expected = Arrays.asList(c[3],c[7],c[8]);
		CardRoom.sortCards(cards);
		int i = 0;
		while(i < expected.size() && expected.get(i) == cards.get(i))
			i++;
		assertEquals(i, expected.size());
	}
	
	@Test //sortCards
	public void sortCards_8B3710R() {
		cards = Arrays.asList(c[8],c[16],c[3],c[7],c[10],c[17]);
		List<Card> expected = Arrays.asList(c[3],c[7],c[8],c[10],c[16],c[17]);
		CardRoom.sortCards(cards);
		int i = 0;
		while(i < expected.size() && expected.get(i) == cards.get(i))
			i++;
		assertEquals(i, expected.size());
	}
	
	@Test //sortCards
	public void sortCards_897456() {
		cards = Arrays.asList(c[8],c[9],c[7],c[4],c[5],c[6]);
		List<Card> expected = Arrays.asList(c[4],c[5],c[6],c[7],c[8],c[9]);
		CardRoom.sortCards(cards);
		int i = 0;
		while(i < expected.size() && expected.get(i) == cards.get(i))
			i++;
		assertEquals(i, expected.size());
	}
	
	@Test //sortCards
	public void sortCards_A234() {
		cards = Arrays.asList(c[14],c[15],c[3],c[4]);
		List<Card> expected = Arrays.asList(c[3],c[4],c[14],c[15]);
		CardRoom.sortCards(cards);
		int i = 0;
		while(i < expected.size() && expected.get(i) == cards.get(i))
			i++;
		assertEquals(i, expected.size());
	}
	
	@Test //sortCards
	public void sortCards_J3769() {
		cards = Arrays.asList(c[11],c[3],c[7],c[6],c[9]);
		List<Card> expected = Arrays.asList(c[3],c[6],c[7],c[9],c[11]);
		CardRoom.sortCards(cards);
		int i = 0;
		while(i < expected.size() && expected.get(i) == cards.get(i))
			i++;
		assertEquals(i, expected.size());
	}
	
	@Test //sortCards
	public void sortCards_JQKJQK() {
		cards = Arrays.asList(c[11],c[12],c[13],c[11],c[12],c[13]);
		List<Card> expected = Arrays.asList(c[11],c[11],c[12],c[12],c[13],c[13]);
		CardRoom.sortCards(cards);
		int i = 0;
		while(i < expected.size() && expected.get(i) == cards.get(i))
			i++;
		assertEquals(i, expected.size());
	}
	
	@Test //sortCards
	public void sortCards_A10KQJ() {
		cards = Arrays.asList(c[14],c[10],c[13],c[12],c[11]);
		List<Card> expected = Arrays.asList(c[10],c[11],c[12],c[13],c[14]);
		CardRoom.sortCards(cards);
		int i = 0;
		while(i < expected.size() && expected.get(i) == cards.get(i))
			i++;
		assertEquals(i, expected.size());
	}
	
	@Test //sortCards
	public void sortCards_2RBA() {
		cards = Arrays.asList(c[15],c[16],c[17],c[14]);
		List<Card> expected = Arrays.asList(c[14],c[15],c[16],c[17]);
		CardRoom.sortCards(cards);
		int i = 0;
		while(i < expected.size() && expected.get(i) == cards.get(i))
			i++;
		assertEquals(i, expected.size());
	}
	
	@Test //sortCards
	public void sortCards_KQJ1098765432A() {
		cards = Arrays.asList(c[13],c[12],c[11],c[10],c[9],c[8],c[7],c[6],
				c[5],c[4],c[3],c[15],c[14]);
		List<Card> expected = Arrays.asList(c[3],c[4],c[5],c[6],c[7],c[8],c[9],
				c[10],c[11],c[12],c[13],c[14],c[15]);
		CardRoom.sortCards(cards);
		int i = 0;
		while(i < expected.size() && expected.get(i) == cards.get(i))
			i++;
		assertEquals(i, expected.size());
	}
	
	@Test //sortCards
	public void sortCards_3456787() {
		cards = Arrays.asList(c[3],c[4],c[5],c[6],c[7],c[8],c[7]);
		List<Card> expected = Arrays.asList(c[3],c[4],c[5],c[6],c[7],c[7],c[8]);
		CardRoom.sortCards(cards);
		int i = 0;
		while(i < expected.size() && expected.get(i) == cards.get(i))
			i++;
		assertEquals(i, expected.size());
	}
	
	@Test // isValidInputCardNames
	public void isValidInputCardNames_789() {		
		ArrayList<String> cardnames = new ArrayList<String>();
		cardnames.add("7");
		cardnames.add("8");
		cardnames.add("9");
		
		try {
			method = CardRoom.class.getDeclaredMethod("isValidInputCardNames", ArrayList.class);
			method.setAccessible(true);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		
		try {
			assertEquals(method.invoke(cr, cardnames), true);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	@Test // isValidInputCardNames
	public void isValidInputCardNames_t() {
		ArrayList<String> cardnames = new ArrayList<String>();
		cardnames.add("t");
		
		try {
			method = CardRoom.class.getDeclaredMethod("isValidInputCardNames", ArrayList.class);
			method.setAccessible(true);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		
		try {
			assertEquals(method.invoke(cr, cardnames), false);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	@Test // isValidInputCardNames
	public void isValidInputCardNames_xa() {
		ArrayList<String> cardnames = new ArrayList<String>();
		cardnames.add("x");
		cardnames.add("a");
		
		try {
			method = CardRoom.class.getDeclaredMethod("isValidInputCardNames", ArrayList.class);
			method.setAccessible(true);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		
		try {
			assertEquals(method.invoke(cr, cardnames), true);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	@Test // isValidInputCardNames
	public void isValidInputCardNames_jQk() {
		ArrayList<String> cardnames = new ArrayList<String>();
		cardnames.add("j");
		cardnames.add("Q");
		cardnames.add("k");
		
		try {
			method = CardRoom.class.getDeclaredMethod("isValidInputCardNames", ArrayList.class);
			method.setAccessible(true);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		
		try {
			assertEquals(method.invoke(cr, cardnames), true);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	@Test // askForPlayChoice
	public void askForPlayChoice_0_pass(){
		cr.setup();
		assertEquals(cr.askForPlayChoice(0), new String("pass"));
	}
	
	@Test // askForNickNames
	public void askForNickname_a_b_c() {
		CardRoom crPVP = new CardRoom();
		crPVP.setType(RoomType.PVP);
		crPVP.setup();
		
		try {
			method = CardRoom.class.getDeclaredMethod("askForNicknames", null);
			method.setAccessible(true);
		} catch (NoSuchMethodException e1) {
			e1.printStackTrace();
		} catch (SecurityException e1) {
			e1.printStackTrace();
		}
	
		try {
			method.invoke(crPVP, null);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		assertEquals(crPVP.getPlayers().get(0).getNickname(), new String("a"));
		assertEquals(crPVP.getPlayers().get(1).getNickname(), new String("b"));
		assertEquals(crPVP.getPlayers().get(2).getNickname(), new String("c"));
	}
	
	@Test // processPlayChoice
	public void processPlayChoice_multiple() {
		CardRoom cr = new CardRoom();
		cr.setType(RoomType.PVP);
		cr.setup();
		
		assertEquals(cr.processPlayerChoice(0, "pass"), false);
		cards = new ArrayList<Card>();
		cards.add(c[3]);
		cards.add(c[3]);
		cards.add(c[4]);
		cr.getPlayers().get(0).setCards(cards);
		assertEquals(cr.processPlayerChoice(0, ""), false);
		assertEquals(cr.processPlayerChoice(0, "5"), false);
		assertEquals(cr.processPlayerChoice(0, "3 4"), false);
		assertEquals(cr.processPlayerChoice(0, "34"), false);
		assertEquals(cr.processPlayerChoice(0, "3 3"), true);
		cr.setLastHandPlayer(0);
		assertEquals(cr.processPlayerChoice(0, "pass"), false);
		cr.setLastHandPlayer(1);
		assertEquals(cr.processPlayerChoice(0, "pass"), true);
	}
	
	@Nested
	@TestMethodOrder(MethodOrderer.Alphanumeric.class)
	class testCommand {
		private Player player;
		private Command<Void> nickname;
		private Command<Boolean> run4landlord;
		private Command<String> playChoice;
		
		@Test // test SetNicknameCommand
		public void setNickname_Robot_0() {
			player = new RobotPlayer("robot", new MediumStrategy());
			nickname = new SetNicknameCommand(player);
			nickname.execute();
			assertEquals(player.getNickname(), "Robot 1");
		}
		
		@Test // test PlayChoiceCommand
		public void setPlayChoice_pass() {
			player = new HumanPlayer();
			playChoice = new PlayChoiceCommand(player);
			playChoice.execute();
			assertEquals(playChoice.getResult().toUpperCase(), new String("PASS"));
		}
		
		@Test // test DecideRunForLandlordCommand
		public void decideRunForLandlord_n() {
			player = new HumanPlayer();
			run4landlord = new DecideRunForLandlordCommand(player);
			run4landlord.execute();
			assertEquals(run4landlord.getResult(), false);
		}
		
		@Test // test DecideRunForLandlordCommand
		public void decideRunForLandlord_y() {
			player = new HumanPlayer();
			run4landlord = new DecideRunForLandlordCommand(player);
			run4landlord.execute();
			assertEquals(run4landlord.getResult(), true);
		}
		
		@Test // test SetNicknameCommand
		public void setNickname_Human_first() {
			player = new HumanPlayer();
			nickname = new SetNicknameCommand(player);
			nickname.execute();
			assertEquals(player.getNickname(), new String("abc"));
		}
		
		@Test // test SetNicknameCommand
		public void setNickname_Human_second() {
			player = new HumanPlayer();
			nickname = new SetNicknameCommand(player);
			nickname.execute();
			assertEquals(player.getNickname(), new String("Foo"));
		}	
	}
	
	@Nested
	@TestMethodOrder(MethodOrderer.Alphanumeric.class)
	public class testGameBoard {
		Method method = null;
		CardRoom crPVE;
		CardRoom crPVP;
		GameBoard gbPVE;
		GameBoard gbPVP;
		
		@BeforeEach
		public void setUp() {
			crPVE = new CardRoom();
			crPVE.setType(RoomType.PVE);
			crPVE.setup();
			gbPVE = new GameBoard(crPVE);
			
			crPVP = new CardRoom();
			crPVP.setType(RoomType.PVP);
			crPVP.setup();
			gbPVP = new GameBoard(crPVP);
		}
		
		
		
		@Test // test claimLandlord
		public void claimLandlord_n_n_y() {
			try {
				method = GameBoard.class.getDeclaredMethod("claimLandlord", int.class);
				method.setAccessible(true);
			} catch (NoSuchMethodException e1) {
				e1.printStackTrace();
			} catch (SecurityException e1) {
				e1.printStackTrace();
			}
			
			int rand = new Random().nextInt(3);
			try {
				method.invoke(gbPVP, rand);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			
			assertEquals(crPVP.getLandlordID(), (rand + 2) % 3);
		}
		
		@Test // test claimLandlord
		public void claimLandlord_n_y_n() {
			try {
				method = GameBoard.class.getDeclaredMethod("claimLandlord", int.class);
				method.setAccessible(true);
			} catch (NoSuchMethodException e1) {
				e1.printStackTrace();
			} catch (SecurityException e1) {
				e1.printStackTrace();
			}
			
			int rand = new Random().nextInt(3);
			try {
				method.invoke(gbPVP, rand);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			
			assertEquals(crPVP.getLandlordID(), (rand + 1) % 3);
		}
		
		
		
		@Test // test claimLandlord
		public void claimLandlord_n_y_y_y() {
			try {
				method = GameBoard.class.getDeclaredMethod("claimLandlord", int.class);
				method.setAccessible(true);
			} catch (NoSuchMethodException e1) {
				e1.printStackTrace();
			} catch (SecurityException e1) {
				e1.printStackTrace();
			}
			
			int rand = new Random().nextInt(3);
			try {
				method.invoke(gbPVP, rand);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			
			assertEquals(crPVP.getLandlordID(), (rand + 1) % 3);
		}
		
		@Test // test claimLandlord
		public void claimLandlord_y_n_y_n() {
			try {
				method = GameBoard.class.getDeclaredMethod("claimLandlord", int.class);
				method.setAccessible(true);
			} catch (NoSuchMethodException e1) {
				e1.printStackTrace();
			} catch (SecurityException e1) {
				e1.printStackTrace();
			}
			
			int rand = new Random().nextInt(3);
			try {
				method.invoke(gbPVP, rand);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			
			assertEquals(crPVP.getLandlordID(), (rand + 2) % 3);
		}
	}
	
	@Nested
	@TestMethodOrder(MethodOrderer.Alphanumeric.class)
	class testPlayer {
		private Card[] c;
		private Player player;
		
		@BeforeEach
		// initial 17 cards
		public void setUp() {
			c = new Card[18];
			player = new HumanPlayer("nick");
			for (int i = 3; i < 18; i++)
				c[i] = new Card(Rank.getRankByValue(i), Suit.BLANK);
			List<Card> cards = Arrays.asList(c[3],c[3],c[4],c[4],c[5],c[5],c[7],c[7],c[7],
					c[8],c[8],c[8],c[8],c[9],c[10],c[11],c[12]);
			player.setCards(cards);
		}
		
		@Test // checkCardsOnHand
		public void checkCardsOnHand_33() {
			ArrayList<String> input = new ArrayList<String>();
			input.add("3");
			input.add("3");
			List<Card> expected = Arrays.asList(c[3],c[3]);
			List<Card> actual = player.checkCardsOnHand(input);
			int i = 0;
			while(i < actual.size() && actual.get(i) == expected.get(i)) 
				++i;
			assertEquals(i, expected.size());
		}
		
		@Test // checkCardsOnHand
		public void checkCardsOnHand_333() {
			ArrayList<String> input = new ArrayList<String>();
			input.add("3");
			input.add("3");
			input.add("3");
			List<Card> actual = player.checkCardsOnHand(input);
			assertEquals(actual, null);
		}
		
		@Test // checkCardsOnHand
		public void checkCardsOnHand_8888() {
			ArrayList<String> input = new ArrayList<String>();
			input.add("8");
			input.add("8");
			input.add("8");
			input.add("8");
			List<Card> expected = Arrays.asList(c[8],c[8],c[8],c[8]);
			List<Card> actual = player.checkCardsOnHand(input);
			int i = 0;
			while(i < actual.size() && actual.get(i) == expected.get(i)) 
				++i;
			assertEquals(i, expected.size());
		}
		
		@Test // checkCardsOnHand
		public void checkCardsOnHand_78910J() {
			ArrayList<String> input = new ArrayList<String>();
			input.add("7");
			input.add("8");
			input.add("9");
			input.add("10");
			input.add("J");
			List<Card> expected = Arrays.asList(c[7],c[8],c[9],c[10],c[11]);
			List<Card> actual = player.checkCardsOnHand(input);
			int i = 0;
			while(i < actual.size() && actual.get(i) == expected.get(i)) 
				++i;
			assertEquals(i, expected.size());
		}
		
		@Test // checkCardsOnHand
		public void checkCardsOnHand_K() {
			ArrayList<String> input = new ArrayList<String>();
			input.add("K");
			List<Card> actual = player.checkCardsOnHand(input);
			assertEquals(actual, null);
		}
		
		@Test // checkCardsOnHand
		public void checkCardsOnHand_JJ() {
			ArrayList<String> input = new ArrayList<String>();
			input.add("J");
			input.add("J");
			List<Card> actual = player.checkCardsOnHand(input);
			assertEquals(actual, null);
		}
		
		@Test // checkCardsOnHand
		public void checkCardsOnHand_777Q() {
			ArrayList<String> input = new ArrayList<String>();
			input.add("7");
			input.add("7");
			input.add("7");
			input.add("Q");
			List<Card> expected = Arrays.asList(c[7],c[7],c[7],c[12]);
			List<Card> actual = player.checkCardsOnHand(input);
			int i = 0;
			while(i < actual.size() && actual.get(i) == expected.get(i)) 
				++i;
			assertEquals(i, expected.size());
		}
		
		@Test // checkCardsOnHand
		public void checkCardsOnHand_BR() {
			ArrayList<String> input = new ArrayList<String>();
			input.add("B");
			input.add("R");
			List<Card> actual = player.checkCardsOnHand(input);
			assertEquals(actual, null);
		}
		
		@Test // checkCardsOnHand
		public void checkCardsOnHand_456() {
			ArrayList<String> input = new ArrayList<String>();
			input.add("4");
			input.add("5");
			input.add("6");
			List<Card> actual = player.checkCardsOnHand(input);
			assertEquals(actual, null);
		}
		
		@Test // checkCardsOnHand
		public void checkCardsOnHand_777888() {
			ArrayList<String> input = new ArrayList<String>();
			input.add("7");
			input.add("7");
			input.add("7");
			input.add("8");
			input.add("8");
			input.add("8");
			List<Card> expected = Arrays.asList(c[7],c[7],c[7],c[8],c[8],c[8]);
			List<Card> actual = player.checkCardsOnHand(input);
			int i = 0;
			while(i < actual.size() && actual.get(i) == expected.get(i)) 
				++i;
			assertEquals(i, expected.size());
		}
		
		@Test // checkCardsOnHand
		public void checkCardsOnHand_345() {
			ArrayList<String> input = new ArrayList<String>();
			input.add("3");
			input.add("4");
			input.add("5");
			List<Card> expected = Arrays.asList(c[3],c[4],c[5]);
			List<Card> actual = player.checkCardsOnHand(input);
			int i = 0;
			while(i < actual.size() && actual.get(i) == expected.get(i)) 
				++i;
			assertEquals(i, expected.size());
		}
		
		@Test // checkCardsOnHand
		public void checkCardsOnHand_788() {
			ArrayList<String> input = new ArrayList<String>();
			input.add("7");
			input.add("8");
			input.add("8");
			List<Card> expected = Arrays.asList(c[7],c[8],c[8]);
			List<Card> actual = player.checkCardsOnHand(input);
			int i = 0;
			while(i < actual.size() && actual.get(i) == expected.get(i)) 
				++i;
			assertEquals(i, expected.size());
		}
		
		@Test // removeCards
		public void removeCards_4from3456() {
			List<Card> cards = new ArrayList<Card>(Arrays.asList(c[3],c[4],c[5],c[6]));
			player.setCards(cards);
			player.removeCards(Hand.cards2hand(new ArrayList<Card>(Arrays.asList(c[4]))));
			List<Card> expected = Arrays.asList(c[3],c[5],c[6]);
			List<Card> actual = player.getCards();
			int i = 0;
			while(i < actual.size() && actual.get(i) == expected.get(i)) 
				++i;
			assertEquals(i, expected.size());
		}
		
		@Test // setNickname
		public void setNickname_abc() {
			player.askForNickname();
			assertEquals(player.getNickname(), new String("abc"));
		}
		
		@Test // decideRunForLandlord()
		public void decideRunForLandlord_n() {
			assertEquals(player.decideRunForLandlord(), false);
		}
		
		@Test // decideRunForLandlord()
		public void decideRunForLandlord_y() {
			assertEquals(player.decideRunForLandlord(), true);
		}
		
		@AfterEach
		public void endUp() {
			System.setIn(System.in);
		}
	}
}
