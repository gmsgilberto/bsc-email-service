package br.com.gms.bsc.commons.mail.service.impl;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import br.com.gms.bsc.commons.mail.exceptions.SendEmailException;
import br.com.gms.bsc.commons.mail.model.Email;
import br.com.gms.bsc.commons.mail.service.EmailSender;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class EmailSenderJavaMail implements EmailSender {

	private final JavaMailSender mailSender;
	private final String from;
	
	@Override
	public void sendEmail(Email email) {

		email.validate();
		
		try {
			
			var emailMessage = new SimpleMailMessage();
			emailMessage.setFrom(from);
			emailMessage.setTo(email.to());
			emailMessage.setCc(email.cc());
			emailMessage.setSubject(email.subject());
			emailMessage.setText(email.body());
			this.mailSender.send(emailMessage);
			
		}catch (Exception e) {
			throw new SendEmailException("bsc.gms.exceptions.email.sender",e);
		}
	}
	
}
