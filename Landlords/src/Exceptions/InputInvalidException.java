package Exceptions;

public class InputInvalidException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InputInvalidException(String message) {
		super(message);
	}
	public InputInvalidException() {
		super("Input should only contain numbers from 2 to 10 and J, Q, K, A, B, R!");
	}
}
