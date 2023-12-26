package br.com.gms.bsc.commons.mail.model;

import br.com.gms.bsc.commons.mail.exceptions.SendEmailException;
import br.com.gms.bsc.commons.mail.util.TextValidation;
import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
class AttachmentValidationStrategy {
	
	@NonNull
	private java.util.List<Attachment> attachments;
	
	void validate() {
		
		if(this.attachments != null) {
			
			var erros = new StringBuilder();
			
			Attachment attachment;
			for(int index = 0; index < this.attachments.size(); index++) {
				attachment = this.attachments.get(index);
				
				if(TextValidation.isBlankOrNull(attachment.getName())) {
					erros.append("Attachment["+index+"] must have a name.");
				}

				if(TextValidation.isBlankOrNull(attachment.getName())) {
					erros.append("Attachment["+index+"] must have a explicit type (sample: pdf, xlsx, etc.)");
				}
				
				if(!attachment.hasContent()) {
					erros.append("Attachment["+index+"] has no content.");
				}

			}
		
			if(!erros.isEmpty()) {
				throw new SendEmailException(erros.toString()); 
			}
			
		}
		
	}
	
}
