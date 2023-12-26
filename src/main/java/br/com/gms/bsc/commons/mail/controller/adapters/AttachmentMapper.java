package br.com.gms.bsc.commons.mail.controller.adapters;

import org.springframework.stereotype.Component;

import br.com.gms.bsc.commons.mail.controller.request.AttachmentRequest;
import br.com.gms.bsc.commons.mail.model.Attachment;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class AttachmentMapper {
	
	public Attachment toModel(AttachmentRequest request) {
		var attachment = new Attachment();
		attachment.setName(request.getName());
		attachment.setType(request.getType());
		attachment.setContent(request.getBase64Content());
		return attachment;
	}
	

}
