package helpers;

import entities.Poker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class distribute{
  
	public void washPoker(List<Poker> basePoker) {
		Collections.shuffle(basePoker);
	}
	private List<Poker> pokerOnHand1=new ArrayList<Poker>(17);
	private List<Poker> pokerOnHand2=new ArrayList<Poker>(17);
	private List<Poker> pokerOnHand3=new ArrayList<Poker>(17);
	private List<Poker> PokerForLandload=new ArrayList<Poker>(3);
	
	private List<List<Poker>> PokerGroup =new ArrayList<List<Poker>>(4);
	public List<List<Poker>> distributePoker(List<Poker> basePoker){
		washPoker(basePoker);
		
		for(int i=0;i<51;i++) {
			pokerOnHand1.add(basePoker.get(i));
			pokerOnHand2.add(basePoker.get(i++));
			pokerOnHand3.add(basePoker.get(i++));
		}
		for(int i=51;i<54;i++) {
			PokerForLandload.add(basePoker.get(i));
		}
		PokerGroup.add(pokerOnHand1);
		PokerGroup.add(pokerOnHand2);
		PokerGroup.add(pokerOnHand3);
		PokerGroup.add(PokerForLandload);
		return PokerGroup;
		
	}
}
