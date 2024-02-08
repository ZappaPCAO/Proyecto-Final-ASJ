package com.bootcampASJ.tzappa;

public class ExceptionCustom extends RuntimeException {
    
	private static final long serialVersionUID = 1L;

	public ExceptionCustom (String message) {
        super(message);
    }
}