package entities;

import java.util.ArrayList;
import java.util.List;

import Commands.Command;

public class PlayerController {
	private List<Command> history;

	public PlayerController() {
		this.history = new ArrayList<Command>();
	};

	public void storeAndExecute(Command cmd) {
		this.history.add(cmd); // optional
		cmd.execute();
	}
}
