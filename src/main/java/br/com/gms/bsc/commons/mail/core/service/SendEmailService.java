package br.com.gms.bsc.commons.mail.core.service;

import java.util.UUID;

import br.com.gms.bsc.commons.mail.core.model.EmailPrototype;
import br.com.gms.bsc.commons.mail.core.model.EmailValidationStrategy;
import br.com.gms.bsc.commons.mail.core.validation.EmailValidationFieldsStrategy;
import br.com.gms.bsc.commons.mail.core.validation.TextValidation;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class SendEmailService {

	private final EmailValidationStrategy emailValidation;
	private final EmailIdService emailIdService;
	
	public SendEmailService() {
		
		this.emailValidation = new EmailValidationFieldsStrategy();
		
		this.emailIdService = new EmailIdService() {
			@Override
			public synchronized String generateNewEmailId() {
				return UUID.randomUUID().toString();
			}

		};
	}
	
	public final String execute(EmailPrototype email){
		
		this.setEmailId(email);
		this.emailValidation.execute(email);
		this.handleSendEmail(email);
		
		return email.getId();
		
	}

	private void setEmailId(EmailPrototype email) {
		if(TextValidation.isBlankOrNull(email.getId())) {
			final var emailId = this.emailIdService.generateNewEmailId();
			email.setId(emailId);
		}
	}


	protected abstract void handleSendEmail(EmailPrototype email);
	
	
}
