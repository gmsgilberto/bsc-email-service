package br.com.gms.bsc.commons.mail.controller.adapters;

import org.springframework.stereotype.Component;

import br.com.gms.bsc.commons.mail.controller.request.EmailRequest;
import br.com.gms.bsc.commons.mail.core.model.EmailPrototype;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class EmailMapper {

	private AttachmentMapper attachmentMapper;
	
	public EmailPrototype toModel(EmailRequest request) {
		var email = new EmailPrototype();
		
		request.getTo().forEach(to -> email.addTo(to));
		request.getCc().forEach(cc -> email.addCc(cc));
		email.setSubject(request.getSubject());
		email.setBody(request.getBody());
		request.getAttachments()
					.forEach(att -> email.addAttachment(this.attachmentMapper.toModel(att)));
		
		return email;
	}
	

}
