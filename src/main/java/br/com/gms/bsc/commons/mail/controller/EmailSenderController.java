package br.com.gms.bsc.commons.mail.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gms.bsc.commons.mail.controller.adapters.EmailMapper;
import br.com.gms.bsc.commons.mail.controller.request.EmailRequest;
import br.com.gms.bsc.commons.mail.service.EmailService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("e-mail")
@AllArgsConstructor
public class EmailSenderController {
	
	private EmailService emailSender;
	private EmailMapper emailMapper;
	
	@PostMapping
	public ResponseEntity<String> sendEmail( @RequestBody EmailRequest request){
		var email = this.emailMapper.toModel(request);
		email = this.emailSender.sendEmail(email);
		return ResponseEntity.status(HttpStatus.CREATED).body(email.getId());
	}
	
	
}
