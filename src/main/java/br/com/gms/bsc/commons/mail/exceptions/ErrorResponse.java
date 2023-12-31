package br.com.gms.bsc.commons.mail.exceptions;

public record ErrorResponse(String title, String description, int httpCode, String errorId) {}
