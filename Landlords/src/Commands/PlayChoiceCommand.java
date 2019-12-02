package Commands;

import entities.Player;

public class PlayChoiceCommand implements Command<String> {
	private Player player;
	private String result;
	
	public PlayChoiceCommand(Player player) {
		this.player = player;
	}
	
	@Override
	public void execute() {
		this.result = player.getPlayChoice();
	}

	@Override
	public String getResult() {
		return result;
	}

}
