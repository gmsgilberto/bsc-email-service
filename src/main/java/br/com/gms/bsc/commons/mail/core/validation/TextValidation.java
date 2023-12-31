package br.com.gms.bsc.commons.mail.core.validation;

public class TextValidation {

	public static boolean isBlankOrNull(String text) {
		return text == null || text.isBlank();
	}

}
