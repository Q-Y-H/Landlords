package entities;

import java.util.List;

import enums.HandType;
import enums.PlayerRole;

public class RobotPlayer extends Player {
	
	/*
	 * Inherited constructor
	 */
	public RobotPlayer(String nickname, PlayerRole role) {
		super(nickname, role);
	}

	public RobotPlayer(String nickname) {
		super(nickname);
	}

	public RobotPlayer() {
	}
	
	
	/*
	 * Attributes
	 */
	private int totalHandCount=0;
	private int stepsToWin=0;
	private List<Hand> handList=null;
	private boolean hasBomb=false;
	/*
	 * Methods
	 */
	
	public List<Card> playCards(List<Card> formerCards) {
		List<Card> response=null;
		if(formerCards==null)
			response=playCardsProactively(formerCards);
		else
			response=playCardsPassively(formerCards);
		this.removeCards(response);
		return response;
	}
	
	public List<Card> playCardsProactively(List<Card> formerCards) {
		List<Card> response = null;
		sparseCards();
		if(totalHandCount==1) {	//Situation where you could win directly
			this.removeCards(handList.get(0));
			return handList.get(0).getCards();
		}
		return response;
	}
	
	public List<Card> playCardsPassively(List<Card> formerCards) {
		List<Card> response = null;
		sparseCards();
		Hand lastHand=Hand.cards2hand(formerCards);
		HandType lastHandType=lastHand.getType();

		if(totalHandCount==2 && hasBomb) {
			
		}
		else {
		switch(lastHandType){
		case ROCKET:{
			break;
		}
		case BOMB:{
			
		}
		case SOLO:{
			if(lastHand.getChainLength()==1) {
				
			}
			else {
				
			}
		}
		case PAIR:{
			if(lastHand.getChainLength()==1) {
				
			}
			else {
				
			}
		}
		case TRIO:{
			
		}
		case QUAD:{
			
		}
		case ILLEGAL:
			break;
		default:
			break;
		}
		}
		return response;
	}
	
	public void sparseCards() {
		
	}
}
