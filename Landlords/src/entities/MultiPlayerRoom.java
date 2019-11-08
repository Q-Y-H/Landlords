package entities;

import java.util.List;

import enums.PlayerRole;
import helpers.Helper;
import helpers.Messenger;

public class MultiPlayerRoom extends CardRoom {

	@Override
	public void initialize() {
		for (int i = 0; i < 3; ++i) { // TODO: room.distributeCards() ?
			String nickname = Messenger.printAskForInput(in, "name", 
					"Player " + (i+1) + ": Please Set Your Nickname >> ");
			players.add(new Player(nickname, PlayerRole.PEASANT));
			players.get(i).setCards(cardLists.get(i));
			Helper.sortCards(players.get(i).getCards());
		}

		Helper.shuffleCards(this.getCardCase());
		// Cut the base cards to 4 folders;
		List<List<Card>> cardLists = Helper.cutCards(this.getCardCase());
		// the last one folder for the landlord
		this.setLandlordCards(cardLists.get(3));

		this.selectLandlord();
	}

}
