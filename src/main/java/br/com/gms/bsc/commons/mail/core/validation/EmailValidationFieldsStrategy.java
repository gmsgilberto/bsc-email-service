package br.com.gms.bsc.commons.mail.core.validation;

import br.com.gms.bsc.commons.mail.core.model.EmailPrototype;
import br.com.gms.bsc.commons.mail.core.model.EmailValidationStrategy;
import br.com.gms.bsc.commons.mail.exceptions.SendEmailException;


public class EmailValidationFieldsStrategy  implements EmailValidationStrategy{
	
	private final AttachmentValidationStrategy attachmentValidationStrategy;
	
	
	public EmailValidationFieldsStrategy() {
		super();
		this.attachmentValidationStrategy = new AttachmentValidationStrategy();
	}
	
	public void execute(EmailPrototype email) {
		
		if(email == null) {
			throw new IllegalArgumentException("parameter email is null");
		}
		
		var erros = new StringBuilder();

		if(TextValidation.isBlankOrNull(email.getId())) {
			erros.append("E-mail id cannot be null").append("; ");
		}

		if(TextValidation.isBlankOrNull(email.getBody())) {
			erros.append("E-mail body cannot be null").append("; ");
		}
		
		if(TextValidation.isBlankOrNull(email.getSubject())) {
			erros.append("E-mail subject cannot be null").append("; ");
		}
		
		if(!email.hasTo()) {
			erros.append("E-mail must havet one ou mor destinations").append("; ");
		}
		
		if(!erros.isEmpty()) {
			throw new SendEmailException(erros.toString()); 
		}
		
		this.attachmentValidationStrategy.execute(email);
		
	}
	
}
