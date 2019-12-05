package Commands;

import entities.Player;

public class SetNicknameCommand implements Command<Void> {
	private Player player;

	public SetNicknameCommand(Player player) {
		this.player = player;
	}

	public void execute() {
		player.askForNickname();
	}

	@Override
	public Void getResult() {
		return null;
	}
}
