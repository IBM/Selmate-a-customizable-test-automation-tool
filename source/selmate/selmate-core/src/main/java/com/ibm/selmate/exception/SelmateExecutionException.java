
package com.ibm.selmate.exception;

public class SelmateExecutionException extends SelmateException {

	public SelmateExecutionException() {
		super();
	}

	public SelmateExecutionException(String message) {
		super(message);
	}

	public SelmateExecutionException(String message, Throwable t) {
		super(message, t);
	}

	public SelmateExecutionException(Throwable t) {
		super(t);
	}

}
