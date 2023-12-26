package br.com.gms.bsc.commons.mail.model;

import br.com.gms.bsc.commons.mail.exceptions.SendEmailException;
import br.com.gms.bsc.commons.mail.util.TextValidation;
import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
class EmailValidationStrategy {
	
	@NonNull
	private Email email;
	
	void validate() {
		
		var erros = new StringBuilder();
		
		if(TextValidation.isBlankOrNull(email.getBody())) {
			erros.append("E-mail body cannot be null").append("\n");
		}
		
		if(TextValidation.isBlankOrNull(email.getSubject())) {
			erros.append("E-mail subject cannot be null").append("\n");
		}
		
		if(email.getTo() == null || email.getTo().length == 0) {
			erros.append("E-mail must havet one ou mor destinations").append("\n");
		}
		
		if(!erros.isEmpty()) {
			throw new SendEmailException(erros.toString()); 
		}
		
		if(email.hasAttachements()) {
			var attachmentValidationStrategy = new AttachmentValidationStrategy(email.getAttachments());
			attachmentValidationStrategy.validate();
		}
		
	}
	
}
