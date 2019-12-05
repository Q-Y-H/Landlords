package testCases;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
}
