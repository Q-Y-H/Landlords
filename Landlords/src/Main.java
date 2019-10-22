import entities.Player;
import entities.PokerRoom;

public class Main {
	
	public static void main(String[] args) {

		PokerRoom room = new PokerRoom();
		for (int i=0; i<3; ++i) {
			room.getPlayers().add(new Player());			
		}
		
		
	}
}
