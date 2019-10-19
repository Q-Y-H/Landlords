package enums;

public enum PokerRank{

	RANK_3(3, "3"),
	
	RANK_4(4, "4"),
	
	RANK_5(5, "5"),
	
	RANK_6(6, "6"),
	
	RANK_7(7, "7"),
	
	RANK_8(8, "8"),
	
	RANK_9(9, "9"),
	
	RANK_10(10, "10"),
	
	RANK_J(11, "J"),
	
	RANK_Q(12, "Q"),
	
	RANK_K(13, "K"),
	
	RANK_A(14, "A"),
	
	RANK_2(15, "2"),
	
	RANK_BLACK_JOKER(16, "B"),	//小王
	
	RANK_RED_JOKER(17, "R")		// 大王
	;
	
	private int rank;
	
	private String name;
	
	private PokerRank(int rank, String name) {
		this.rank = rank;
		this.name = name;
	}

	public final String getName() {
		return name;
	}

	public final int getRank() {
		return rank;
	}
}
