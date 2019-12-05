package testCases;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Commands.Command;
import Commands.DecideRunForLandlordCommand;
import Commands.SetNicknameCommand;
import entities.HumanPlayer;
import entities.Player;
import entities.RobotPlayer;

public class testCommand {
	Player player;
	Command nickname;
	Command run4landlord;
	
	@BeforeEach
	public void setUp() {
		String input = new String("n\n" + "abc\n" + "Foo\n" + "abc\n" + "n\n" + "y\n");
		ByteArrayInputStream bais = new ByteArrayInputStream(input.getBytes());
		System.setIn(bais);
		//The rebinding of System.in is restored in testPlayer.java
	}
	
	@Test // test SetNicknameCommand
	public void setNickname_Robot_0() {
		player = new RobotPlayer("robot");
		nickname = new SetNicknameCommand(player);
		nickname.execute();
		assertEquals(player.getNickname(), "Robot 0");
	}
	
	@Test // test DecideRunForLandlordCommand
	public void decideRunForLandlord_n() {
		player = new HumanPlayer();
		run4landlord = new DecideRunForLandlordCommand(player);
		run4landlord.execute();
	}
	
	@Test // test SetNicknameCommand
	public void setNickname_Human_first() {
		player = new HumanPlayer();
		nickname = new SetNicknameCommand(player);
		nickname.execute();
		assertEquals(player.getNickname(), new String("abc"));
	}
	
	@Test // test SetNicknameCommand
	public void setNickname_Human_second() {
		player = new HumanPlayer();
		nickname = new SetNicknameCommand(player);
		nickname.execute();
		assertEquals(player.getNickname(), new String("Foo"));
	}
	
	@AfterEach
	public void endUp() {
		//System.setIn(System.in);
	}
}
