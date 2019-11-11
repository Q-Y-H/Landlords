import entities.CardRoom;

public class LandlordGame {
	public LandlordGame() {
		initGame();
	}
	
	public void initGame() {
		CardRoom room = new CardRoom();
		GameBoard gameBoard = new GameBoard(room);
		gameBoard.run();
	}
	
	public static void main(String args[]) {
		LandlordGame game = new LandlordGame();
		
	}
}
