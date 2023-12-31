package br.com.gms.bsc.commons.mail.core.model;

import br.com.gms.bsc.commons.mail.exceptions.SendEmailException;


public interface EmailValidationStrategy {
	
	/**
	 * Exeucta a validacao do e-mail informado.
	 * @param email
	 * @throws SendEmailException
	 */
	void execute(EmailPrototype email) throws SendEmailException;
	
}
