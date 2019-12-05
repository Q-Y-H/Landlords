import entities.CardRoom;
import entities.GameBoard;
import enums.RoomType;
import helpers.Messenger;

public class LandlordGame {
	private GameBoard gameBoard;

	public LandlordGame() {
		CardRoom room = new CardRoom();
		Messenger.getInstance().print("Welcome to the Landlord Poker Game!\n");
		askForRoomType(room);
		gameBoard = new GameBoard(room);
	}

	public void askForRoomType(CardRoom room) {
		String prompt = "Please select the game mode:\n";
		prompt += "1. Player v.s. Player (PvP)\n";
		prompt += "2. Player v.s. Environment(Robot) (PvE)\n";
		prompt += "Your choice >> ";
		String input = Messenger.getInstance().askForInput(prompt, new String[] { "1", "2" }, false);
		if (input.equals("1"))
			room.setType(RoomType.PVP);
		else if (input.equals("2"))
			room.setType(RoomType.PVE);
	}

	public void start() {
		gameBoard.run();
	}

	public static void main(String args[]) {
		LandlordGame game = new LandlordGame();
		game.start();
	}
}
