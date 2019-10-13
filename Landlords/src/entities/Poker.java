package entities;
public class Poker{
	
	private PokerLevel level;	//大小
	
	private PokerType type;		//花色

	public Poker(PokerLevel level, PokerType type) {
		this.level = level;
		this.type = type;
	}

	public final PokerLevel getLevel() {
		return level;
	}

	public final PokerType getType() {
		return type;
	}
	
	public boolean greater(Poker b) {	//compare the level between two poker
		//return true if this.level>b.level
		if (this.level.getLevel() >b.level.getLevel()) 
			return true;
		else 
			return false;
	}

	@Override
	public String toString() {
		return String.valueOf(level.getName()) + " ";
	}
	
}
