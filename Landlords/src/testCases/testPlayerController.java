package testCases;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Commands.PlayChoiceCommand;
import Commands.SetNicknameCommand;
import entities.HumanPlayer;
import entities.Player;
import entities.PlayerController;
import entities.RobotPlayer;

public class testPlayerController {
	PlayerController pc;
	
	@BeforeEach
	public void setUp() {
		pc = new PlayerController();
	}
	
	@Test // storeAndExecute
	public void storeAndExecute_PlayChoiceCommand() {
		Player player = new HumanPlayer();
		PlayChoiceCommand playChoice= new PlayChoiceCommand(player);
		playChoice.execute();
		assertEquals(playChoice.getResult().toUpperCase(), new String("PASS"));
	}
	
	@Test // storeAndExecute
	public void storeAndExecute_SetNickNameCommand() {
		Player player = new HumanPlayer();
		SetNicknameCommand nickname = new SetNicknameCommand(player);
		pc.storeAndExecute(nickname);
		assertEquals(player.getNickname(), new String("abc"));
	}
}
