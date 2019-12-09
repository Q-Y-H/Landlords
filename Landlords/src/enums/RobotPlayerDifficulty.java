package enums;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import entities.Card;
import entities.CardRoom;
import entities.Hand;
import entities.RobotPlayer;
import entities.robotDifficultyStrategy;;

public enum RobotPlayerDifficulty implements robotDifficultyStrategy{
	EASYROBOTSTRATEGY{

		@Override
		public List<Card> calculateResponse(RobotPlayer robotPlayer,LinkedList<Hand> recentHands) {
			List<Card> response=new ArrayList<Card>();
			
			// Strategies
			if (recentHands.isEmpty()
					|| recentHands.getFirst().getCards().isEmpty() && recentHands.getLast().getCards().isEmpty()) { // proactive
																													// strategy
				List<Card> returnCardsList=new ArrayList<Card>();
				returnCardsList.add(robotPlayer.getCards().get(0));
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
				response= CardRoom.hintCards(robotPlayer.getCards(), Hand.cards2hand(formerCards));
			}
			
			return response;
		}
	},
	
	MEDIUMROBOTSTRATEGY{
		@Override
		public List<Card> calculateResponse(RobotPlayer robotPlayer,LinkedList<Hand> recentHands) {
			List<Card> response=new ArrayList<Card>();
			
			// Strategies
			if (recentHands.isEmpty()
					|| recentHands.getFirst().getCards().isEmpty() && recentHands.getLast().getCards().isEmpty()) { // proactive
																													// strategy
				response = robotPlayer.playCardsProactively();
			} else { // passive strategy
				Hand lastValidHand = null;
				for (int i = recentHands.size() - 1; i >= 0; i--) { // get the last valid hand for comparison
					if (!recentHands.get(i).getCards().isEmpty()) {
						lastValidHand = recentHands.get(i);
						break;
					}
				}
				List<Card> formerCards = lastValidHand.getCards(); // get last valid cards
				response = robotPlayer.playCardsPassively(formerCards);
			}
			
			return response;
		}
	};

}
