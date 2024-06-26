package com.online.banking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class OnlineBankingExceptionHandler {

	@ExceptionHandler(value = { OnlineBankingException.class })
	public ResponseEntity<Object> handleOnlineBankingException
	(OnlineBankingException onlineBankingException) {
		return ResponseEntity.status
	(onlineBankingException.getHttpStatus()).body(onlineBankingException.getMessage());

	}

	@ExceptionHandler(value = { RuntimeException.class })
	public ResponseEntity<Object> handleRuntimeException(RuntimeException runtimeException) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body("Some thing is wrong. please try again later");

	}

}
