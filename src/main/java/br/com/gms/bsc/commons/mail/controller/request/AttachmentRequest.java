package br.com.gms.bsc.commons.mail.controller.request;

import java.io.Serializable;

import br.com.gms.bsc.commons.mail.model.AttachmentType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttachmentRequest implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String name;
	private AttachmentType type;
	private String base64Content;
	
}
