package br.com.gms.bsc.commons.mail.core.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class EmailPrototype implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String id;
	private final List<String> to;
	private final List<String> cc;
	private final List<Attachment> attachments;
	private String subject;
	private String body;
	
	public EmailPrototype() {
		this.attachments = new ArrayList<>();
		this.to = new ArrayList<>();
		this.cc = new ArrayList<>();
	}
	
	public void addTo(String to) {
		this.to.add(to);
	}
	
	public void addCc(String cc) {
		this.cc.add(cc);
	}
	
	
	public void validate(EmailValidationStrategy validation) {
		validation.execute(this);
	}

	public boolean hasCc() {
		return !this.cc.isEmpty();
	}
	
	public boolean hasTo() {
		return !this.to.isEmpty();
	}
	

	public void addAttachment(Attachment attachment) {
		this.attachments.add(attachment);
	}
	
	
	public boolean hasAttachements() {
		return this.attachments != null && !this.attachments.isEmpty();
	}
	
}
