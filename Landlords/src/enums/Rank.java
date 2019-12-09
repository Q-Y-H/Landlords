package enums;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public enum Rank {

	RANK_3(3, "3", new String[] { "3" }),

	RANK_4(4, "4", new String[] { "4" }),

	RANK_5(5, "5", new String[] { "5" }),

	RANK_6(6, "6", new String[] { "6" }),

	RANK_7(7, "7", new String[] { "7" }),

	RANK_8(8, "8", new String[] { "8" }),

	RANK_9(9, "9", new String[] { "9" }),

	RANK_10(10, "10", new String[] { "10", "x", "X" }),

	RANK_J(11, "J", new String[] { "J", "j" }),

	RANK_Q(12, "Q", new String[] { "Q", "q" }),

	RANK_K(13, "K", new String[] { "K", "k" }),

	RANK_A(14, "A", new String[] { "A", "a", "1" }),

	RANK_2(15, "2", new String[] { "2" }),

	RANK_BLACK_JOKER(16, "B", new String[] { "B", "b" }),

	RANK_RED_JOKER(17, "R", new String[] { "R", "r" }),;

	private int value;
	private String name;
	private String[] alias;
	private static Set<String> aliasSet = new HashSet<String>();

	private Rank(int value, String name, String[] alias) {
		this.value = value;
		this.name = name;
		this.alias = alias;
	}

	public String getName() {
		return name;
	}

	public static Set<String> getAliasSet() {
		if (aliasSet.isEmpty())
			for (Rank r : Rank.values()) {
				aliasSet.addAll(Arrays.asList(r.alias));
			}
		return aliasSet;
	}

	public static boolean aliasSetContains(String key) {
		return getAliasSet().contains(key);
	}

	public boolean hasAlias(String key) {
		return Arrays.asList(this.alias).contains(key);
	}

	public static Rank getRankByValue(int value) {
		for (Rank r : Rank.values())
			if (r.ordinal() + 3 == value)
				return r;
		return null;
	}

	public static Rank getRankByName(String name) {
		for (Rank r : Rank.values())
			if (Arrays.asList(r.alias).contains(name))
				return r;
		return null;
	}
}
