package br.com.gms.bsc.commons.mail.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Email implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private final String id;
	private final List<String> to;
	private final List<String> cc;
	private final List<Attachment> attachments;
	private String subject;
	private String body;


	public Email() {
		this(UUID.randomUUID().toString());
	}
	
	public Email(String uuid) {
		this.id = uuid;
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
	
	
	public void validate() {
		var strategy = new EmailValidationStrategy(this);
		strategy.validate();
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
