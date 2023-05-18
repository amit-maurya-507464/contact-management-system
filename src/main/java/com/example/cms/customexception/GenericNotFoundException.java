package com.example.cms.customexception;

public class GenericNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public GenericNotFoundException(String msg) {
		super(msg);
	}
}
