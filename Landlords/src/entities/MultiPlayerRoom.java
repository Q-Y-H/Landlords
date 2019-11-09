package entities;

import java.util.List;
import java.util.Scanner;

import enums.PlayerRole;
import helpers.Helper;
import helpers.Messenger;

public class MultiPlayerRoom extends CardRoom {

	@Override
	public void initialize() {
		Helper.shuffleCards(this.getCardCase());
		// Cut the base cards to 4 folders;
		List<List<Card>> cardLists = Helper.cutCards(this.getCardCase());
		// the last one folder for the landlord
		this.setLandlordCards(cardLists.get(3));
		
		for (int i = 0; i < 3; ++i) { // TODO: room.distributeCards() ?
			Scanner in = new Scanner(System.in);
			String nickname = Messenger.printAskForInput(in, "name", 
					"Player " + (i+1) + ": Please Set Your Nickname >> ");
			getPlayers().add(new HumanPlayer(nickname, PlayerRole.PEASANT));
			getPlayers().get(i).setCards(cardLists.get(i));
			Helper.sortCards(getPlayers().get(i).getCards());
		}



		this.selectLandlord();
	}

}
