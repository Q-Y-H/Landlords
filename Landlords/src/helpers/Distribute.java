package helpers;

import entities.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Distribute{
  
	public void washPoker(List<Card> basePoker) {
		Collections.shuffle(basePoker);
	}
	public List<List<Card>> distributeCards(List<Card> baseCards){
		List<Card> cardsOnHand1=new ArrayList<Card>(17);
		List<Card> cardsOnHand2=new ArrayList<Card>(17);
		List<Card> cardsOnHand3=new ArrayList<Card>(17);
		List<Card> cardsForLandload=new ArrayList<Card>(3);//cards for landload
		
		List<List<Card>> CardsGroup =new ArrayList<List<Card>>(4);
		
		washPoker(baseCards);
		
		for(int i=0;i<51;i++) {
			cardsOnHand1.add(baseCards.get(i));
			cardsOnHand2.add(baseCards.get(i++));
			cardsOnHand3.add(baseCards.get(i++));
		}//17*3
		for(int i=51;i<54;i++) {
			cardsForLandload.add(baseCards.get(i));
		}//3
		CardsGroup.add(cardsOnHand1);
		CardsGroup.add(cardsOnHand2);
		CardsGroup.add(cardsOnHand3);
		CardsGroup.add(cardsForLandload);
		return CardsGroup;
		
	}
}
