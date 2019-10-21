package helpers;

import entities.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class distribute{
  
	public void washPoker(List<Card> basePoker) {
		Collections.shuffle(basePoker);
	}
	public List<List<Card>> distributePoker(List<Card> basePoker){
		List<Card> pokerOnHand1=new ArrayList<Card>(17);
		List<Card> pokerOnHand2=new ArrayList<Card>(17);
		List<Card> pokerOnHand3=new ArrayList<Card>(17);
		List<Card> PokerForLandload=new ArrayList<Card>(3);//Poker for landload
		
		List<List<Card>> PokerGroup =new ArrayList<List<Card>>(4);
		
		washPoker(basePoker);
		
		for(int i=0;i<51;i++) {
			pokerOnHand1.add(basePoker.get(i));
			pokerOnHand2.add(basePoker.get(i++));
			pokerOnHand3.add(basePoker.get(i++));
		}//17*3
		for(int i=51;i<54;i++) {
			PokerForLandload.add(basePoker.get(i));
		}//3
		PokerGroup.add(pokerOnHand1);
		PokerGroup.add(pokerOnHand2);
		PokerGroup.add(pokerOnHand3);
		PokerGroup.add(PokerForLandload);
		return PokerGroup;
		
	}
}
