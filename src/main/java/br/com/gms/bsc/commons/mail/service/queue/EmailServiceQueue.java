package br.com.gms.bsc.commons.mail.service.queue;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;

import com.google.gson.Gson;

import br.com.gms.bsc.commons.mail.exceptions.SendEmailException;
import br.com.gms.bsc.commons.mail.model.Email;
import br.com.gms.bsc.commons.mail.service.EmailService;
import jakarta.jms.Session;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@AllArgsConstructor
@Slf4j
public class EmailServiceQueue implements EmailService {

	public static final String QUEUE_OUT= "EMAIL_OUT";
	private static final Gson GSON_PARSER = new Gson();
	private EmailService emailService;
	private JmsTemplate jmsTemplate;
	
	/**
	 * Send a e-mails
	 * @param email
	 * @return
	 */
	public Email sendEmail(Email email){
		email.validate();
		try {
			log.info("[EmailServiceQueue] - Send to queue: {}.", email.getId());
			this.jmsTemplate.convertAndSend(QUEUE_OUT, GSON_PARSER.toJson(email));
		}catch (Exception e) {
			throw new SendEmailException("bsc.gms.exceptions.email.sender",e);
		}
		return email;
	}

	@JmsListener(destination = QUEUE_OUT, concurrency = "3-7" )
    public void receiveMessage(String message, Session session) {

		try {
			var email = GSON_PARSER.fromJson(message, Email.class);
			log.info("[EmailServiceQueue] - Processa mensagem: {}", email.getId());
			this.emailService.sendEmail(email);
			session.commit();
		}catch (Exception e) {
			throw new SendEmailException("bsc.gms.exceptions.email.sender",e);
		}
    }
	
}
