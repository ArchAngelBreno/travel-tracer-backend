package com.traveltracer.service.exception;

public class BaseException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public BaseException(String msg) {
		super(msg);
	}
	
	public BaseException(String msg, Throwable cause) {
		super(msg,cause);
	}

}
