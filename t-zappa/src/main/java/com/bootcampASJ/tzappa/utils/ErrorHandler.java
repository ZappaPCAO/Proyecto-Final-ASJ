package com.bootcampASJ.tzappa.utils;

import org.springframework.validation.BindingResult;

public class ErrorHandler {
	
	public String inputValidate(BindingResult bindingResult){
		StringBuilder errors = new StringBuilder();

	    bindingResult.getFieldErrors().forEach((error) -> {
	        String errMsj = error.getDefaultMessage().toString();
	        errors.append(errMsj).append("\n"); 
	    });

	    return errors.toString();
	}	
}
