package Exceptions;

public class DisobeyRulesException extends Exception {

	private static final long serialVersionUID = 1L;

	public DisobeyRulesException(String message) {
		super(message);
	}

	public DisobeyRulesException() {
		super("Your input doesn't meet the rules!");
	}
}
