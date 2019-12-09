package Strategies;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import entities.Card;
import entities.CardRoom;
import entities.Hand;
import entities.RobotPlayer;

public class SimpleStrategy implements RobotDecisionStrategy {
	public List<Card> playCards(RobotPlayer robot) {
		LinkedList<Hand> recentHands = robot.getRecentHands();
		List<Card> response = new ArrayList<Card>();

		// Strategies
		if (recentHands.isEmpty() || recentHands.getFirst().getCards().isEmpty() 
				&& recentHands.getLast().getCards().isEmpty()) { // Proactive Strategy
			List<Card> returnCardsList = new ArrayList<Card>();
			returnCardsList.add(robot.getCards().get(0));
			response = Hand.cards2hand(returnCardsList).getCards();
		} else { // passive strategy
			Hand lastValidHand = null;
			for (int i = recentHands.size() - 1; i >= 0; i--) { // get the last valid hand for comparison
				if (!recentHands.get(i).getCards().isEmpty()) {
					lastValidHand = recentHands.get(i);
					break;
				}
			}
			List<Card> formerCards = lastValidHand.getCards(); // get last valid cards
			response = CardRoom.hintCards(robot.getCards(), Hand.cards2hand(formerCards));
		}

		return response;
	}
}
