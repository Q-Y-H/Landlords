package entities;

import java.util.LinkedList;
import java.util.List;


public interface robotDifficultyStrategy {
	public List<Card> calculateResponse(RobotPlayer robotPlayer, LinkedList<Hand> recentHands);
}
