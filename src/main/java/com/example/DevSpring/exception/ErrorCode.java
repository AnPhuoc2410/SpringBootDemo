package com.example.DevSpring.exception;

public enum ErrorCode {
	USER_EXISTED(1001,"User Existed");
	
	
	
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
