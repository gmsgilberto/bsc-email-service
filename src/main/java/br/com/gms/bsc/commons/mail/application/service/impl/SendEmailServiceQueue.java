package br.com.gms.bsc.commons.mail.application.service.impl;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;

import com.google.gson.Gson;

import br.com.gms.bsc.commons.mail.core.model.EmailPrototype;
import br.com.gms.bsc.commons.mail.core.service.SendEmailService;
import br.com.gms.bsc.commons.mail.exceptions.SendEmailException;
import jakarta.jms.Session;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@AllArgsConstructor
@Slf4j
public class SendEmailServiceQueue extends SendEmailService {

	public static final String QUEUE_OUT= "EMAIL_OUT";
	private static final Gson GSON_PARSER = new Gson();
	private SendEmailService emailService;
	private JmsTemplate jmsTemplate;
	
	/**
	 * Send a e-mails
	 * @param email
	 * @return
	 */
	@Override
	protected void handleSendEmail(EmailPrototype email) {
		try {
			log.info("[EmailServiceQueue] - send e-mail {} to queue: {}.", email.getId(), QUEUE_OUT);
			this.jmsTemplate.convertAndSend(QUEUE_OUT, GSON_PARSER.toJson(email));
		}catch (Exception e) {
			throw new SendEmailException("bsc.gms.exceptions.email.sender",e);
		}
	}

	@JmsListener(destination = QUEUE_OUT, concurrency = "3-7" )
    public void receiveMessage(String message, Session session) {

		try {
			var email = GSON_PARSER.fromJson(message, EmailPrototype.class);
			log.info("[EmailServiceQueue] - Processa mensagem: {}", email.getId());
			this.emailService.execute(email);
			session.commit();
		}catch (Exception e) {
			throw new SendEmailException("bsc.gms.exceptions.email.sender",e);
		}
    }
	
}
