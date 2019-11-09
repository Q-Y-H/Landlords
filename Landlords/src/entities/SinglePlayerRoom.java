package entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import enums.PlayerRole;
import helpers.Helper;
import helpers.Messenger;

public class SinglePlayerRoom extends CardRoom {
	private ArrayList<String> robotNameList=new ArrayList<String>(Arrays.asList("xyf","hgm"));
	public SinglePlayerRoom() {
		super();
	}
	
	@Override
	public void initialize() {
		Helper.shuffleCards(this.getCardCase());
		// Cut the base cards to 4 folders;
		List<List<Card>> cardLists = Helper.cutCards(this.getCardCase());
		// the last one folder for the landlord
		this.setLandlordCards(cardLists.get(3));
		
		//Initialize humanPlayer
		Scanner in = new Scanner(System.in);
		String nickname = Messenger.printAskForInput(in, "name", 
				"Player " + (0+1) + ": Please Set Your Nickname >> ");
		getPlayers().add(new HumanPlayer(nickname, PlayerRole.PEASANT));
		getPlayers().get(0).setCards(cardLists.get(0));
		Helper.sortCards(getPlayers().get(0).getCards());
		
		//Initialize
		for (int i = 1; i < 3; ++i) { // TODO: room.distributeCards() ?
			nickname = robotNameList.get(i-1);
			getPlayers().add(new RobotPlayer(nickname, PlayerRole.PEASANT));
			getPlayers().get(i).setCards(cardLists.get(i));
			Helper.sortCards(getPlayers().get(i).getCards());
		}

		this.selectLandlord();
	}
	
}
