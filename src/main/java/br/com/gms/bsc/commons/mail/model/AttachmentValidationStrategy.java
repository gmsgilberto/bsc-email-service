package br.com.gms.bsc.commons.mail.model;

import br.com.gms.bsc.commons.mail.exceptions.SendEmailException;
import br.com.gms.bsc.commons.mail.util.TextValidation;
import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
class AttachmentValidationStrategy {
	
	@NonNull
	private Attachment[] attachments;
	
	void validate() {
		
		if(this.attachments != null && this.attachments.length > 0) {
			
			var erros = new StringBuilder();
			
			Attachment attachment;
			for(int index = 0; index < this.attachments.length; index++) {
				attachment = this.attachments[index];
				
				if(TextValidation.isBlankOrNull(attachment.name())) {
					erros.append("Attachment["+index+"] must have a name.");
				}

				if(TextValidation.isBlankOrNull(attachment.type())) {
					erros.append("Attachment["+index+"] must have a explicit type (sample: pdf, xlsx, etc.)");
				}
				
				if(TextValidation.isBlankOrNull(attachment.base64Content())) {
					erros.append("Attachment["+index+"] has no content.");
				}

			}
		
			if(!erros.isEmpty()) {
				throw new SendEmailException(erros.toString()); 
			}
			
		}
		
	}
	
}
