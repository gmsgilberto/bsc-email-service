/**
 * 
 */
package br.com.gms.bsc.commons.mail.exceptions;

/**
 * 
 */
public class SendEmailException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public SendEmailException(String message) {
		super(message);
	}
	
	public SendEmailException(String message, Exception e) {
		super(message, e);
	}

}
