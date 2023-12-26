package br.com.gms.bsc.commons.mail.service.impl;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import br.com.gms.bsc.commons.mail.exceptions.SendEmailException;
import br.com.gms.bsc.commons.mail.model.Attachment;
import br.com.gms.bsc.commons.mail.model.AttachmentType;
import br.com.gms.bsc.commons.mail.model.Email;
import br.com.gms.bsc.commons.mail.service.EmailSender;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@AllArgsConstructor
@Slf4j
public class EmailSenderWithJavaMail implements EmailSender {

	private final JavaMailSender mailSender;
	private final String from;

	/**
	 * Send a e-mails
	 * @param email
	 * @return
	 */
	public Email sendEmail(Email email){

		email.validate();
		
		try {
			
			log.info("Send e-mail {}", email.getId());

			var emailMessage = this.mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(emailMessage, true);
			
			helper.setFrom(this.from);
			helper.setText(email.getBody(), true);
			helper.setSubject(email.getSubject());
			
			for(String to : email.getTo()) {
				helper.addTo(to);
			}
			
			for(String cc : email.getCc()) {
				helper.addCc(cc);
			}
			
			this.addAttachments(email, helper);
	
			this.mailSender.send(emailMessage);
			
		}catch (Exception e) {
			throw new SendEmailException("bsc.gms.exceptions.email.sender",e);
		}
		
		return email;
	}

	
	
	private void addAttachments(Email email, MimeMessageHelper helper) throws MessagingException {
		for(Attachment attachment: email.getAttachments()) {
			if(attachment.getType() == AttachmentType.INLINE) {
				helper.addInline(attachment.getName(),new ByteArrayResource(attachment.getContent()) {
					@Override
					public String getFilename() {
						return attachment.getName();
					}
				});
			}else {
				helper.addAttachment(attachment.getName(),new ByteArrayResource(attachment.getContent()));
			}
		}
	}
	
}
