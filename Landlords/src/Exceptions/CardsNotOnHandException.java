package Exceptions;

public class CardsNotOnHandException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CardsNotOnHandException(String message) {
		super(message);
	}
	public CardsNotOnHandException() {
		super("You should select the cards in your hand!");
	}
}
