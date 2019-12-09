package Strategies;

import java.util.List;

import entities.Card;
import entities.RobotPlayer;


public interface RobotDecisionStrategy {
	public List<Card> playCards(RobotPlayer robotPlayer);
}
