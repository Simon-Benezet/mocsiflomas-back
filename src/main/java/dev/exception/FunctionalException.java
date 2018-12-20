package dev.exception;

public class FunctionalException extends Exception {

	public FunctionalException() {
	}

	public FunctionalException(String message) {
		super(message);
	}

	public FunctionalException(Throwable cause) {
		super(cause);
	}

	public FunctionalException(String message, Throwable cause) {
		super(message, cause);
	}

	public FunctionalException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
