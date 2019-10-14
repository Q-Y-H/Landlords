package enums;

public enum PokerSuit{

	BLANK(" "),
	
	DIAMOND("♦"),
	
	CLUB("♣"),
	
	SPADE("♠"),
	
	HEART("♥")
	;
	
	private String name;

	private PokerSuit(String name) {
		this.name = name;
	}
	
	public final String getName() {
		return name;
	}
}
