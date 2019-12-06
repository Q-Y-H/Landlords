package entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import enums.PlayerRole;
import enums.RoomType;

public class CardRoom {

	private List<Player> players;
	private List<Card> landlordCards;
	private Player lastHandPlayer;
	private int landlordID;
	private CardCase cardCase;
	private LinkedList<Hand> handHistory;
	private LinkedList<Hand> recentHands;

	private RoomType type;

	public CardRoom() {
		this.players = new ArrayList<Player>();
		this.landlordCards = null;
		this.lastHandPlayer = null;
		this.cardCase = new CardCase();
		this.handHistory = new LinkedList<Hand>();
		this.type = null;
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

		if (this.type == RoomType.PVP) {
			for (int i = 0; i < 3; ++i)
				this.players.add(new HumanPlayer("UNDEFINED", PlayerRole.PEASANT, recentHands));
		} else {
			this.players.add(new HumanPlayer("UNDEFINED", PlayerRole.PEASANT, recentHands));
			this.players.add(new RobotPlayer("UNDEFINED", PlayerRole.PEASANT, recentHands));
			this.players.add(new RobotPlayer("UNDEFINED", PlayerRole.PEASANT, recentHands));
		}
		
		for(Player player:this.players) {
			player.setCards(cardLists.get(player.getId()%3));
		}
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
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

	public Player getLastHandPlayer() {
		return lastHandPlayer;
	}

	public void setLastHandPlayer(Player lastHandPlayer) {
		this.lastHandPlayer = lastHandPlayer;
	}

	public CardCase getCardCase() {
		return this.cardCase;
	}

	public Player getNextPlayer(Player player) {
		return this.players.get((player.getId() + 1) % 3);
	}

	public Player getPrePlayer(Player player) {
		return this.players.get((player.getId() + 2) % 3);
	}

	public RoomType getType() {
		return type;
	}

	public void setType(RoomType type) {
		this.type = type;
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
		//check bombs
		List<Card>RBJoker= new ArrayList<Card>();
		int[] numOfRanks = new int[20];
		for(Card card: cards) {
			if(card.getRank().ordinal()==14||card.getRank().ordinal()==13) {
				RBJoker.add(card);
			}
			else{
				numOfRanks[card.getRank().ordinal()+3]++;	
			}
		}			
		for(int i=0;i<numOfRanks.length;i++) {
			if(numOfRanks[i]==4) {
				List<Card> tem=new ArrayList<Card>();
				for(Card card:cards) {
					if(card.getRank().ordinal()==i-3) {
						tem.add(card);
					}
				}
				numOfRanks[i]=0;
				return tem;
			}
		}
		//Rocket
		if(RBJoker.size()==2) {
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
		if (size < 2) {
			this.recentHands = this.handHistory;
		} else {
			this.recentHands = (LinkedList<Hand>) this.handHistory.subList(size - 2, size);
		}
	}

	public LinkedList<Hand> getRecentHands() {
		return recentHands;
	}

	public static void sortCards(List<Card> cards) {
		Collections.sort(cards, Card.cardComparator);
	}
}
