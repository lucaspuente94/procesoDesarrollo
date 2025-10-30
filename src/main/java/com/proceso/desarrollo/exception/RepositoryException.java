package com.proceso.desarrollo.exception;

public class RepositoryException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private final String code;

	public RepositoryException(String message) {
		super(message);
		this.code = "ERR_DATABASE";
	}

	public RepositoryException(String code, String message) {
		super(message);
		this.code = code;
	}

	public String getCode() {
		return code;
	}
	
}
