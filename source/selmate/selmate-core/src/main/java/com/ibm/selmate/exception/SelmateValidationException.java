
package com.ibm.selmate.exception;

public class SelmateValidationException extends SelmateException {

	public SelmateValidationException() {
		super();
	}

	public SelmateValidationException(String message) {
		super(message);
	}

	public SelmateValidationException(String message, Throwable t) {
		super(message, t);
	}

	public SelmateValidationException(Throwable t) {
		super(t);
	}

}
