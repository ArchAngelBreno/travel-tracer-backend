package com.traveltracer.service.exception;

import java.util.Arrays;
import java.util.Date;
import java.util.List;


public class ApiError {

	private int httpStatus;
	
	private String message;
	
	private List<String> errors;
	
	private Date timestamp;
	
	

	public ApiError(int httpStatus, String message, List<String> errors, Date timestamp) {
		super();
		this.httpStatus = httpStatus;
		this.message = message;
		this.errors = errors;
		this.timestamp = timestamp;
	}
	
	


	public ApiError(int httpStatus, String message, String error, Date timestamp) {
		super();
		this.httpStatus = httpStatus;
		this.message = message;
		this.errors = Arrays.asList(error);
		this.timestamp = timestamp;
	}




	public ApiError() {
		// TODO Auto-generated constructor stub
	}
	

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	

	public List<String> getErrors() {
		return errors;
	}


	public void setErrors(List<String> errors) {
		this.errors = errors;
	}


	public int getHttpStatus() {
		return httpStatus;
	}


	public void setHttpStatus(int httpStatus) {
		this.httpStatus = httpStatus;
	}


	public Date getTimestamp() {
		return timestamp;
	}


	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	
	
	
}
