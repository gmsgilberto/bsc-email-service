package br.com.gms.bsc.commons.mail.controller.advice;

public record ErrorResponse(String title, String description, int httpCode, String errorId) {

}
