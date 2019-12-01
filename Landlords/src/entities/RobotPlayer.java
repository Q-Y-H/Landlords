package entities;

import enums.PlayerRole;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import enums.HandType;
import enums.Rank;
import helpers.Helper;

public class RobotPlayer extends Player{
	
	/*
	 * Attribute
	 */
	private int totalHandCount=0;
	private List<Hand> handList=new ArrayList<Hand>();
	private List<Hand> bombList=new ArrayList<Hand>();
	private List<Hand> combinationList=new ArrayList<Hand>();
	private List<Card> copyCards=new ArrayList<Card>();
	private List<Card> playedCards=new ArrayList<Card>();
	/*
	 * Constructor
	 */
	public RobotPlayer(String nickname, PlayerRole role,LinkedList<Hand> handHistory) {
		super(nickname, role,handHistory);
	}

	public RobotPlayer(String nickname) {
		super(nickname,null,null);
	}

	public RobotPlayer() {
		super(null,null,null);
	}

	/*
	 * Methods
	 */
	
	@Override
	public void askForNickname() {
		this.setNickname("Robot " + getId());
	}

	@Override
	public Boolean decideRunForLandlord() {
		copyCards.clear();
		copyCards.addAll(cards);
		int valueSum=0;
		for(Hand hand: handList) valueSum+=hand.getWeight();
		if(valueSum>0) {
			return true;
		}
		else {
			return false;
		}		
	}

	@Override
	public String getPlayChoice( ) {
		//Initialization

		List<Card> response=new ArrayList<Card>();
		sparseCards();
		System.out.println("Handlist:");
		System.out.println(handList);
		calculateCombinationList();
		System.out.println("BombList:");
		System.out.println(bombList);				
		System.out.println("combinationList:");
		System.out.println(combinationList);
		System.out.println("Hand history:");
		//Strategies
		for(int i=0;i<handHistroy.size();i++) {
			if(handHistroy.get(i).getType()!=null)				
				System.out.println(handHistroy.get(i));
			else {
				System.out.println("null");
			}
		}
		if(handHistroy.isEmpty()||handHistroy.size()>2&&handHistroy.get(handHistroy.size()-1).getType()==null &&handHistroy.get(handHistroy.size()-2).getType()==null) {
			System.out.println("play proactively");
			response=playCardsProactively();
		}
		else {
			List<Card> formerCards=handHistroy.getLast().getCards();
			System.out.println("play passively");
			response=playCardsPassively(formerCards);
			System.out.println("Passive answer:"+response);
		}
		
		this.playedCards.clear();
		this.playedCards.addAll(response);
		//Convert response to answer
		if(response.isEmpty())
			return "pass";
		String ans="";
		for(Card card:response) ans+=(card.toString()+" ");
		return ans;
	}
	
	public List<Card> playCardsProactively() {
		List<Card> response = new ArrayList<Card>();
		if(totalHandCount==1) {	//Situation where you could win directly
			return handList.get(0).getCards();
		}
		response=handList.get(0).getCards();
		return response;
	}

	public List<Card> playCardsPassively(List<Card> formerCards) {
		List<Card> response = new ArrayList<Card>();
		Hand formerHand=Hand.cards2hand(formerCards);
		totalHandCount=handList.size();
		System.out.println(formerHand);
		if(totalHandCount==2 && !bombList.isEmpty()) {
			response=bombList.get(0).getCards();
			bombList.remove(0);
			return response;
		}
		if(formerHand.getType()==HandType.ROCKET){
			System.out.println("a");
			return new ArrayList<Card>();
		}
		for(Hand hand:handList) {
			if(formerHand.isSmallerThan(hand)==true) {
				System.out.println(hand);
				System.out.println("b");
				return hand.getCards();
			}				
		}
		for(Hand hand:combinationList) {
			if(formerHand.isSmallerThan(hand)==true) {
				System.out.println("c");
				return hand.getCards();
			}
		}
		for(Hand hand:bombList) {
			if(formerHand.isSmallerThan(hand)==true) {
				System.out.println("d");
				return hand.getCards();
			}
		}		
		return Helper.hintCards(cards, formerHand, formerHand.getCards().size());

	}


	public void sparseCards() {		
		
		List<Card>RBJoker= new ArrayList<Card>();
		int[] numOfRanks = new int[20];
		for(Card card: copyCards) {
			if(card.getRank().ordinal()==14||card.getRank().ordinal()==13) {
				RBJoker.add(card);
			}
			else{
				numOfRanks[card.getRank().ordinal()+3]++;	
			}
		}		
		
		
		//0. cut jokers
		
		if (!RBJoker.isEmpty()) {
			Hand jokerHand=Hand.cards2hand(RBJoker);
			handList.add(jokerHand);
			if (RBJoker.size()==2) {
				bombList.add(jokerHand);
			}
		}
		copyCards.removeAll(RBJoker);
		
		//1. check if has bomb yes:cut the bomb out;
		
		for(int i=0;i<numOfRanks.length;i++) {
			if(numOfRanks[i]==4) {
				List<Card> tem=new ArrayList<Card>();
				for(Card card:copyCards) {
					if(card.getRank().ordinal()==i-3) {
						tem.add(card);
					}
				}
				copyCards.removeAll(tem);
				handList.add(Hand.cards2hand(tem));
				bombList.add(Hand.cards2hand(tem));
				numOfRanks[i]=0;
			}
		}
				
		
		//2. check if has 2 yes: cut 2s out;
		List<Card> tem1=new ArrayList<Card>();
		for(Card card:copyCards) {
			if(card.getRank().ordinal()==12) {
				tem1.add(card);
			}
		}
		numOfRanks[15]=0;
		copyCards.removeAll(tem1);
		handList.add(Hand.cards2hand(tem1));
		tem1.clear();
		
		
		//3. check if has plane;
		
		for(int i=3;i<numOfRanks.length;i++) {
			if (numOfRanks[i]==3) {
				List<Card> tem =new ArrayList<Card>();
				while (numOfRanks[i+1] == 3) {
					for(Card card:copyCards) {
						if(card.getRank().ordinal()==i-3||card.getRank().ordinal()==i-2) {
							tem.add(card);
						}
				} 
					numOfRanks[i]=0;
					numOfRanks[i+1]=0;
					i++;
				}
				if (!tem.isEmpty()) {
					copyCards.removeAll(tem);
					handList.add(Hand.cards2hand(tem));
					tem.clear();
				}
			}
		}
		
		List<StraightOfCards> ListOfSOS = new ArrayList<StraightOfCards>();
		int t=1;
		while (t!=0) {
			t=checkSOS(numOfRanks, ListOfSOS);
			if (!ListOfSOS.isEmpty()) {
				for (StraightOfCards zsos : ListOfSOS) {
					handList.add(Hand.cards2hand(zsos.getCards()));
				}
			} 
		}
		
		// left cards check
		
		for(int i=0;i<20;i++) {
			if(numOfRanks[i]!=0) {
				List<Card> tem =new ArrayList<Card>();
				for(Card card:copyCards) {
					if(card.getRank().ordinal()==i-3) {
						tem.add(card);
					}
				}
				copyCards.removeAll(tem);
				handList.add(Hand.cards2hand(tem));
			}
		}
		Collections.sort(handList, Hand.handComparator);
	}	
	
	//4. check if has straight of solo(always pick the longest)
	private int checkSOS(int[] numOfRanks,List<StraightOfCards> ListOfSOS) {
		int maxChain=0;
		int maxStart=0;
		int maxEnd=0;
		int chainLength=0;
		int endPoint=0;
		for(int i=3;i<numOfRanks.length;i++) {
			if(numOfRanks[i]>0) {
				chainLength++;
				endPoint=i;
			}
			else if(chainLength>=5&&chainLength>maxChain){
				maxChain=chainLength;
				maxEnd=endPoint;
				chainLength=0;
				endPoint=0;
			}else {
				chainLength=0;
				endPoint=0;
			}
		}
		if (maxChain!=0) {
			maxStart = maxEnd - maxChain+1;
			for (int i = maxStart; i <= maxEnd; i++) {
				numOfRanks[i]--;
			}			
			ListOfSOS.addAll(handlerOfSOS(copyCards, maxStart, maxEnd, numOfRanks, handList));
		}
		return chainLength;							
	}
	
	
	//5. handle the SOS
	private static List<StraightOfCards> handlerOfSOS(List<Card> copyCards,int maxStart,int maxEnd,int[]numOfRanks,List<Hand> handList) {
		List<StraightOfCards> temp=new ArrayList<StraightOfCards>();
		
		
		//5.1若顺子中出现单牌（连续长度不限）且该单牌段长x，与顺子头部长度距离d1，尾部距离d2，满足：d1+x>=5且d2+x>=5，拆为两个顺子
		int additionLength=0;
		int additionEnd=0;
		for(int i=maxStart;i<=maxEnd;i++) {
			if(numOfRanks[i]>0) {
				additionLength++;
				additionEnd=i;
			}
			else if(additionEnd-maxStart>=4&&additionLength+maxEnd-additionEnd>=4) {
				int maxS1=maxStart;
				int maxE1=additionEnd;
				int maxS2=additionEnd-additionLength+1;
				int maxE2=maxEnd;
				for(int t=maxS2;t<=maxE1;t++) {
					numOfRanks[t]--;
				}
				temp.addAll(handlerOfSOS(copyCards,maxS2,maxE2,numOfRanks,handList));
				temp.addAll(handlerOfSOS(copyCards,maxS1,maxE1,numOfRanks,handList));
				return temp;//
			}else {	
				additionLength=0;
				additionEnd=0;
			}
		}
		
		//5.2 顺子长度大于5，头/尾存在连对，顺子长度-连对长度>=5,转化为三带加顺子
		if(numOfRanks[maxStart]>=2&&maxEnd-maxStart>=4) {
			numOfRanks[maxStart]=0;
			List<Card> tem=new ArrayList<Card>();
			for(Card card :copyCards) {
				if(card.getRank().ordinal()==maxStart-3) {
					tem.add(card);

				}
			}
			copyCards.removeAll(tem);
			handList.add(Hand.cards2hand(tem));
			return handlerOfSOS(copyCards,maxStart+1,maxEnd,numOfRanks,handList);			
		}
		if(numOfRanks[maxEnd]>=2&&maxEnd-maxStart>=5) {
			numOfRanks[maxEnd]=0;
			List<Card> tem=new ArrayList<Card>();
			for(Card card :copyCards) {
				if(card.getRank().ordinal()==maxEnd-3) {
					tem.add(card);
				}
			}
			copyCards.removeAll(tem);
			handList.add(Hand.cards2hand(tem));
			return handlerOfSOS(copyCards,maxStart,maxEnd-1,numOfRanks,handList);			
		}
		
		//5.3 顺子长度大于5，头/尾存在单牌，顺子长度-连对长度>=5,转化为对子加顺子
		
		int point =maxEnd;
		int[] addition1=new int[maxEnd-maxStart];
		int[] addition2=new int[maxEnd-maxStart];
		if (numOfRanks[point]==1) {
			while (numOfRanks[point --] >= 1&&point-maxStart>4){
				addition1[maxEnd-point]=point;
			}  ;
			point++;
		}else {
			point=0;
		}
		if(point!=0&&maxEnd-point>=2) {
			List<Card> tem =new ArrayList<Card>();
			for(int t=maxEnd;t>=point;t--) {
				for(Card card : copyCards) {
					if(card.getRank().ordinal()==t-3) {
						tem.add(card);
					}
				}
				numOfRanks[point]--;
				copyCards.removeAll(tem);
			}

			temp.add(new StraightOfCards(HandType.PAIR,Rank.getRankByValue(maxEnd),maxEnd-point+1,tem));
			temp.addAll(handlerOfSOS(copyCards,maxStart,point-1,numOfRanks,handList));
			return temp;
		}else if (point!=0){
			for(int t=maxEnd;t>=point;t--) {
				List<Card> tem=new ArrayList<Card>();
				for(Card card : copyCards) {
					if(card.getRank().ordinal()==t-3) {
						tem.add(card);
					}
				}
				numOfRanks[point]--;
				copyCards.removeAll(tem);
				handList.add(Hand.cards2hand(tem));
			}
			return handlerOfSOS(copyCards,maxStart,point-1,numOfRanks,handList);
		}
		//behind
		
		
		
		point =maxStart;
		
		
		if (numOfRanks[point]==1) {
			while (numOfRanks[point ++] >= 1&&maxEnd-point>4){
				addition2[point-maxStart]=point;
			}  ;
			point--;
		}else {
			point=0;
		}
	
		if(point!=0&&point-maxStart>=2) {
			List<Card> tem=new ArrayList<Card>();
			for(int t=maxStart;t<=point;t++) {
				numOfRanks[point]--;
				for(Card card : copyCards) {
					if(card.getRank().ordinal()==t-3) {
						tem.add(card);
					}
				}
				copyCards.removeAll(tem);
			}
			temp.add(new StraightOfCards(HandType.PAIR,Rank.getRankByValue(point),point-maxStart+1,tem));
			temp.addAll(handlerOfSOS(copyCards,point+1,maxEnd,numOfRanks,handList));
			return temp;
		}else if(point!=0) {
			for(int t=maxStart;t<=point;t++) {
				List<Card>tem=new ArrayList<Card>();
				for(Card card : copyCards) {
					if(card.getRank().ordinal()==t-3) {
						tem.add(card);
					}
				}
				numOfRanks[point]--;
				copyCards.removeAll(tem);
				handList.add(Hand.cards2hand(tem));
			}
			return handlerOfSOS(copyCards,point+1,maxEnd,numOfRanks,handList);
		}
		
		temp.add(new StraightOfCards(HandType.SOLO,Rank.getRankByValue(maxEnd),maxEnd-maxStart,setCard(copyCards,maxStart+1,maxEnd)));//鏃犲彉鍖�

		return temp;
	}
	
	public static List<Card> setCard(List<Card> copyCards,int start,int end){
		List<Card> tem=new ArrayList<Card>();
		for(int i=start;i<=end;i++) {
			for(Card card: copyCards) {
				if(card.getRank().ordinal()==i-3) {
					tem.add(card);
					break;
				}
			}
		}
		copyCards.removeAll(tem);
		return tem;
	}
	
	
	private void calculateCombinationList() {
		Hand temp1Hand;
		Hand temp2Hand;
		List<Card> temp1Cards=new ArrayList<Card>();
		List<Card> temp2Cards=new ArrayList<Card>();
		for(int i=0;i<handList.size()-1;i++) {
			temp1Cards.clear();
			temp1Hand=handList.get(i);
			if(temp1Hand.getType()==HandType.SOLO&&temp1Hand.getChainLength()!=1)	// skip the chained-solo
				continue;
			temp1Cards=temp1Hand.getCards();
			for(int j=i+1;j<handList.size();j++) {
				temp2Cards.clear();
				temp2Hand=handList.get(j);
				if(temp2Hand.getType()==HandType.SOLO&&temp2Hand.getChainLength()!=1) // skip the chained-solo
					continue;
				temp2Cards=temp2Hand.getCards();
				temp1Cards.addAll(temp2Cards);
				Hand combinationHand=Hand.cards2hand(temp1Cards);
				if(combinationHand.getType()!=HandType.ILLEGAL) {
					combinationList.add(combinationHand);
				}
			}
		}
		Collections.sort(combinationList, Hand.handComparator);
	}
	
}


	
	
	
	