package enums;
public enum PokerType{

	BLANK(" "),
	
	DIAMOND("♦"),
	
	CLUB("♣"),
	
	SPADE("♠"),
	
	HEART("♥")
	;
	
	private String name;

	private PokerType(String name) {
		this.name = name;
	}
	
	public final String getName() {
		return name;
	}
}
