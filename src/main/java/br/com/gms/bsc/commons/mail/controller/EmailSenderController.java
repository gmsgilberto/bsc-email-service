package br.com.gms.bsc.commons.mail.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gms.bsc.commons.mail.controller.request.EmailRequestWithAttachment;
import br.com.gms.bsc.commons.mail.controller.request.EmailSimpleRequest;
import br.com.gms.bsc.commons.mail.service.EmailSender;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("e-mail/send")
@AllArgsConstructor
public class EmailSenderController {
	
	private EmailSender emailSender;
	
	@PostMapping(path = "no-attachments")
	public ResponseEntity<String> sendWithoutAttachment( @RequestBody EmailSimpleRequest email){
		this.emailSender.sendEmail(email.toModel());
		return ResponseEntity.status(HttpStatus.CREATED).body("ok");
	}
	
	@PostMapping(path = "with-attachments")
	public ResponseEntity<String> send( @RequestBody EmailRequestWithAttachment email){
		this.emailSender.sendEmail(email.toModel());
		return ResponseEntity.status(HttpStatus.CREATED).body("ok");
	}
	
	
}
