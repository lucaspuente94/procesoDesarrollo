package com.proceso.desarrollo.exception;

public class CustomException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private final String code;

	public CustomException(String message) {
		super(message);
		this.code = "ERR_GENERIC";
	}

	public CustomException(String code, String message) {
		super(message);
		this.code = code;
	}

	public String getCode() {
		return code;
	}

}
