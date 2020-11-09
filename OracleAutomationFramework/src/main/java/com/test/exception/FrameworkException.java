package com.test.exception;

@SuppressWarnings("serial")

public class FrameworkException extends Exception {

	public FrameworkException() {
		super();
	}

	public FrameworkException(String message) {
		super(message);
	}

	public FrameworkException(String message, Exception e) {
		super(message, e);
	}

	public FrameworkException(Exception e) {
		super(e);
	}
}
