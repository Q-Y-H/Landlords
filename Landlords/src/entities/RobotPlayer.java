package entities;

import java.util.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;

import enums.HandType;
import enums.PlayerRole;
import enums.Rank;
import enums.Suit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import entities.*;
import enums.*;
import junit.framework.TestCase;

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
		int[] numOfCards = new int[20];
		for(Card card: cards) numOfCards[card.getRank().ordinal()+3]++;
		
		if(totalHandCount==2 && hasBomb) {
			
		}
		else {
		switch(lastHandType){
		case ROCKET:{
			break;
		}
		case BOMB:{
			for (int i=3;i<18;i++) {
				if(numOfCards[i]==4 && i>lastHand.getOrdinal())

			}
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
			if(lastHand.getChainLength()==1) {
				
			}
			else {
				
			}
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
		List<Card> cardListForSparsing=new ArrayList<>(cards);
		List<Hand> optimizingHandList;
		this.handList=null;
		stepsToWin=sparseCards(optimizingHandList,cardListForSparsing,0);
	}
	
	public int sparseCards(List<Hand> optimizingHandList,List<Card> cardListForSparsing,int formerSteps) {
		int steps=formerSteps;	
		int countTrio=0;
		int countPair=0;
		int countSolo=0;
		if(cardListForSparsing.isEmpty())
			return 0;
		// Sparse Cards with minimum finishing steps
		int[] numOfCards = new int[20];
		for(Card card: cardListForSparsing) numOfCards[card.getRank().ordinal()+3]++;		
		
		if(numOfCards[16]!=0&&numOfCards[17]!=0) {
			//Check Rocket
			List <Card> bombList=Arrays.asList(c[17],c[16]);
			optimizingHandList.add(Hand.cards2hand(bombList));
			for(Card card:bombList) cardListForSparsing.remove(card);
			steps+=1;
		}
		
		for(int rank=0; rank<numOfCards.length; rank++) {
			// Check Bomb
			if(numOfCards[rank] == 4) {
				List <Card> bombList=null;
				for(int j=0;j<4;j++) bombList.add(c[rank]);
				optimizingHandList.add(Hand.cards2hand(bombList));
				for(Card card:bombList)cardListForSparsing.remove(card);
				steps+=1;
			}
			else if(numOfCards[rank]==3) {
				countTrio++;
			}
			else if(numOfCards[rank]==2) {
				countPair++;
			}
			else if(numOfCards[rank]==1) {
				countSolo++;
			}
		}
		
		// Check plane
		if(countTrio>=2) {
			for(int rank=0; rank<numOfCards.length-1; rank++) {
				if(numOfCards[rank]==3&&numOfCards[rank+1]==3)
				List<Card> planeList=null;
				List<Card> newSparseList=null;
				//addPlane;
				int tempStemp=sparseCards(optimizingHandList,newSparseList,steps);
				if() {
					
				}
			}
		}
		// Check 连对
		// Check three+one
		// Check 顺子
		
		// Check solo and pair
		int[] numOfCards1 = new int[20];
		for(Card card: cardListForSparsing) numOfCards1[card.getRank().ordinal()+3]++;
		for(int rank=0; rank<numOfCards1.length; rank++) {
			if(numOfCards1[rank] != 0) {
				steps++;
				List<Card> boringList=null;
				for(int i=0;i<numOfCards1[rank];i++) boringList.add(c[rank]);
				optimizingHandList.add(Hand.cards2hand(boringList));
				for(Card card:boringList)cardListForSparsing.remove(card);
			}
		}
		return steps;
	}
	
	public int sortAccordingToWeight() {
		
	
	}
	
	public int calculateAccordingToWeight() {
		
	}
	
	private Card[] c = new Card[20];
	

	@BeforeEach
 	//initial 17 cards
    public void setUp() {
	 	for(int i=3;i<18;i++)
	 		c[i] = new Card(Rank.getRankByValue(i),Suit.BLANK);
    
}
