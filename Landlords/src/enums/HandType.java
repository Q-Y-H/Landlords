package enums;

public enum HandType {
	ILLEGAL, 
	SOLO, 
	PAIR, 
	TRIO, 
	QUAD, 
	BOMB, 
	ROCKET,
	;
	
	public static HandType getHandType(int value) {
		for(HandType h: HandType.values())
			if(value == h.ordinal()) return h;
		return null;
	}
}
