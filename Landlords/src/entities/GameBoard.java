package entities;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import Commands.Command;
import Commands.DecideRunForLandlordCommand;
import Commands.PlayChoiceCommand;
import Commands.SetNicknameCommand;
import Exceptions.CardsNotOnHandException;
import Exceptions.DisobeyRulesException;
import Exceptions.FirstPlayerCannotPassException;
import Exceptions.InputInvalidException;
import enums.HandType;
import enums.PlayerRole;
import enums.Rank;

public class GameBoard {
	private CardRoom room;
	private PlayerController playerController;
	private Messenger messenger;

	public GameBoard(CardRoom room) {
		this.room = room;
		this.playerController = new PlayerController();
		this.messenger = Messenger.getInstance();
	}

	public void run() {
		room.setup();
		setNickname();
		claimLandlord();
		gameStart();
		checkWinner();
	}

	private void setNickname() {
		for (Player player : room.getPlayers()) {
			this.playerController.storeAndExecute(new SetNicknameCommand(player));
		}
	}

	private void claimLandlord(int initCursor) {
		List<Player> players = this.room.getPlayers();
		List<Boolean> choices = new ArrayList<Boolean>();
		int currCursor = initCursor;
		int landlordID = 0;
		int nWaive = 0;

		for (int i = 0; i < 4; ++i) {
			if (i == 3) {
				if (nWaive == 3) { // all waive
					landlordID = new Random().nextInt(3);
					break;
				} else if (nWaive == 2) // two players waive
					break;
				else if (nWaive == 1 && !choices.get(0)) // one player waives
					currCursor = (currCursor + 1) % 3;
				// all run for landlord: give the chance to the first player
			}

			Player player = players.get(currCursor);
			this.messenger.handleRunForLandlord(choices, players, currCursor, initCursor);
			Command<Boolean> runForLandlord = new DecideRunForLandlordCommand(player);
			this.playerController.storeAndExecute(runForLandlord);

			Boolean isRunningForLandlord = runForLandlord.getResult();
			choices.add(isRunningForLandlord);
			if (isRunningForLandlord)
				landlordID = currCursor;
			else
				++nWaive;

			currCursor = (currCursor + 1) % 3;
		}

		room.setLandlordID(landlordID);
		players.get(landlordID).setRole(PlayerRole.LANDLORD);
		players.get(landlordID).getCards().addAll(this.room.getLandlordCards());
		CardRoom.sortCards(players.get(landlordID).getCards());

		this.messenger.clear();
		this.messenger.println("The landlord is Player " + players.get(landlordID).getNickname());
		this.messenger.println("Landlord cards:");
		this.messenger.println(this.messenger.printCards(this.room.getLandlordCards()));
		this.messenger.waiting();
	}
	
	private void claimLandlord() {
		claimLandlord(new Random().nextInt(3));
	}

	public void gameStart() {
		boolean isFinish = false;
		int cursor = room.getLandlordID();
		List<Player> players = this.room.getPlayers();
		LinkedList<Hand> handHistory = this.room.getHandHistory();
		Hand lastValidHand = null;

		this.messenger.clear();
		this.messenger.print("===========\n");
		this.messenger.print("Game Start!\n");
		this.messenger.print("===========\n");

		while (!isFinish) {
			Player player = players.get(cursor);
			this.messenger.waitForPlayer(player);
			this.messenger.clear();
			this.messenger.print(this.messenger.prevPlayersInfo(cursor, this.room));
			boolean isValidInput = false;

			while (!isValidInput) {
				try {
					Command<String> playChoiceCommand = new PlayChoiceCommand(player);
					this.playerController.storeAndExecute(playChoiceCommand);
					String cmd = playChoiceCommand.getResult();
					
					if (cmd.toUpperCase().equals("PASS")) {
						if (room.getLastHandPlayer() == null || room.getLastHandPlayer() == player) {
							throw new FirstPlayerCannotPassException();
						} else {
							handHistory.add(Hand.cards2hand(new ArrayList<Card>()));
							isValidInput = true;
						}
					} else {
						ArrayList<String> inputCardNames = new ArrayList<String>();
						Scanner cmdScanner = new Scanner(cmd);
						while (cmdScanner.hasNext())
							inputCardNames.add(cmdScanner.next());
						cmdScanner.close();

						if (inputCardNames.size() == 0 || !isValidInputCardNames(inputCardNames)) {
							throw new InputInvalidException();
						}

						List<Card> selectedCards = player.checkCardsOnHand(inputCardNames);
						if (selectedCards == null) {
							throw new CardsNotOnHandException();
						}

						Hand currHand = Hand.cards2hand(selectedCards);
						if (currHand.getType() == HandType.ILLEGAL) {
							throw new DisobeyRulesException();
						}

						if (room.getLastHandPlayer() == null || room.getLastHandPlayer() == player
								|| lastValidHand.isSmallerThan(currHand)) {
							player.removeCards(selectedCards);
							handHistory.add(currHand);
							lastValidHand = currHand;
							room.setLastHandPlayer(player);
							isValidInput = true;
						} else {
							throw new DisobeyRulesException();
						}

					}
				} catch (InputInvalidException e) {
					this.messenger.println(e.getMessage());
				} catch (CardsNotOnHandException e) {
					this.messenger.println(e.getMessage());
				} catch (DisobeyRulesException e) {
					this.messenger.println(e.getMessage());
				} catch (FirstPlayerCannotPassException e) {
					this.messenger.println(e.getMessage());
				}
			}

			if (!handHistory.getLast().getCards().isEmpty())
				this.messenger.println(this.messenger.printCards(handHistory.getLast().getCards()));

			this.messenger.waiting();
			this.messenger.clear();

			// check finish
			if (player.getCards().size() == 0)
				isFinish = true;

			// update active player and recentHands
			cursor = (cursor + 1) % 3;
			room.updateRecentHands();
		}
	}

	private boolean isValidInputCardNames(ArrayList<String> cardNames) {
		for (String cardName : cardNames) {
			if (!Rank.aliasSetContains(cardName))
				return false;
		}
		return true;
	}

	private void checkWinner() {
		if (room.getLastHandPlayer().getRole() == PlayerRole.LANDLORD) {
			this.messenger.println("==============");
			this.messenger.println("Landlord wins!");
			this.messenger.println("==============");
		}else {
			this.messenger.println("==============");
			this.messenger.println("Peasants win!");
			this.messenger.println("==============");
		}
		this.messenger.waiting();
	}
}
