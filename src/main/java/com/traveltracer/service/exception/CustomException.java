package com.traveltracer.service.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomException extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public final ResponseEntity<ApiError> handleNotFoundException(ObjectNotFoundException ex, WebRequest request) {
		ApiError exceptionResponse = new ApiError(HttpStatus.NOT_FOUND.value(), ex.getMessage(),
				request.getDescription(false), new Date());
		return new ResponseEntity<ApiError>(exceptionResponse, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(UnprocessableEntityException.class)
	public final ResponseEntity<ApiError> handleNotFoundException(UnprocessableEntityException ex, WebRequest request) {
		ApiError exceptionResponse = new ApiError(HttpStatus.UNPROCESSABLE_ENTITY.value(), ex.getMessage(),
				request.getDescription(false), new Date());
		return new ResponseEntity<ApiError>(exceptionResponse, HttpStatus.UNPROCESSABLE_ENTITY);
	}
}
