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
	
	public void setRoomType() {
		/*
		 * Prompt for user type
		 */		
		String input="";
		do {
			Messenger.print("Do you want to play mutiplayer or solo?[M/S]");
			input=in.nextLine().toUpperCase();
		}
		while(!(input.equals("M")||input.equals("S")));
		if(input.equals("M")) {
			this.room = new MultiPlayerRoom();
		}
		else {
			this.room=new SinglePlayerRoom();
		}
	}
	
	public static void main(String args[]) {
		LandlordGame game = new LandlordGame();
		
	}
}
