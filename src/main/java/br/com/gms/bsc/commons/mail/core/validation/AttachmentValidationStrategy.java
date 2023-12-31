package br.com.gms.bsc.commons.mail.core.validation;

import br.com.gms.bsc.commons.mail.core.model.Attachment;
import br.com.gms.bsc.commons.mail.core.model.EmailPrototype;
import br.com.gms.bsc.commons.mail.core.model.EmailValidationStrategy;
import br.com.gms.bsc.commons.mail.exceptions.SendEmailException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AttachmentValidationStrategy implements EmailValidationStrategy{
	
	@Override
	public void execute(EmailPrototype email) throws SendEmailException {
		
		
		if(email.hasAttachements()) {
			
			var attachments = email.getAttachments();
			var erros = new StringBuilder();
			
			Attachment attachment;
			for(int index = 0; index < attachments.size(); index++) {
				attachment = attachments.get(index);
				
				if(TextValidation.isBlankOrNull(attachment.getName())) {
					erros.append("Attachment["+index+"] must have a name.").append("; ");
				}

				if(TextValidation.isBlankOrNull(attachment.getName())) {
					erros.append("Attachment["+index+"] must have a explicit type (sample: pdf, xlsx, etc.)").append("; ");
				}
				
				if(!attachment.hasContent()) {
					erros.append("Attachment["+index+"] has no content.").append("; ");
				}

			}
		
			if(!erros.isEmpty()) {
				throw new SendEmailException(erros.toString()); 
			}
			
		}
		
	}
	
}
