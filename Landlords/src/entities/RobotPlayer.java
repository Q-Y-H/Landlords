package entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.sun.tools.sjavac.comp.dependencies.PublicApiCollector;

import enums.HandType;
import enums.PlayerRole;
import enums.Rank;
import enums.RobotPlayerDifficulty;

public class RobotPlayer extends Player {

	/*
	 * Attribute
	 */
	private int totalHandCount = 0;
	private List<Hand> handList = new ArrayList<Hand>();
	private List<Hand> bombList = new ArrayList<Hand>();
	private List<Hand> combinationList = new ArrayList<Hand>();
	private List<Card> copyCards = new ArrayList<Card>();
	private RobotPlayerDifficulty robotPlayerDifficulty;
	
	/*
	 * Constructor
	 */
	public RobotPlayer(String nickname, PlayerRole role, LinkedList<Hand> recentHands) {
		super(nickname, role, recentHands);
		this.robotPlayerDifficulty=RobotPlayerDifficulty.MEDIUMROBOTSTRATEGY;
	}

	public RobotPlayer(String nickname) {
		super(nickname, null, null);
		this.robotPlayerDifficulty=RobotPlayerDifficulty.MEDIUMROBOTSTRATEGY;
	}
	
	public void setDifficulty(RobotPlayerDifficulty robotPlayerDifficulty) {
		this.robotPlayerDifficulty=robotPlayerDifficulty;
	}
	/*
	 * Methods
	 */

	@Override
	public void askForNickname() {
		this.setNickname("Robot " + getId() % 3);
	}

	@Override
	public Boolean decideRunForLandlord() {	//deicde run for landlords based on sum of weight of hands 	
		parseCards();
		int weightSum = 0;
		for (Hand hand : handList) {
			weightSum += hand.getWeight();
		}
		if (weightSum > 0)
			return true;
		else {
			return false;
		}
	}

	@Override
	public String getPlayChoice( ) {
		//Initialization
		List<Card> response=new ArrayList<Card>();
		parseCards();	
		calculateCombinationList();
		
		response=this.robotPlayerDifficulty.calculateResponse(this,this.recentHands);


		// Convert calculated hand response to string
		String ans = "";
		if (response.isEmpty())
			return "pass";
		for (Card card : response)
			ans += card.toString();
		return ans;
	}

	public List<Card> playCardsProactively() {
		return handList.get(0).getCards();
	}

	public List<Card> playCardsPassively(List<Card> formerCards) {
		List<Card> response = new ArrayList<Card>();
		Hand formerHand = Hand.cards2hand(formerCards);
		totalHandCount = handList.size();
		if (totalHandCount == 2 && !bombList.isEmpty()) { // situation you may take controls of others
			response = bombList.get(0).getCards();
			return response;
		}
		if (formerHand.getType() == HandType.ROCKET) { // situation you can only pass
			return new ArrayList<Card>();
		}
		for (Hand hand : handList) { // check for answers in handlist according to weigth of hands
			if (formerHand.isSmallerThan(hand) == true) {
				return hand.getCards();
			}
		}
		for (Hand hand : combinationList) { // check for answers in combinationlist according to weigth of hands
			if (formerHand.isSmallerThan(hand) == true) {
				return hand.getCards();
			}
		}
		return CardRoom.hintCards(cards, formerHand);
	}

	public String getHandList() {
		String message = "";
		for (Hand hand : handList) {
			message += hand.toString();
		}
		return message;
	}

	
	public void parseCards() {		

		handList.clear();
		bombList.clear();
		copyCards.clear();
		copyCards.addAll(cards);
		List<Card> RBJoker = new ArrayList<Card>();
		int[] numOfRanks = new int[20];
		for (Card card : copyCards) {
			if (card.getRank().ordinal() == 14 || card.getRank().ordinal() == 13) {
				RBJoker.add(card);
			} else {
				numOfRanks[card.getRank().ordinal() + 3]++;
			}
		}

		// 0. cut jokers

		if (!RBJoker.isEmpty()) {
			Hand jokerHand = Hand.cards2hand(RBJoker);
			handList.add(jokerHand);
			if (RBJoker.size() == 2) {
				bombList.add(jokerHand);
			}
		}
		copyCards.removeAll(RBJoker);

		// 1. check if has bomb yes:cut the bomb out;

		for (int i = 0; i < numOfRanks.length; i++) {
			if (numOfRanks[i] == 4) {
				List<Card> tem = new ArrayList<Card>();
				for (Card card : copyCards) {
					if (card.getRank().ordinal() == i - 3) {
						tem.add(card);
					}
				}
				copyCards.removeAll(tem);
				handList.add(Hand.cards2hand(tem));
				bombList.add(Hand.cards2hand(tem));
				numOfRanks[i] = 0;
			}
		}

		// 2. check if has 2 yes: cut 2s out;
		List<Card> tem1 = new ArrayList<Card>();
		for (Card card : copyCards) {
			if (card.getRank().ordinal() == 12) {
				tem1.add(card);
			}
		}
		numOfRanks[15] = 0;
		if (!tem1.isEmpty()) {
			copyCards.removeAll(tem1);
			handList.add(Hand.cards2hand(tem1));
		}

		// 3. check if has plane;

		for (int i = 3; i < numOfRanks.length; i++) {
			if (numOfRanks[i] == 3) {
				List<Card> tem = new ArrayList<Card>();
				while (numOfRanks[i + 1] == 3) {
					for (Card card : copyCards) {
						if (card.getRank().ordinal() == i - 3 || card.getRank().ordinal() == i - 2) {
							tem.add(card);
						}
					}
					numOfRanks[i] = 0;
					numOfRanks[i + 1] = 0;
					i++;
				}
				if (!tem.isEmpty()) {
					copyCards.removeAll(tem);
					handList.add(Hand.cards2hand(tem));
				}
			}
		}

		List<StraightOfCards> ListOfSOS = new ArrayList<StraightOfCards>();
		int t = 1;
		while (t != 0) {
			t = checkSOS(numOfRanks, ListOfSOS);
			if (!ListOfSOS.isEmpty()) {
				for (StraightOfCards zsos : ListOfSOS) {
					handList.add(Hand.cards2hand(zsos.getCards()));
				}
			}
		}

		// left cards check

		for (int i = 0; i < 20; i++) {
			if (numOfRanks[i] != 0) {
				List<Card> tem = new ArrayList<Card>();
				for (Card card : copyCards) {
					if (card.getRank().ordinal() == i - 3) {
						tem.add(card);
					}
				}
				numOfRanks[i] = 0;
				if (!tem.isEmpty()) {
					copyCards.removeAll(tem);
					handList.add(Hand.cards2hand(tem));
				}
			}
		}
		Collections.sort(handList, Hand.handComparator);
	}

	// 4. check if has straight of solo(always pick the longest)
	private int checkSOS(int[] numOfRanks, List<StraightOfCards> ListOfSOS) {
		int maxChain = 0;
		int maxStart = 0;
		int maxEnd = 0;
		int chainLength = 0;
		int endPoint = 0;
		for (int i = 3; i < numOfRanks.length; i++) {
			if (numOfRanks[i] > 0) {
				chainLength++;
				endPoint = i;
			} else if (chainLength >= 5 && chainLength > maxChain) {
				maxChain = chainLength;
				maxEnd = endPoint;
				chainLength = 0;
				endPoint = 0;
			} else {
				chainLength = 0;
				endPoint = 0;
			}
		}
		if (maxChain != 0) {
			maxStart = maxEnd - maxChain + 1;
			for (int i = maxStart; i <= maxEnd; i++) {
				numOfRanks[i]--;
			}
			ListOfSOS.addAll(handlerOfSOS(copyCards, maxStart, maxEnd, numOfRanks, handList));
		}
		return chainLength;
	}

	// 5. handle the straight of solo (sos in the later part)
	private static List<StraightOfCards> handlerOfSOS(List<Card> copyCards, int maxStart, int maxEnd, int[] numOfRanks,
			List<Hand> handList) {
		List<StraightOfCards> temp = new ArrayList<StraightOfCards>();

		// 5.1 parse sos into two straights
		int additionLength = 0;
		int additionEnd = 0;
		for (int i = maxStart; i <= maxEnd; i++) {
			if (numOfRanks[i] > 0) {
				additionLength++;
				additionEnd = i;
			} else if (additionEnd - maxStart >= 4 && additionLength + maxEnd - additionEnd >= 5) {
				int maxS1 = maxStart;
				int maxE1 = additionEnd;
				int maxS2 = additionEnd - additionLength + 1;
				int maxE2 = maxEnd;
				for (int t = maxS2; t <= maxE1; t++) {
					numOfRanks[t]--;
				}
				temp.addAll(handlerOfSOS(copyCards, maxS2, maxE2, numOfRanks, handList));
				temp.addAll(handlerOfSOS(copyCards, maxS1, maxE1, numOfRanks, handList));
				return temp;//
			} else {
				additionLength = 0;
				additionEnd = 0;
			}
		}


		// 5.2sparse sos into sos plus trio
		if (numOfRanks[maxStart] >= 2 && maxEnd - maxStart >= 5) { // check trio at the front of straight
			numOfRanks[maxStart] = 0;
			List<Card> tem = new ArrayList<Card>();
			for (Card card : copyCards) {
				if (card.getRank().ordinal() == maxStart - 3) {
					tem.add(card);

				}
			}
			if (!tem.isEmpty()) {
				copyCards.removeAll(tem);
				handList.add(Hand.cards2hand(tem));
			}
			return handlerOfSOS(copyCards, maxStart + 1, maxEnd, numOfRanks, handList);
		}
		// behind
		if (numOfRanks[maxEnd] >= 2 && maxEnd - maxStart >= 5) {
			numOfRanks[maxEnd] = 0;
			List<Card> tem = new ArrayList<Card>();
			for (Card card : copyCards) {
				if (card.getRank().ordinal() == maxEnd - 3) {
					tem.add(card);
				}
			}
			if (!tem.isEmpty()) {
				copyCards.removeAll(tem);
				handList.add(Hand.cards2hand(tem));
			}
			return handlerOfSOS(copyCards, maxStart, maxEnd - 1, numOfRanks, handList);
		}


		// 5.3 parse sos into paris and sos
		//check pairs at the front of straight
		int point = maxEnd;
		int[] addition1 = new int[maxEnd - maxStart];
		int[] addition2 = new int[maxEnd - maxStart];
		if (numOfRanks[point] == 1) {
			while (numOfRanks[point--] >= 1 && point - maxStart > 4) {
				addition1[maxEnd - point] = point;
			}
			;
			point++;
		} else {
			point = 0;
		}
		if (point != 0 && maxEnd - point >= 2) {
			List<Card> tem = new ArrayList<Card>();
			for (int t = maxEnd; t >= point; t--) {
				for (Card card : copyCards) {
					if (card.getRank().ordinal() == t - 3) {
						tem.add(card);
					}
				}
				numOfRanks[point]--;
				copyCards.removeAll(tem);
			}

			temp.add(new StraightOfCards(HandType.PAIR, Rank.getRankByValue(maxEnd), maxEnd - point + 1, tem));
			temp.addAll(handlerOfSOS(copyCards, maxStart, point - 1, numOfRanks, handList));
			return temp;
		} else if (point != 0 && point - maxStart > 4) {
			for (int t = maxEnd; t >= point; t--) {
				List<Card> tem = new ArrayList<Card>();
				for (Card card : copyCards) {
					if (card.getRank().ordinal() == t - 3) {
						tem.add(card);
					}
				}
				numOfRanks[point]--;
				if (!tem.isEmpty()) {
					copyCards.removeAll(tem);
					handList.add(Hand.cards2hand(tem));
				}
			}
			return handlerOfSOS(copyCards, maxStart, point - 1, numOfRanks, handList);
		}
		// behind
		point = maxStart;

		if (numOfRanks[point] == 1) {
			while (numOfRanks[point++] >= 1 && maxEnd - point > 4) {
				addition2[point - maxStart] = point;
			}
			;
			point--;
		} else {
			point = 0;
		}

		if (point != 0 && point - maxStart >= 2) {
			List<Card> tem = new ArrayList<Card>();
			for (int t = maxStart; t <= point; t++) {
				numOfRanks[point]--;
				for (Card card : copyCards) {
					if (card.getRank().ordinal() == t - 3) {
						tem.add(card);
					}
				}
				copyCards.removeAll(tem);
			}
			temp.add(new StraightOfCards(HandType.PAIR, Rank.getRankByValue(point), point - maxStart + 1, tem));
			temp.addAll(handlerOfSOS(copyCards, point + 1, maxEnd, numOfRanks, handList));
			return temp;
		} else if (point != 0 && maxEnd - point > 4) {
			for (int t = maxStart; t <= point; t++) {
				List<Card> tem = new ArrayList<Card>();
				for (Card card : copyCards) {
					if (card.getRank().ordinal() == t - 3) {
						tem.add(card);
					}
				}
				numOfRanks[point]--;
				if (!tem.isEmpty()) {
					copyCards.removeAll(tem);
					handList.add(Hand.cards2hand(tem));
				}
			}
			return handlerOfSOS(copyCards, point + 1, maxEnd, numOfRanks, handList);
		}

		temp.add(new StraightOfCards(HandType.SOLO, Rank.getRankByValue(maxStart), maxEnd - maxStart + 1,
				setCard(copyCards, maxStart, maxEnd)));

		return temp;
	}

	public static List<Card> setCard(List<Card> copyCards, int start, int end) {
		List<Card> tem = new ArrayList<Card>();
		for (int i = start; i <= end; i++) {
			for (Card card : copyCards) {
				if (card.getRank().ordinal() == i - 3) {
					tem.add(card);
					break;
				}
			}
		}
		copyCards.removeAll(tem);
		return tem;
	}

	private void calculateCombinationList() {
		combinationList.clear();
		List<Card> copyCards = new ArrayList<Card>();
		for (Card card : cards) {
			copyCards.clear();
			copyCards.add(card);
			combinationList.add(Hand.cards2hand(copyCards));
		}
		Hand temp1Hand = null;
		Hand temp2Hand = null;
		List<Card> temp1Cards = new ArrayList<Card>();
		List<Card> temp2Cards = new ArrayList<Card>();

		for (int i = 0; i < handList.size() - 1; i++) {
			for (int j = i + 1; j < handList.size(); j++) {
				temp2Cards.clear();
				temp1Cards.clear();
				temp1Hand = handList.get(i);
				temp2Hand = handList.get(j);
				if (temp1Hand.getChainLength() != 1 && temp1Hand.getType() == HandType.SOLO) // skip the chained-solo
					continue;
				temp1Cards.addAll(temp1Hand.getCards());
				if (temp2Hand.getChainLength() != 1 && temp2Hand.getType() == HandType.SOLO) // skip the chained-solo
					continue;
				temp2Cards.addAll(temp2Hand.getCards());
				temp1Cards.addAll(temp2Cards);
				Hand combinationHand = Hand.cards2hand(temp1Cards);
				if (combinationHand.getType() != HandType.ILLEGAL) {
					combinationList.add(combinationHand);
				}
			}
		}
		Collections.sort(combinationList, Hand.handComparator);
	}

}

class StraightOfCards extends Hand { // data structure of straight of cards for handling straight of solo

	public StraightOfCards(HandType handType, Rank startRank, int length, List<Card> cards) {
		super(handType, Rank.getRankByValue(startRank.ordinal() + 3), null, length, cards);
	}

	public List<Card> getCards() {
		return super.getCards();
	}
	
}

