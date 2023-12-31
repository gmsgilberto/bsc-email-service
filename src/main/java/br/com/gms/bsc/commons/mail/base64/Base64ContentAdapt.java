package br.com.gms.bsc.commons.mail.base64;

import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

public class Base64ContentAdapt {

	private static final Encoder ENCONDER = Base64.getEncoder();
	private static final Decoder DECODER = Base64.getDecoder();
	
	public static byte[] decode(String base64Content) {
		return DECODER.decode(base64Content);
	}
	
	public static String encode( byte[] content) {
		return ENCONDER.encodeToString(content);
	}
	
}
