package entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import Commands.Command;
import Commands.DecideRunForLandlordCommand;
import Commands.PlayChoiceCommand;
import Commands.SetNicknameCommand;
import Exceptions.CardsNotOnHandException;
import Exceptions.DisobeyRulesException;
import Exceptions.FirstPlayerCannotPassException;
import Exceptions.InputInvalidException;
import Strategies.MediumStrategy;
import enums.HandType;
import enums.PlayerRole;
import enums.Rank;
import enums.RoomType;

public class CardRoom {

	private List<Player> players;
	private List<Card> landlordCards;
	private int lastHandPlayerID;
	private int landlordID;
	private CardCase cardCase;
	private PlayerController playerController;
	private LinkedList<Hand> handHistory = new LinkedList<Hand>();
	private LinkedList<Hand> recentHands = new LinkedList<Hand>();
	private RoomType roomType;

	public CardRoom() {
		this.players = new ArrayList<Player>();
		this.landlordCards = null;
		this.lastHandPlayerID = -1;
		this.cardCase = new CardCase();
		this.handHistory = new LinkedList<Hand>();
		this.playerController = new PlayerController();
		this.roomType = null;
		updateRecentHands();
	}

	public void setup() {
		shuffleCards();

		// Cut the base cards into 4 portions;
		List<List<Card>> cardLists = cutCards();

		// Sort cards
		for (List<Card> cards : cardLists) {
			CardRoom.sortCards(cards);
		}

		// The last one portion for the landlord
		this.landlordCards = cardLists.get(3);

		if (this.roomType == RoomType.PVP) {
			for (int i = 0; i < 3; ++i)
				this.players.add(new HumanPlayer("UNDEFINED", PlayerRole.PEASANT, recentHands));
		} else {
			this.players.add(new HumanPlayer("UNDEFINED", PlayerRole.PEASANT, recentHands));
			this.players.add(new RobotPlayer("UNDEFINED", PlayerRole.PEASANT, recentHands, new MediumStrategy()));
			this.players.add(new RobotPlayer("UNDEFINED", PlayerRole.PEASANT, recentHands, new MediumStrategy()));
		}

		for (Player player : this.players) {
			player.setCards(cardLists.get(player.getId() % 3));
		}
	}
	
	public RoomType getRoomType() {
		return roomType;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public List<Card> getLandlordCards() {
		return landlordCards;
	}

	public void setLandlordCards(List<Card> landlordCards) {
		this.landlordCards = landlordCards;
	}

	public int getLandlordID() {
		return landlordID;
	}

	public void setLandlordID(int landlordID) {
		this.landlordID = landlordID;
	}

	public void setLastHandPlayer(int lastHandPlayerID) {
		this.lastHandPlayerID = lastHandPlayerID;
	}

	public void setType(RoomType type) {
		this.roomType = type;
	}

	public LinkedList<Hand> getHandHistory() {
		return handHistory;
	}

	public void setHandHistory(LinkedList<Hand> handHistory) {
		this.handHistory = handHistory;
	}

	public void shuffleCards() {
		Collections.shuffle(cardCase.getBaseCards());
	}

	public List<List<Card>> cutCards() {
		List<List<Card>> cardGroups = new ArrayList<List<Card>>(4);

		for (int i = 0; i < 3; i++) {
			cardGroups.add(new ArrayList<Card>(17));
		}

		for (int i = 0; i < 51; i++) {
			cardGroups.get(i % 3).add(cardCase.getBaseCards().get(i));
		}

		List<Card> landloadCards = new ArrayList<Card>(3);
		landloadCards.addAll(cardCase.getBaseCards().subList(51, 54));
		cardGroups.add(landloadCards);

		return cardGroups;
	}

	public static List<Card> hintCards(List<Card> cards, Hand prev) {

		List<Card> TempCards = new ArrayList<Card>();
		List<List<Card>> workspace = new ArrayList<List<Card>>();
		combinationSelect(workspace, cards, TempCards, prev.getCards().size());
		for (List<Card> c : workspace) {
			Hand tempHand = Hand.cards2hand(c);
			if (prev.isSmallerThan(tempHand) == true) {
				return c;
			}
		}
		// check bombs
		List<Card> RBJoker = new ArrayList<Card>();
		int[] numOfRanks = new int[20];
		for (Card card : cards) {
			if (card.getRank().ordinal() == 14 || card.getRank().ordinal() == 13) {
				RBJoker.add(card);
			} else {
				numOfRanks[card.getRank().ordinal() + 3]++;
			}
		}
		for (int i = 0; i < numOfRanks.length; i++) {
			if (numOfRanks[i] == 4) {
				List<Card> tem = new ArrayList<Card>();
				for (Card card : cards) {
					if (card.getRank().ordinal() == i - 3) {
						tem.add(card);
					}
				}
				numOfRanks[i] = 0;
				return tem;
			}
		}
		// Rocket
		if (RBJoker.size() == 2) {
			return RBJoker;
		}
		return new ArrayList<Card>();
	}

	private static void combinationSelect(List<List<Card>> workspace, List<Card> dataList, List<Card> resultList,
			int length) {
		List<Card> copyData;
		List<Card> copyResult;

		if (resultList.size() == length) {
			workspace.add(resultList);
		}

		for (int i = 0; i < dataList.size(); i++) {
			copyData = new ArrayList<Card>(dataList);
			copyResult = new ArrayList<Card>(resultList);

			copyResult.add(copyData.get(i));
			for (int j = i; j >= 0; j--)
				copyData.remove(j);
			combinationSelect(workspace, copyData, copyResult, length);
		}
	}

	public void updateRecentHands() {
		int size = this.handHistory.size();
		if (size == 0) {
			return;
		} else if (size < 2) {
			recentHands.add(handHistory.get(size - 1));
		} else {
			recentHands.clear();
			recentHands.add(handHistory.get(size - 2));
			recentHands.add(handHistory.get(size - 1));
		}
	}

	public LinkedList<Hand> getRecentHands() {
		return recentHands;
	}

	public static void sortCards(List<Card> cards) {
		Collections.sort(cards, Card.cardComparator);
	}

	public void askForNicknames() {
		for (Player player : this.players) {
			this.playerController.storeAndExecute(new SetNicknameCommand(player));
		}
	}

	public boolean askForClaimLandlord(int cursor) {
		Player player = this.players.get(cursor);
		Command<Boolean> runForLandlord = new DecideRunForLandlordCommand(player);
		this.playerController.storeAndExecute(runForLandlord);
		return runForLandlord.getResult();
	}

	public void updateLandlord(int landlordID) {
		this.landlordID = landlordID;
		this.players.get(landlordID).setRole(PlayerRole.LANDLORD);
		this.players.get(landlordID).getCards().addAll(this.landlordCards);
		CardRoom.sortCards(players.get(landlordID).getCards());
	}

	public String askForPlayChoice(int cursor) {
		Player player = this.players.get(cursor);
		Command<String> playChoiceCommand = new PlayChoiceCommand(player);
		this.playerController.storeAndExecute(playChoiceCommand);
		return playChoiceCommand.getResult();
	}

	public boolean processPlayerChoice(int cursor, String cmd) {
		Player player = players.get(cursor);
		Messenger messenger = Messenger.getInstance();

		try {
			if (cmd.toUpperCase().equals("PASS")) {
				if (this.lastHandPlayerID == -1 || this.lastHandPlayerID == cursor) {
					throw new FirstPlayerCannotPassException();
				} else {
					handHistory.add(Hand.cards2hand(new ArrayList<Card>()));
					return true;
				}
			} else {
				ArrayList<String> inputCardNames = new ArrayList<String>();
				Scanner cmdScanner = new Scanner(cmd);
				while (cmdScanner.hasNext())
					inputCardNames.add(cmdScanner.next());
				cmdScanner.close();

				if (inputCardNames.size() == 0 || !isValidInputCardNames(inputCardNames)) {
					throw new InputInvalidException();
				}

				List<Card> selectedCards = player.checkCardsOnHand(inputCardNames);
				if (selectedCards == null) {
					throw new CardsNotOnHandException();
				}

				Hand currHand = Hand.cards2hand(selectedCards);
				if (currHand.getType() == HandType.ILLEGAL) {
					throw new DisobeyRulesException();
				}

				if (this.lastHandPlayerID == -1 || this.lastHandPlayerID == cursor
						|| this.getLastValidHand().isSmallerThan(currHand)) {
					player.removeCards(selectedCards);
					handHistory.add(currHand);
					this.lastHandPlayerID = cursor;
					return true;
				} else {
					throw new DisobeyRulesException();
				}
			}
		} catch (InputInvalidException e) {
			messenger.println(e.getMessage());
			return false;
		} catch (CardsNotOnHandException e) {
			messenger.println(e.getMessage());
			return false;
		} catch (DisobeyRulesException e) {
			messenger.println(e.getMessage());
			return false;
		} catch (FirstPlayerCannotPassException e) {
			messenger.println(e.getMessage());
			return false;
		}
	}
	
	public PlayerRole checkLastPlayerRole() {
		return players.get(lastHandPlayerID).getRole();
	}

	public boolean checkFinished(int cursor) {
		Player player = this.players.get(cursor);
		return player.getCards().size() == 0;
	}

	private boolean isValidInputCardNames(ArrayList<String> cardNames) {
		for (String cardName : cardNames) {
			if (!Rank.aliasSetContains(cardName))
				return false;
		}
		return true;
	}

	private Hand getLastValidHand() {
		Hand lastHand = this.recentHands.getLast();
		if (!lastHand.isPass())
			return lastHand;
		else
			return this.recentHands.getFirst();
	}
}
