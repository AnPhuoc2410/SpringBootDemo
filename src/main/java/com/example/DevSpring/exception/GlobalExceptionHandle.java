package com.example.DevSpring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.DevSpring.dto.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandle {
	
	@ExceptionHandler(value = RuntimeException.class)
	ResponseEntity<ApiResponse> handlingRuntimeException(RuntimeException exception){
		ApiResponse apiResponse = new ApiResponse();
		
		apiResponse.setCode(1001);
		apiResponse.setMessage(exception.getMessage());
		
		return ResponseEntity.badRequest().body(apiResponse);
	}
	
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
		ResponseEntity<String> handlingValidation(MethodArgumentNotValidException exception){
			return ResponseEntity.badRequest().body(exception.getFieldError().getDefaultMessage());
		}
	
	
}
