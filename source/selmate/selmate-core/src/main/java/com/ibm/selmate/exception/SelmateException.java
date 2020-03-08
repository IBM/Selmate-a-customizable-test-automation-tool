
package com.ibm.selmate.exception;

public class SelmateException extends Exception {

	public SelmateException() {
		super();
	}

	public SelmateException(String message) {
		super(message);
	}

	public SelmateException(String message, Throwable t) {
		super(message, t);
	}

	public SelmateException(Throwable t) {
		super(t);
	}

}
