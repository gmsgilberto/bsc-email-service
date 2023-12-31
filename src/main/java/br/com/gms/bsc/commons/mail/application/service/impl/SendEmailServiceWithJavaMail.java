package br.com.gms.bsc.commons.mail.application.service.impl;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import br.com.gms.bsc.commons.mail.core.model.Attachment;
import br.com.gms.bsc.commons.mail.core.model.AttachmentType;
import br.com.gms.bsc.commons.mail.core.model.EmailPrototype;
import br.com.gms.bsc.commons.mail.core.service.SendEmailService;
import br.com.gms.bsc.commons.mail.exceptions.SendEmailException;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@AllArgsConstructor
public class SendEmailServiceWithJavaMail extends SendEmailService {

	private final JavaMailSender mailSender;
	private final String from;

	
	@Override
	protected void handleSendEmail(EmailPrototype email) {

		try {
			
			log.info("[SendEmailServiceImpl] - sending e-mail {}", email.getId());

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
		
	}

	
	
	private void addAttachments(EmailPrototype email, MimeMessageHelper helper) throws MessagingException {
		for(Attachment attachment: email.getAttachments()) {
			if(attachment.getType() == AttachmentType.INLINE) {
				helper.addInline(attachment.getName(),new ByteArrayResource(attachment.bytes()) {
					@Override
					public String getFilename() {
						return attachment.getName();
					}
				});
			}else {
				helper.addAttachment(attachment.getName(),new ByteArrayResource(attachment.bytes()));
			}
		}
	}
	
}
