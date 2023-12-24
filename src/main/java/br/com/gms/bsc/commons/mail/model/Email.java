package br.com.gms.bsc.commons.mail.model;

public record Email (String[] to, String[] cc, String subject, String body) {

	public void validate() {
		var strategy = new EmailValidationStrategy(this);
		strategy.validate();
	}
	
}
