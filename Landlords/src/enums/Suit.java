package enums;

public enum Suit{

	BLANK(" "),
	
	DIAMOND("♦"),
	
	CLUB("♣"),
	
	SPADE("♠"),
	
	HEART("♥")
	;
	
	private String name;

	private Suit(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name; 
	}
}
