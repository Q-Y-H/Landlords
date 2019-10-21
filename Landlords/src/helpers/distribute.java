package helpers;

import entities.Poker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class distribute{
  
	public void washPoker(List<Poker> basePoker) {
		Collections.shuffle(basePoker);
	}
	public List<List<Poker>> distributePoker(List<Poker> basePoker){
		List<Poker> pokerOnHand1=new ArrayList<Poker>(17);
		List<Poker> pokerOnHand2=new ArrayList<Poker>(17);
		List<Poker> pokerOnHand3=new ArrayList<Poker>(17);
		List<Poker> PokerForLandload=new ArrayList<Poker>(3);//Poker for landload
		
		List<List<Poker>> PokerGroup =new ArrayList<List<Poker>>(4);
		
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
