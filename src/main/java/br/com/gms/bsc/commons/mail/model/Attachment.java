package br.com.gms.bsc.commons.mail.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter 
public class Attachment implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String name;
	private String type;
	private byte[] content;
	
	
	public boolean hasContent() {
		return this.content != null && this.content.length > 0;
	}
	
}
