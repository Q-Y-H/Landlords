package testCases;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Random;

import entities.CardRoom;
import entities.GameBoard;
import enums.RoomType;

public class testGameBoard {
	Method method = null;
	CardRoom crPVE;
	CardRoom crPVP;
	GameBoard gbPVE;
	GameBoard gbPVP;
	
	@BeforeEach
	public void setUp() {
		crPVE = new CardRoom();
		crPVE.setType(RoomType.PVE);
		crPVE.setup();
		gbPVE = new GameBoard(crPVE);
		
		crPVP = new CardRoom();
		crPVP.setType(RoomType.PVP);
		crPVP.setup();
		gbPVP = new GameBoard(crPVP);
	}
	
	@Test // isValidInputCardNames
	public void isValidInputCardNames_789() {		
		ArrayList<String> cardnames = new ArrayList<String>();
		cardnames.add("7");
		cardnames.add("8");
		cardnames.add("9");
		
		try {
			method = GameBoard.class.getDeclaredMethod("isValidInputCardNames", ArrayList.class);
			method.setAccessible(true);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		
		try {
			assertEquals(method.invoke(gbPVE, cardnames), true);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	@Test // isValidInputCardNames
	public void isValidInputCardNames_t() {
		ArrayList<String> cardnames = new ArrayList<String>();
		cardnames.add("t");
		
		try {
			method = GameBoard.class.getDeclaredMethod("isValidInputCardNames", ArrayList.class);
			method.setAccessible(true);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		
		try {
			assertEquals(method.invoke(gbPVE, cardnames), false);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	@Test // isValidInputCardNames
	public void isValidInputCardNames_xa() {
		ArrayList<String> cardnames = new ArrayList<String>();
		cardnames.add("x");
		cardnames.add("a");
		
		try {
			method = GameBoard.class.getDeclaredMethod("isValidInputCardNames", ArrayList.class);
			method.setAccessible(true);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		
		try {
			assertEquals(method.invoke(gbPVE, cardnames), true);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	@Test // isValidInputCardNames
	public void isValidInputCardNames_jQk() {
		ArrayList<String> cardnames = new ArrayList<String>();
		cardnames.add("j");
		cardnames.add("Q");
		cardnames.add("k");
		
		try {
			method = GameBoard.class.getDeclaredMethod("isValidInputCardNames", ArrayList.class);
			method.setAccessible(true);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		
		try {
			assertEquals(method.invoke(gbPVE, cardnames), true);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	@Test // test electLandlord
	public void electLandlord_n_n_y() {
		try {
			method = GameBoard.class.getDeclaredMethod("electLandlord", int.class);
			method.setAccessible(true);
		} catch (NoSuchMethodException e1) {
			e1.printStackTrace();
		} catch (SecurityException e1) {
			e1.printStackTrace();
		}
		
		int rand = new Random().nextInt(3);
		try {
			method.invoke(gbPVP, rand);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		assertEquals(crPVP.getLandlordID(), (rand + 2) % 3);
	}
	
	@Test // test electLandlord
	public void electLandlord_n_y_n() {
		try {
			method = GameBoard.class.getDeclaredMethod("electLandlord", int.class);
			method.setAccessible(true);
		} catch (NoSuchMethodException e1) {
			e1.printStackTrace();
		} catch (SecurityException e1) {
			e1.printStackTrace();
		}
		
		int rand = new Random().nextInt(3);
		try {
			method.invoke(gbPVP, rand);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		assertEquals(crPVP.getLandlordID(), (rand + 1) % 3);
	}
	
	@Test // test setNickName
	public void setNickname_a_b_c() {
		try {
			method = GameBoard.class.getDeclaredMethod("setNickname", null);
			method.setAccessible(true);
		} catch (NoSuchMethodException e1) {
			e1.printStackTrace();
		} catch (SecurityException e1) {
			e1.printStackTrace();
		}
	
		try {
			method.invoke(gbPVP, null);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		assertEquals(crPVP.getPlayers().get(0).getNickname(), new String("a"));
		assertEquals(crPVP.getPlayers().get(1).getNickname(), new String("b"));
		assertEquals(crPVP.getPlayers().get(2).getNickname(), new String("c"));
	}
	
	@Test // test electLandlord
	public void electLandlord_n_y_y_y() {
		try {
			method = GameBoard.class.getDeclaredMethod("electLandlord", int.class);
			method.setAccessible(true);
		} catch (NoSuchMethodException e1) {
			e1.printStackTrace();
		} catch (SecurityException e1) {
			e1.printStackTrace();
		}
		
		int rand = new Random().nextInt(3);
		try {
			method.invoke(gbPVP, rand);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		assertEquals(crPVP.getLandlordID(), (rand + 1) % 3);
	}
	
	@Test // test electLandlord
	public void electLandlord_y_n_y_n() {
		try {
			method = GameBoard.class.getDeclaredMethod("electLandlord", int.class);
			method.setAccessible(true);
		} catch (NoSuchMethodException e1) {
			e1.printStackTrace();
		} catch (SecurityException e1) {
			e1.printStackTrace();
		}
		
		int rand = new Random().nextInt(3);
		try {
			method.invoke(gbPVP, rand);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		assertEquals(crPVP.getLandlordID(), (rand + 2) % 3);
	}
	
}
