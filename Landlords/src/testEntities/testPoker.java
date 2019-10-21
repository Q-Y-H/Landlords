package testEntities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import entities.Poker;
import entities.Hand;

import enums.Rank;
import enums.HandType;
class TestPoker {

	@Test//单牌大
	void Should_ReturnTure_When_RankIsHigher() {
		Poker poker1=new Poker(Rank.RANK_K,null);
		Poker poker2=new Poker(Rank.RANK_Q, null);
		boolean result=poker1.compareTo(poker2);
		assertEquals(result,true);
	}
	
	@Test//单牌小
	void Should_ReturnFalse_When_RankIsLower() {
		Poker poker1=new Poker(Rank.RANK_3, null);
		Poker poker2=new Poker(Rank.RANK_10, null);
		boolean result=poker1.compareTo(poker2);
		assertEquals(result,false);
	}
	
	@Test//对子大
	void compareTo_Pair_When_RankIsHigher_True_Comparison() {
		Hand hand1=new Hand(HandType.PAIR,Rank.RANK_2,2);
		Hand hand2=new Hand(HandType.PAIR,Rank.RANK_J,2);
		boolean result =true;//need to be modified(comparison between Pair)
		assertEquals(result,true);
	}
	
	@Test//对子小
	void compareTo_Pair_When_RankIsLower_False_Comparison() {
		Hand hand1=new Hand(HandType.PAIR,Rank.RANK_5,2);
		Hand hand2=new Hand(HandType.PAIR,Rank.RANK_8,2);
		boolean result =false;//need to be modified(comparison between Pair)
		assertEquals(result,false);	
	}
	
	@Test//三带大
	void compareTo_Trio_When_RankIsHigher_True_Comparison() {
		Hand hand1=new Hand(HandType.TRIO,Rank.RANK_A,null,3);
		Hand hand2=new Hand(HandType.TRIO,Rank.RANK_K,null,3);
		boolean result =true;//need to be modified(comparison between Trio)
		assertEquals(result,true);
	}
	
	@Test//三带小
	void compareTo_Trio_When_RankIsLower_False_Comparison() {
		Hand hand1=new Hand(HandType.TRIO,Rank.RANK_4,null,3);
		Hand hand2=new Hand(HandType.TRIO,Rank.RANK_7,null,3);
		boolean result =false;//need to be modified(comparison between Trio)
		assertEquals(result,false);
	}
	
	@Test//四带大
	void compareTo_Quad_When_RankIsHigher_True_Comparison() {
		Hand hand1=new Hand(HandType.QUAD,Rank.RANK_K,null,4);
		Hand hand2=new Hand(HandType.QUAD,Rank.RANK_10,null,4);
		boolean result =true;//need to be modified(comparison between Quad)
		assertEquals(result,true);
	}
	
	@Test//四带小
	void compareTo_Quad_When_RankIsLower_False_Comparison() {
		Hand hand1=new Hand(HandType.QUAD,Rank.RANK_5,null,4);
		Hand hand2=new Hand(HandType.QUAD,Rank.RANK_9,null,4);
		boolean result =false;//need to be modified(comparison between Quad)
		assertEquals(result,false);		
	}
	
	@Test//炸弹大
	void compareTo_Bomb_When_RankIsHigher_True_Comparison() {
		Hand hand1=new Hand(HandType.BOMB,Rank.RANK_2,4);
		Hand hand2=new Hand(HandType.BOMB,Rank.RANK_8,4);
		boolean result =true;//need to be modified(comparison between Bomb)
		assertEquals(result,true);		
	}
	
	@Test//炸弹小
	void compareTo_Bomb_When_RankIsLower_False_Comparison() {
		Hand hand1=new Hand(HandType.BOMB,Rank.RANK_5,null,4);
		Hand hand2=new Hand(HandType.BOMB,Rank.RANK_9,null,4);
		boolean result =false;//need to be modified(comparison between Bomb)
		assertEquals(result,false);		
	}
	
	@Test//顺子大
	void compareTo_StraightOfSolo_When_RankIsHigher_True_Comparison() {
		Hand hand1=new Hand(HandType.SOLO,Rank.RANK_10,5);
		Hand hand2=new Hand(HandType.SOLO,Rank.RANK_4,5);
		boolean result =true;//need to be modified(comparison between Straight)
		assertEquals(result,true);	
	}

	@Test//顺子小
	void compareTo_StraightOfSolo_When_RankIsLower_False_Comparison() {
		Hand hand1=new Hand(HandType.SOLO,Rank.RANK_3,7);
		Hand hand2=new Hand(HandType.SOLO,Rank.RANK_8,7);
		boolean result =false;//need to be modified(comparison between Straight)
		assertEquals(result,false);	
	}

	@Test//连对大
	void compareTo_StraightOfPair_When_RankIsHigher_True_Comparison() {
		Hand hand1=new Hand(HandType.PAIR,Rank.RANK_10,3);
		Hand hand2=new Hand(HandType.PAIR,Rank.RANK_4,3);
		boolean result =true;//need to be modified(comparison between Straight)
		assertEquals(result,true);	
	}

	@Test//连对小
	void compareTo_StraightOfPair_When_RankIsLower_False_Comparison() {
		Hand hand1=new Hand(HandType.PAIR,Rank.RANK_3,4);
		Hand hand2=new Hand(HandType.PAIR,Rank.RANK_8,4);
		boolean result =false;//need to be modified(comparison between Straight)
		assertEquals(result,false);	
	}

}
