package br.com.gms.bsc.commons.mail.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.mail.javamail.JavaMailSender;

import br.com.gms.bsc.commons.mail.service.EmailService;
import br.com.gms.bsc.commons.mail.service.impl.EmailServiceImpl;
import br.com.gms.bsc.commons.mail.service.queue.EmailServiceQueue;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Configuration
@NoArgsConstructor
@AllArgsConstructor
public class EmailServiceConfig {

	@Value("${bsc.gms.email.from}")
	private String emailFrom;

	@Autowired
	private JmsTemplate jmsTemplate;

	@Bean
	EmailService emailSender(final JavaMailSender javaMailSender) {
		var emailService = new EmailServiceImpl(javaMailSender, this.emailFrom);
		return new EmailServiceQueue(emailService, this.jmsTemplate);
	}

}
