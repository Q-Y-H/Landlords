/*
package testCases;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Random;

import entities.CardRoom;
import entities.GameBoard;
import enums.RoomType;

@TestMethodOrder(MethodOrderer.Alphanumeric.class)
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
	
	
	
	@Test // test claimLandlord
	public void claimLandlord_n_n_y() {
		try {
			method = GameBoard.class.getDeclaredMethod("claimLandlord", int.class);
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
	
	@Test // test claimLandlord
	public void claimLandlord_n_y_n() {
		try {
			method = GameBoard.class.getDeclaredMethod("claimLandlord", int.class);
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
	
	
	
	@Test // test claimLandlord
	public void claimLandlord_n_y_y_y() {
		try {
			method = GameBoard.class.getDeclaredMethod("claimLandlord", int.class);
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
	
	@Test // test claimLandlord
	public void claimLandlord_y_n_y_n() {
		try {
			method = GameBoard.class.getDeclaredMethod("claimLandlord", int.class);
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
*/