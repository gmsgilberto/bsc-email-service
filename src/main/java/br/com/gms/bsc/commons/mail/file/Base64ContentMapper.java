package br.com.gms.bsc.commons.mail.file;

import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

import org.springframework.stereotype.Component;

@Component
public class Base64ContentMapper {

	private final Encoder enconder = Base64.getEncoder();
	private final Decoder decoder = Base64.getDecoder();
	
	public byte[] decode(String base64Content) {
		return this.decoder.decode(base64Content);
	}
	
	public String encode( byte[] content) {
		return this.enconder.encodeToString(content);
	}
	
}
