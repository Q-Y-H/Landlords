package testCases;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Commands.Command;
import Commands.DecideRunForLandlordCommand;
import Commands.PlayChoiceCommand;
import Commands.SetNicknameCommand;
import entities.HumanPlayer;
import entities.Player;
import entities.RobotPlayer;

public class testCommand {
	Player player;
	Command<Void> nickname;
	Command<Boolean> run4landlord;
	Command<String> playChoice;
	
	@BeforeEach
	public void setUp() {
		String input = new String("help\n" + "pass\n" +  "n\n" + "y\n" +  "suggest\n" + "abc\n" + "Foo\n" +
				"n\n" + "n\n" + "n\n" + "n\n" + "n\n" + "y\n" + "n\n" + "y\n" + "n\n" + 
				"a\n" + "b\n" + "c\n" + "n\n" + "y\n" + "y\n" + "y\n" + "y\n" + "n\n" + "y\n" + "n\n" +
				"abc\n" + "n\n" + "y\n");
		ByteArrayInputStream bais = new ByteArrayInputStream(input.getBytes());
		System.setIn(bais);
		//The bond to System.in is restored in testPlayer.java
	}
	
	@Test // test SetNicknameCommand
	public void setNickname_Robot_0() {
		player = new RobotPlayer("robot");
		nickname = new SetNicknameCommand(player);
		nickname.execute();
		assertEquals(player.getNickname(), "Robot 0");
	}
	
	@Test // test PlayChoiceCommand
	public void setPlayChoice_help() {
		player = new HumanPlayer();
		playChoice = new PlayChoiceCommand(player);
		playChoice.execute();
		assertEquals(playChoice.getResult().toUpperCase(), new String("HELP"));
	}
	
	@Test // test PlayChoiceCommand
	public void setPlayChoice_pass() {
		player = new HumanPlayer();
		playChoice = new PlayChoiceCommand(player);
		playChoice.execute();
		assertEquals(playChoice.getResult().toUpperCase(), new String("PASS"));
	}
	
	@Test // test DecideRunForLandlordCommand
	public void decideRunForLandlord_n() {
		player = new HumanPlayer();
		run4landlord = new DecideRunForLandlordCommand(player);
		run4landlord.execute();
		assertEquals(run4landlord.getResult(), false);
	}
	
	@Test // test DecideRunForLandlordCommand
	public void decideRunForLandlord_y() {
		player = new HumanPlayer();
		run4landlord = new DecideRunForLandlordCommand(player);
		run4landlord.execute();
		assertEquals(run4landlord.getResult(), true);
	}
	
	@Test // test PlayChoiceCommand
	public void setPlayChoice_suggest() {
		player = new HumanPlayer();
		playChoice = new PlayChoiceCommand(player);
		playChoice.execute();
		assertEquals(playChoice.getResult().toUpperCase(), new String("SUGGEST"));
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

}
