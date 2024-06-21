package com.cts.auth.exceptions;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<String> userNotFoundExceptionHandler(UserNotFoundException e){
		
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler
	public ResponseEntity<Map<String, String>> validationFailureHandler(MethodArgumentNotValidException e){
		
		Map<String, String> errorMap=new HashMap<String, String>();
		
		List<FieldError> fieldErrors=e.getFieldErrors();
		for(FieldError fieldError :fieldErrors) {
			errorMap.put(fieldError .getField(), fieldError.getDefaultMessage());
		}
		
		return new ResponseEntity<Map<String,String>>(errorMap,HttpStatus.BAD_REQUEST);
	}
}
