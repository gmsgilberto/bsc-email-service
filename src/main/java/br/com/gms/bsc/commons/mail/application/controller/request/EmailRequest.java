package br.com.gms.bsc.commons.mail.application.controller.request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EmailRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<String> to;
	private List<String> cc;
	private String subject;
	private String body;
	private List<AttachmentRequest> attachments;

	public List<String> getTo() {
		if(this.to == null) {
			this.to =new ArrayList<>();
		}
		return to;
	}
	
	public List<String> getCc() {
		if(this.cc == null) {
			this.cc =new ArrayList<>();
		}
		return cc;
	}
	
	public final List<AttachmentRequest> getAttachments(){
		if(this.attachments == null) {
			this.attachments = new ArrayList<>();
		}
		return attachments;
	}
	
}
