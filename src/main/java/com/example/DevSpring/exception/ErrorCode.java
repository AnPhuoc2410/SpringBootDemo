package com.example.DevSpring.exception;

public enum ErrorCode {
	USER_EXISTED(1001,"User Existed"),
	USERNAME_INVALID(1002,"Username must be at least 3 charater"),
	USER_PASSWORD(1003,"User password must at least 8 character"),
	USER_NOT_EXISTED(1004,"User not available")
	;
	
	
	
	private int code;
	private String message;
	
	ErrorCode(int code, String message) {
		this.code = code;
		this.message = message;
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String messsage) {
		this.message = messsage;
	}
	
}
