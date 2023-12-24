package br.com.gms.bsc.commons.mail.model;

import br.com.gms.bsc.commons.mail.exceptions.SendEmailException;
import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
class EmailValidationStrategy {
	
	@NonNull
	private Email email;
	
	void validate() {
		
		var erros = new StringBuilder();
		
		if(email.body() == null || email.body().isBlank()) {
			erros.append("E-mail body cannot be null").append("\n");
		}
		
		if(email.subject() == null || email.subject().isBlank()) {
			erros.append("E-mail subject cannot be null").append("\n");
		}
		
		if(email.to() == null || email.to().length == 0) {
			erros.append("E-mail must havet one ou mor destinations").append("\n");
		}
		
		if(!erros.isEmpty()) {
			throw new SendEmailException(erros.toString()); 
		}
		
	}
	
}
