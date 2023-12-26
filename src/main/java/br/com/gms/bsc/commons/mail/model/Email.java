package br.com.gms.bsc.commons.mail.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Email implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String[] to;
	private String[] cc;
	private String subject;
	private String body;
	private Attachment[] attachments;

	public void validate() {
		var strategy = new EmailValidationStrategy(this);
		strategy.validate();
	}

	public boolean hasCc() {
		return this.cc != null && this.cc.length > 0;
	}

	
	public boolean hasAttachements() {
		return this.attachments != null && this.attachments.length > 0;
	}
	
}
