package com.bootcampASJ.tzappa.utils;

public class ExceptionCustom extends RuntimeException {
    
	private static final long serialVersionUID = 1L;

	public ExceptionCustom (String message) {
        super(message);
    }
}