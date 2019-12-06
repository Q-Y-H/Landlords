package Exceptions;

public class FirstPlayerCannotPassException extends Exception {

	private static final long serialVersionUID = 1L;

	public FirstPlayerCannotPassException(String message) {
		super(message);
	}

	public FirstPlayerCannotPassException() {
		super("You cannot pass in the first round of the game!");
	}
}
