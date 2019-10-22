package entities;

import java.util.ArrayList;
import java.util.List;

import enums.PlayerRole;


public class Player {

	private List<Card> pokers = new ArrayList<Card>();
	private PlayerRole role;
	

	public Player() {
		this.pokers = null;
		this.setRole(null);
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
}
