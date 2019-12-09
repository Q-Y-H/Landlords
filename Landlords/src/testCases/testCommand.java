/*
package testCases;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import Commands.Command;
import Commands.DecideRunForLandlordCommand;
import Commands.PlayChoiceCommand;
import Commands.SetNicknameCommand;
import entities.HumanPlayer;
import entities.Player;
import entities.RobotPlayer;

@TestMethodOrder(MethodOrderer.Alphanumeric.class)
public class testCommand {
	private Player player;
	private Command<Void> nickname;
	private Command<Boolean> run4landlord;
	private Command<String> playChoice;
	
	@Test // test SetNicknameCommand
	public void setNickname_Robot_0() {
		player = new RobotPlayer("robot");
		nickname = new SetNicknameCommand(player);
		nickname.execute();
		assertEquals(player.getNickname(), "Robot 1");
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
}*/
