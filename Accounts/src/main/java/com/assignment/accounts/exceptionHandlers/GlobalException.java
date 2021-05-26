package com.assignment.accounts.exceptionHandlers;

import java.util.NoSuchElementException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class GlobalException extends ResponseEntityExceptionHandler{

	@ExceptionHandler(NoSuchFieldException.class)
	public ResponseEntity<String> handleNoSuchFieldException(NoSuchFieldException exception){
		return new ResponseEntity<String>("Problem with data field, Please check your request.", HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException exception){
		return new ResponseEntity<String>("No such element present, Please check your request.", HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<String> handleNullPointerException(NullPointerException exception){
		return new ResponseEntity<String>("No value present, Please check your request.", HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<String> handleIllegalArgumentException(NullPointerException exception){
		return new ResponseEntity<String>("No value present, Please check your request.", HttpStatus.BAD_REQUEST);
		
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		// TODO Auto-generated method stub
		return new ResponseEntity<Object>("Validation Failed, Please check your request.", HttpStatus.BAD_REQUEST);
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return new ResponseEntity<Object>("Wrong HTTP request. Please check and send again", HttpStatus.BAD_REQUEST);
	}	
	
	
}
