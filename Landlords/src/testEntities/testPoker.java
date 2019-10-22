package testEntities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

<<<<<<< HEAD
import entities.Poker;
import enums.Rank;
=======
import entities.Card;
import entities.Hand;
>>>>>>> dev

import enums.Rank;
import enums.HandType;
class TestPoker {

	@Test//���ƴ�
	void Should_ReturnTure_When_RankIsHigher() {
<<<<<<< HEAD
		Poker poker1=new Poker(Rank.RANK_K, null);
		Poker poker2=new Poker(Rank.RANK_Q, null);
=======
		Card poker1=new Card(Rank.RANK_K,null);
		Card poker2=new Card(Rank.RANK_Q, null);
>>>>>>> dev
		boolean result=poker1.compareTo(poker2);
		assertEquals(result,true);
	}
	
<<<<<<< HEAD
	@Test
	void Should_ReturnFalso_When_RankIsLower() {
		Poker poker1=new Poker(Rank.RANK_3, null);
		Poker poker2=new Poker(Rank.RANK_10, null);
=======
	@Test//����С
	void Should_ReturnFalse_When_RankIsLower() {
		Card poker1=new Card(Rank.RANK_3, null);
		Card poker2=new Card(Rank.RANK_10, null);
>>>>>>> dev
		boolean result=poker1.compareTo(poker2);
		assertEquals(result,false);
	}
	
	@Test//���Ӵ�
	void compareTo_Pair_When_RankIsHigher_True_Comparison() {
		Hand hand1=new Hand(HandType.PAIR,Rank.RANK_2,2);
		Hand hand2=new Hand(HandType.PAIR,Rank.RANK_J,2);
		boolean result =true;//need to be modified(comparison between Pair)
		assertEquals(result,true);
	}
	
	@Test//����С
	void compareTo_Pair_When_RankIsLower_False_Comparison() {
		Hand hand1=new Hand(HandType.PAIR,Rank.RANK_5,2);
		Hand hand2=new Hand(HandType.PAIR,Rank.RANK_8,2);
		boolean result =false;//need to be modified(comparison between Pair)
		assertEquals(result,false);	
	}
	
	@Test//������
	void compareTo_Trio_When_RankIsHigher_True_Comparison() {
		Hand hand1=new Hand(HandType.TRIO,Rank.RANK_A,null,3);
		Hand hand2=new Hand(HandType.TRIO,Rank.RANK_K,null,3);
		boolean result =true;//need to be modified(comparison between Trio)
		assertEquals(result,true);
	}
	
	@Test//����С
	void compareTo_Trio_When_RankIsLower_False_Comparison() {
		Hand hand1=new Hand(HandType.TRIO,Rank.RANK_4,null,3);
		Hand hand2=new Hand(HandType.TRIO,Rank.RANK_7,null,3);
		boolean result =false;//need to be modified(comparison between Trio)
		assertEquals(result,false);
	}
	
	@Test//�Ĵ���
	void compareTo_Quad_When_RankIsHigher_True_Comparison() {
		Hand hand1=new Hand(HandType.QUAD,Rank.RANK_K,null,4);
		Hand hand2=new Hand(HandType.QUAD,Rank.RANK_10,null,4);
		boolean result =true;//need to be modified(comparison between Quad)
		assertEquals(result,true);
	}
	
	@Test//�Ĵ�С
	void compareTo_Quad_When_RankIsLower_False_Comparison() {
		Hand hand1=new Hand(HandType.QUAD,Rank.RANK_5,null,4);
		Hand hand2=new Hand(HandType.QUAD,Rank.RANK_9,null,4);
		boolean result =false;//need to be modified(comparison between Quad)
		assertEquals(result,false);		
	}
	
	@Test//ը����
	void compareTo_Bomb_When_RankIsHigher_True_Comparison() {
		Hand hand1=new Hand(HandType.BOMB,Rank.RANK_2,4);
		Hand hand2=new Hand(HandType.BOMB,Rank.RANK_8,4);
		boolean result =true;//need to be modified(comparison between Bomb)
		assertEquals(result,true);		
	}
	
	@Test//ը��С
	void compareTo_Bomb_When_RankIsLower_False_Comparison() {
		Hand hand1=new Hand(HandType.BOMB,Rank.RANK_5,null,4);
		Hand hand2=new Hand(HandType.BOMB,Rank.RANK_9,null,4);
		boolean result =false;//need to be modified(comparison between Bomb)
		assertEquals(result,false);		
	}
	
	@Test//˳�Ӵ�
	void compareTo_StraightOfSolo_When_RankIsHigher_True_Comparison() {
		Hand hand1=new Hand(HandType.SOLO,Rank.RANK_10,5);
		Hand hand2=new Hand(HandType.SOLO,Rank.RANK_4,5);
		boolean result =true;//need to be modified(comparison between Straight)
		assertEquals(result,true);	
	}

	@Test//˳��С
	void compareTo_StraightOfSolo_When_RankIsLower_False_Comparison() {
		Hand hand1=new Hand(HandType.SOLO,Rank.RANK_3,7);
		Hand hand2=new Hand(HandType.SOLO,Rank.RANK_8,7);
		boolean result =false;//need to be modified(comparison between Straight)
		assertEquals(result,false);	
	}

	@Test//���Դ�
	void compareTo_StraightOfPair_When_RankIsHigher_True_Comparison() {
		Hand hand1=new Hand(HandType.PAIR,Rank.RANK_10,3);
		Hand hand2=new Hand(HandType.PAIR,Rank.RANK_4,3);
		boolean result =true;//need to be modified(comparison between Straight)
		assertEquals(result,true);	
	}

	@Test//����С
	void compareTo_StraightOfPair_When_RankIsLower_False_Comparison() {
		Hand hand1=new Hand(HandType.PAIR,Rank.RANK_3,4);
		Hand hand2=new Hand(HandType.PAIR,Rank.RANK_8,4);
		boolean result =false;//need to be modified(comparison between Straight)
		assertEquals(result,false);	
	}

}
