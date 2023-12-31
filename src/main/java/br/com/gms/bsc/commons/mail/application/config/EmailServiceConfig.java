package br.com.gms.bsc.commons.mail.application.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.mail.javamail.JavaMailSender;

import br.com.gms.bsc.commons.mail.core.service.SendEmailService;
import br.com.gms.bsc.commons.mail.service.impl.javamail.SendEmailServiceWithJavaMail;
import br.com.gms.bsc.commons.mail.service.impl.queue.SendEmailServiceQueue;
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
	@Primary
	SendEmailService emailSender(SendEmailServiceWithJavaMail sendEmailServiceImpl) {
		return new SendEmailServiceQueue(sendEmailServiceImpl, this.jmsTemplate);
	}
	
	@Bean
	SendEmailServiceWithJavaMail sendEmailServiceImpl(final JavaMailSender javaMailSender) {
		return new SendEmailServiceWithJavaMail(javaMailSender, this.emailFrom);
	}
	
}
