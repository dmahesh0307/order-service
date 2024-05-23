package com.example.demo;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ApplicationExceptionHandler {

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(ResourceNotFoundException.class)
	public ErrorDetails handleResourceNotFoundException (ResourceNotFoundException ex ,WebRequest wr) {
		return new ErrorDetails( new Date(),ex.getMessage(),wr.getDescription(false));
		
	}
	
	
	
}
