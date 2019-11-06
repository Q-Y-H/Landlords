package entities;

import java.util.ArrayList;
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
					response=
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
		//TO-DO:
		List<Card> copyData=new ArrayList<Card>(cards);//copy of handCards
		List<Card> temp=new ArrayList<Card>();
		List<List<Card>> tempCollection=new ArrayList<List<Card>>();	
		
		//1. check if has bomb yes:cut the bomb out;	
		
		combinationSelect(tempCollection,copyData,temp, 4);
		for(List<Card> combine: tempCollection) {
			if(Hand.cards2hand(combine).getType()!=HandType.BOMB) {
				tempCollection.remove(combine);
			}
		}
		for(List<Card> combine:tempCollection) {
			handList.add(Hand.cards2hand(combine));
			copyData.removeAll(combine);
		}
		temp.clear();
		tempCollection.clear();//generation
		
		//2. check if has 2 yes: cut 2s out;
		
		List<Card>collectionOf2s=new ArrayList<Card>();
		combinationSelect(tempCollection,copyData,temp, 1);
		for(List<Card> combine: tempCollection) {
			if(Hand.cards2hand(combine).getOrdinal()==2) {
				collectionOf2s.addAll(combine);
			}
		}
		handList.add(Hand.cards2hand(collectionOf2s));
		copyData.removeAll(collectionOf2s);
		
		temp.clear();
		tempCollection.clear();//generation
		
		//3. check if has plane;
		int countOf3=cards.size()/3*3;
		for(int i=countOf3;i>=6;i-=3) {
			combinationSelect(tempCollection,copyData,temp,i);
			for(List<Card> combine: tempCollection) {
				Hand tempHand=Hand.cards2hand(combine);
				if(tempHand.getType()==HandType.TRIO&&tempHand.getChainLength()==i/3) {
					handList.add(tempHand);
					copyData.removeAll(combine);
				}
			}
		}
		temp.clear();
		tempCollection.clear();
		
		//4. check if has straight of solo(always pick the longest)
		List<List<Card>> collectionOfSOS =new ArrayList<List<Card>>();
		for(int i=cards.size();i>=5;i--) {
			combinationSelect(tempCollection,copyData,temp,i);
			for(List<Card> combine: tempCollection) {
				Hand tempHand=Hand.cards2hand(combine);
				if(tempHand.getType()==HandType.SOLO) {
					//handle
					
					
					
					
					collectionOfSOS.add(combine);
					copyData.removeAll(combine);
				}
			}
		}
		
		//5. handle the SOS
			
		//5.1 若顺子中出现单牌（连续长度不限）且该单牌段长x，与顺子头部长度距离d1，尾部距离d2，满足：d1+x>=5且d2+x>=5，拆为两个顺子
			
		//5.2 顺子长度大于5，头/尾存在连对，顺子长度-连对长度>=5,转化为三带加顺子
		//5.3 顺子长度大于5，头/尾存在单牌，顺子长度-连对长度>=5,转化为对子加顺子
		//5.4 顺子周围存在多个对子/单牌。计算该区域totalHandCount，选择最小情况}直到无变化
		//
	}
	
	
	//穷尽后比较，输出满足条件的第一个List.(单牌对子ok,三带一输出为null，怀疑是三带一比较转换出现问题）另，需要添加炸弹识别
		//need to be modified. ^_^
		private static void combinationSelect(List<List<Card>> workspace,List<Card> dataList, List<Card> resultList, int length) {
			List<Card> copyData;
			List<Card> copyResult;

			if(resultList.size() == length) {
				workspace.add(resultList);
			}

			for(int i = 0; i < dataList.size(); i++) {
				copyData = new ArrayList<Card>(dataList);
				copyResult = new ArrayList<Card>(resultList);

				copyResult.add(copyData.get(i));
				for(int j = i; j >=  0; j--)
					copyData.remove(j);
				combinationSelect(workspace,copyData, copyResult, length);
			}
		}
}
