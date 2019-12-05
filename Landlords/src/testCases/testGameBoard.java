package testCases;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entities.CardRoom;
import entities.GameBoard;
import enums.RoomType;

public class testGameBoard {
	GameBoard gb;
	
	@BeforeEach
	public void setUp() {
		CardRoom cr = new CardRoom();
		cr.setType(RoomType.PVE);
		gb = new GameBoard(cr);
	}
	
	@Test // test setNickname
	public void setNickName_a_b_c() {
		
	}
	
	@Test // isValidInputCardNames
	public void isValidInputCardNames_789() {
		ArrayList<String> cardnames = new ArrayList<String>();
		cardnames.add("7");
		cardnames.add("8");
		cardnames.add("9");
		
		assertEquals(gb.isValidInputCardNames(cardnames), true);
	}
	
	@Test // isValidInputCardNames
	public void isValidInputCardNames_t() {
		ArrayList<String> cardnames = new ArrayList<String>();
		cardnames.add("t");
		
		assertEquals(gb.isValidInputCardNames(cardnames), false);
	}
	
	@Test // isValidInputCardNames
	public void isValidInputCardNames_xa() {
		ArrayList<String> cardnames = new ArrayList<String>();
		cardnames.add("x");
		cardnames.add("a");
		
		assertEquals(gb.isValidInputCardNames(cardnames), true);
	}
	
	@Test // isValidInputCardNames
	public void isValidInputCardNames_jQk() {
		ArrayList<String> cardnames = new ArrayList<String>();
		cardnames.add("j");
		cardnames.add("Q");
		cardnames.add("k");
		
		assertEquals(gb.isValidInputCardNames(cardnames), true);
	}
}
