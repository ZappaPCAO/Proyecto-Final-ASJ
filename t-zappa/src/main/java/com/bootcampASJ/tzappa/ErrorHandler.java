package com.bootcampASJ.tzappa;

import java.util.HashMap;
import java.util.Map;

import org.springframework.validation.BindingResult;

public class ErrorHandler {
	
	public Map<String, String> inputValidate(BindingResult bindingResult){
		
		Map<String, String> errors = new HashMap<>();
		
		bindingResult.getFieldErrors().forEach((error) -> {
			errors.put(error.getField(), error.getDefaultMessage());
		});
		
		return errors;		
	}
	
}
