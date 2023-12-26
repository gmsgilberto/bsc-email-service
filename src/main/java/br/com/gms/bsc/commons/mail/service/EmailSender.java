package br.com.gms.bsc.commons.mail.service;

import br.com.gms.bsc.commons.mail.model.Email;

public interface EmailSender {

	Email sendEmail(Email email);
	
}
