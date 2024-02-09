package com.bootcampASJ.tzappa.utils;

import org.springframework.validation.BindingResult;

public class ErrorHandler {
	
	public String inputValidate(BindingResult bindingResult){
		StringBuilder errors = new StringBuilder();

	    // recorremos todos los errores y los guardamos en nuestra variable
	    bindingResult.getFieldErrors().forEach((error) -> {
	        String errMsj = error.getDefaultMessage().toString();
	        errors.append(errMsj).append("\n"); 
	    });

	    // retornamos los errores (campo:mensaje)
	    return errors.toString();
	}	
}
