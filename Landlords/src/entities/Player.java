package entities;

import java.util.ArrayList;
import java.util.List;

import javax.management.relation.Role;

import enums.PlayerRole;


public class Player {

	private static int idCounter = 0;
	private int id;
	private String nickname;
	private PlayerRole role;
	private List<Card> pokers = new ArrayList<Card>();

	public Player(String nickname, PlayerRole role) {
		this.setId(idCounter);
		this.nickname = nickname;
		this.role = role;
		this.pokers = null;
		
		++Player.idCounter;
	}
	
	public Player(String nickname) {
		this(nickname, null);
	}
	
	public Player() {
		this(null, null);
	}

	public PlayerRole getRole() {
		return role;
	}

	public void setRole(PlayerRole role) {
		this.role = role;
	}

	public List<Card> getPokers() {
		return pokers;
	}

	public void setPokers(List<Card> pokers) {
		this.pokers = pokers;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Card> checkCardsOnHand(ArrayList<String> cardNames) {
		// TODO Auto-generated method stub
		return null;
	}

	public void playCards(List<Card> cards) {
		// TODO Auto-generated method stub
		
	}

}
