package Commands;

import entities.Player;

public class PlayChoiceCommand implements Command<String> {
	private Player player;
	private String result;
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		this.result = player.getPlayChoice();
	}

	@Override
	public String getResult() {
		// TODO Auto-generated method stub
		return result;
	}

}
