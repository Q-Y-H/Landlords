package entities;

import java.util.ArrayList;
import java.util.List;

import enums.HandType;
import enums.PlayerRole;
import enums.Rank;


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
		List<List<Card>> cardsCollection=new ArrayList<List<Card>>(20);
		List<Card>RBJoker= new ArrayList<Card>();
		int[] numOfRanks = new int[20];
		for(Card card: cards) {
			if(card.getRank()==Rank.RANK_BLACK_JOKER||card.getRank()==Rank.RANK_RED_JOKER) {
				RBJoker.add(card);
			}
			numOfRanks[card.getRank().ordinal()+3]++;	
			cardsCollection.get(card.getRank().ordinal()+3).add(card);
		}
		
		
		
		
		List<Card> copyData=new ArrayList<Card>(cards);//copy of handCards
		List<Card> temp=new ArrayList<Card>();
			
		//0. cut jokers
		
		handList.add(Hand.cards2hand(RBJoker));
		copyData.removeAll(RBJoker);
		
		//1. check if has bomb yes:cut the bomb out;
		
		for(int i=0;i<numOfRanks.length;i++) {
			if(numOfRanks[i]==4) {
				for(Card card:copyData) {
					if(card.getRank().ordinal()==i-3) {
						copyData.remove(card);
					}
				}
				handList.add(Hand.cards2hand(cardsCollection.get(i)));
				numOfRanks[i]=0;
			}
		}
		/*
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
		
		
		*/
		
		
		
		//2. check if has 2 yes: cut 2s out;
	
			for(Card card:copyData) {
				if(card.getRank()==Rank.RANK_2) {
					copyData.remove(card);
					temp.add(card);
				}
			}
			numOfRanks[12]=0;
			handList.add(Hand.cards2hand(temp));
			temp.clear();
		
		/*
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
		
		*/
		
		
		//3. check if has plane;
		
		for(int i=3;i<numOfRanks.length;i++) {
			if (numOfRanks[i]==3) {
				while (numOfRanks[i+1] == 3) {
					for(Card card:cards) {
						if(card.getRank().ordinal()==i-3||card.getRank().ordinal()==i-2) {
							temp.add(card);
							copyData.remove(card);
						}
				} 
				numOfRanks[i]=0;
				i++;
			}
				handList.add(Hand.cards2hand(temp));
				temp.clear();
			}
		}
		
		
		/*
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
		
		*/
		//4. check if has straight of solo(always pick the longest)
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
			}
			chainLength=0;
			endPoint=0;
		}
		List<StraightOfCards> ListOfSOS=new ArrayList<StraightOfCards>();
		maxStart=maxEnd-maxChain;
		for(int i=maxStart;i<=maxEnd;i++) {
			numOfRanks[i]--;
		}
		ListOfSOS.addAll(handlerOfSOS(cards,maxStart,maxEnd,numOfRanks,handList));
		/*
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
		
		
		*/
		
		//5. handle the SOS
			
		//5.1 若顺子中出现单牌（连续长度不限）且该单牌段长x，与顺子头部长度距离d1，尾部距离d2，满足：d1+x>=5且d2+x>=5，拆为两个顺子						
		//5.2 顺子长度大于5，头/尾存在连对，顺子长度-连对长度>=5,转化为三带加顺子
		//5.3 顺子长度大于5，头/尾存在单牌，顺子长度-连对长度>=5,转化为对子加顺子
		//5.4 顺子周围存在多个对子/单牌。计算该区域totalHandCount，选择最小情况}直到无变化
		//
	}
	
	private static List<StraightOfCards> handlerOfSOS(List<Card> cards,int maxStart,int maxEnd,int[]numOfRanks,List<Hand> handList) {
		List<StraightOfCards> temp=new ArrayList<StraightOfCards>();
		
		
		//5.1
		int additionLength=0;
		int additionEnd=0;
		for(int i=maxStart;i<=maxEnd;i++) {
			if(numOfRanks[i]>0) {
				additionLength++;
				additionEnd=i;
			}
			else if(additionEnd-maxStart>=5&&additionLength+maxEnd-additionEnd>=5) {
				int maxS1=maxStart;
				int maxE1=additionEnd;
				int maxS2=additionEnd-additionLength;
				int maxE2=maxEnd;
				for(int t=maxS2;t<=maxE1;t++) {
					numOfRanks[t]--;
				}
				temp.add(new StraightOfCards(maxE1-maxS1,HandType.SOLO,Rank.getRankByValue(maxE1)));
				temp.addAll(handlerOfSOS(cards,maxS1,maxE1,numOfRanks,handList));
				return temp;//
			}	
			additionLength=0;
			additionEnd=0;
		}
		
		//5.2
		if(numOfRanks[maxStart]>=2&&maxEnd-maxStart>=6) {
			numOfRanks[maxStart]=0;
			List<Card> tem=new ArrayList<Card>();
			for(Card card :cards) {
				if(card.getRank().ordinal()==maxStart-3) {
					tem.add(card);
					cards.remove(card);
				}
			}
			handList.add(Hand.cards2hand(cards));
			return handlerOfSOS(cards,maxStart+1,maxEnd,numOfRanks,handList);			
		}
		if(numOfRanks[maxEnd]>=2&&maxEnd-maxStart>=6) {
			numOfRanks[maxEnd]=0;
			List<Card> tem=new ArrayList<Card>();
			for(Card card :cards) {
				if(card.getRank().ordinal()==maxEnd-3) {
					tem.add(card);
					cards.remove(card);
				}
			}
			handList.add(Hand.cards2hand(cards));
			return handlerOfSOS(cards,maxStart,maxEnd-1,numOfRanks,handList);			
		}
		
		//5.3
		
		int point =maxEnd;
		int[] addition1=new int[maxEnd-maxStart];
		int[] addition2=new int[maxEnd-maxStart];
		if (numOfRanks[point]==1) {
			{
				addition1[point-maxStart]=point;
			} while (numOfRanks[point --] >= 1&&point-maxStart>=6) ;
		}
		
		if(maxEnd-point>=3) {
			temp.add(new StraightOfCards(maxEnd-point,HandType.PAIR,Rank.getRankByValue(maxEnd)));
			temp.addAll(handlerOfSOS(cards,maxStart,point,numOfRanks,handList));
			return temp;
		}
		for(int t=maxEnd;t>=point;t--) {
			//////////////////记得往下改！！！！！！
		}
		
		point =maxStart;
		
		
		if (numOfRanks[point]==1) {
			{
				addition2[point-maxStart]=point;
			} while (numOfRanks[point ++] >= 1&&maxEnd-point>=6) ;
		}
		
		if(point-maxStart>=3) {
			temp.add(new StraightOfCards(point-maxStart,HandType.PAIR,Rank.getRankByValue(point)));
			temp.addAll(handlerOfSOS(cards,point,maxEnd,numOfRanks,handList));
			return temp;
		}
		
		
		
		temp.add(new StraightOfCards(maxEnd-maxStart,HandType.SOLO,Rank.getRankByValue(maxEnd)));//无变化
		return temp;
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
