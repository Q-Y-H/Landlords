package enums;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public enum Rank{
	
	RANK_3("3", new String[]{"3"}), // TODO: Maybe 'value' is not necessary?
	
	RANK_4("4", new String[]{"4"}),
	
	RANK_5("5", new String[]{"5"}),
	
	RANK_6("6", new String[]{"6"}),
	
	RANK_7("7", new String[]{"7"}),
	
	RANK_8("8", new String[]{"8"}),
	
	RANK_9("9", new String[]{"9"}),
	
	RANK_10("10", new String[]{"10", "x", "X"}),
	
	RANK_J("J", new String[]{"J", "j"}),
	
	RANK_Q("Q", new String[]{"Q", "q"}),
	
	RANK_K("K", new String[]{"K", "k"}),
	
	RANK_A("A", new String[]{"A", "a", "1"}),
	
	RANK_2("2", new String[]{"2"}),
	
	RANK_BLACK_JOKER("B", new String[]{"B", "b"}),
	
	RANK_RED_JOKER("R", new String[]{"R", "r"}),
	;
	
	private String name;
	private String[] alias;
	private static Set<String> aliasSet = new HashSet<String>();
	
	private Rank(String name, String[] alias) {
		this.name = name;
		this.alias = alias;
	}
	
	public String getName() {
		return name;
	}
	
	public static Set<String> getAliasSet() {
		if (aliasSet.isEmpty())
			for(Rank r: Rank.values()) {
				aliasSet.addAll(Arrays.asList(r.alias));
			}
		return aliasSet;
	}
	
	public static boolean aliasContains(String key) {
		return getAliasSet().contains(key);
	}

	public static Rank getRankByValue(int value) {
		for(Rank r: Rank.values()) 
			if(r.ordinal()+3 == value) return r;
		return null;
	}
	
	public static Rank getRankByName(String name) {
		for (Rank r: Rank.values())
			if (Arrays.asList(r.alias).contains(name)) return r;
		return null;
	}
}
