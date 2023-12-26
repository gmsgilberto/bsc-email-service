package br.com.gms.bsc.commons.mail.model;

import java.io.Serializable;

import br.com.gms.bsc.commons.mail.file.Base64ContentAdapt;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter 
public class Attachment implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String name;
	private AttachmentType type;
	private String content;
	
	
	public boolean hasContent() {
		return this.content != null && !this.content.isBlank();
	}


	public byte[] bytes() {
		return Base64ContentAdapt.decode(content);
	}
	
}
