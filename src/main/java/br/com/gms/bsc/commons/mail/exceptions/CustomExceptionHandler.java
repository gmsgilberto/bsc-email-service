package br.com.gms.bsc.commons.mail.exceptions;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class CustomExceptionHandler {
	
	@ExceptionHandler(value = IllegalArgumentException.class)
	public ResponseEntity<ErrorResponse> hanldleIllegalArgumentException(IllegalArgumentException e){
		var errorId = getErrorId();
		var response = new ErrorResponse("Mandatory fields", e.getMessage(), 400, errorId);
		log.error(response.errorId() + " " + response.errorId());
		return ResponseEntity
					.status(400)
					.body(response);
	}
	
	
	@ExceptionHandler(value = Throwable.class)
	public ResponseEntity<ErrorResponse> handleThrowable(Throwable e){
		var errorId = getErrorId();
		var erroMessage = "It was not possible to send the email. Try again later. If the error persists, contact the IT team and provide the error code " + errorId;
		var response = new ErrorResponse("Send e-mail error", erroMessage, 500, errorId);
		log.error(response.errorId(), e);
		return ResponseEntity
					.status(500)
					.body(response);
		
	}


	private String getErrorId() {
		var errorId = "[E] " + UUID.randomUUID().toString();
		return errorId;
	}
	
}
