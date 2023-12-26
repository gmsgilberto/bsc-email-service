package br.com.gms.bsc.commons.mail.controller.request;

import java.io.Serializable;

import br.com.gms.bsc.commons.mail.model.Email;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter 
public class EmailSimpleRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	private String[] to;
	private String[] cc;
	private String subject;
	private String body;
	
	public Email toModel() {
		return new Email(to, cc, subject, body, null);
	}

}
