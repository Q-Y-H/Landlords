package Commands;

import entities.Player;

public class DecideRunForLandlordCommand implements Command<Boolean> {
	private Player player;
	private Boolean result;

	public DecideRunForLandlordCommand(Player player) {
		this.player = player;
	}

	public void execute() {
		this.result = player.decideRunForLandlord();
	}
	
	public Boolean getResult() {
		return this.result;
	}
}