package entities;

public enum PokerLevel{

	LEVEL_3(3, "3"),
	
	LEVEL_4(4, "4"),
	
	LEVEL_5(5, "5"),
	
	LEVEL_6(6, "6"),
	
	LEVEL_7(7, "7"),
	
	LEVEL_8(8, "8"),
	
	LEVEL_9(9, "9"),
	
	LEVEL_10(10, "10"),
	
	LEVEL_J(11, "J"),
	
	LEVEL_Q(12, "Q"),
	
	LEVEL_K(13, "K"),
	
	LEVEL_A(14, "A"),
	
	LEVEL_2(15, "2"),
	
	LEVEL_BLACK_JOKER(16, "S"),		//小王
	
	LEVEL_RED_JOKER(17, "X")	// 大王
	;
	
	private int level;
	
	private String name;
	
	private PokerLevel(int level, String name) {
		this.level = level;
		this.name = name;
	}

	public final String getName() {
		return name;
	}

	public final int getLevel() {
		return level;
	}
}
