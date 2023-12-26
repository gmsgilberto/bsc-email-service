package br.com.gms.bsc.commons.mail.service.impl;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import br.com.gms.bsc.commons.mail.exceptions.SendEmailException;
import br.com.gms.bsc.commons.mail.model.Email;
import br.com.gms.bsc.commons.mail.service.EmailSender;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@AllArgsConstructor
@Slf4j
public class EmailSenderJavaMail implements EmailSender {

	private final JavaMailSender mailSender;
	private final String from;
	
	@Override
	public void sendEmail(Email email) {

		email.validate();
		
		try {
			
			log.info("Send new e-mail: " + email.getSubject());
			
			if(email.hasAttachements()) {
				
			}else {
				sendSimpleEmail(email);
			}
			
		}catch (Exception e) {
			throw new SendEmailException("bsc.gms.exceptions.email.sender",e);
		}
	}

	private void sendSimpleEmail(Email email) throws MessagingException {
		
		var emailMessage = this.mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(emailMessage, true);
		
		helper.setFrom(this.from);
		helper.setText(email.getBody(), true);
		helper.setSubject(email.getSubject());
		
		for(String to : email.getTo()) {
			helper.addTo(to);
		}
		
		if(email.hasCc()) {
			for(String cc : email.getCc()) {
				helper.addCc(cc);
			}
		}

		
		//Attachment[] attachments
		
		
		
		this.mailSender.send(emailMessage);		
	}
	
	
}
