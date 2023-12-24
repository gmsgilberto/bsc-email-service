package br.com.gms.bsc.commons.mail.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;

import br.com.gms.bsc.commons.mail.service.EmailSender;
import br.com.gms.bsc.commons.mail.service.impl.EmailSenderJavaMail;

@Configuration
public class EmailServiceConfig {

	@Value("${bsc.gms.email.from}")
	private String emailFrom;
	
	
	@Bean
	EmailSender emailSender(final JavaMailSender javaMailSender) {
		return new EmailSenderJavaMail(javaMailSender, this.emailFrom);
	}
	
}
