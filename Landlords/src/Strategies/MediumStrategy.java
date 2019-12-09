package Strategies;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import entities.Card;
import entities.Hand;
import entities.RobotPlayer;

public class MediumStrategy implements RobotDecisionStrategy{
	public List<Card> playCards(RobotPlayer robot) {
		LinkedList<Hand> recentHands = robot.getRecentHands();
		List<Card> response=new ArrayList<Card>();
		
		// Strategies
		if (recentHands.isEmpty() || recentHands.getFirst().getCards().isEmpty() 
				&& recentHands.getLast().getCards().isEmpty()) { // Proactive strategy
			response = robot.playCardsProactively();
		} else { // passive strategy
			Hand lastValidHand = null;
			for (int i = recentHands.size() - 1; i >= 0; i--) { // get the last valid hand for comparison
				if (!recentHands.get(i).getCards().isEmpty()) {
					lastValidHand = recentHands.get(i);
					break;
				}
			}
			List<Card> formerCards = lastValidHand.getCards(); // get last valid cards
			response = robot.playCardsPassively(formerCards);
		}
		
		return response;
	}
}
