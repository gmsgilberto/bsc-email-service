package br.com.gms.bsc.commons.mail.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gms.bsc.commons.mail.model.Email;
import br.com.gms.bsc.commons.mail.service.EmailSender;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("bsc/gms/e-mail/sender")
@AllArgsConstructor
public class EmailSenderController {
	
	private EmailSender emailSender;
	
	
	@PostMapping
	public ResponseEntity<String> send( @RequestBody Email email){
		this.emailSender.sendEmail(email);
		return ResponseEntity.status(HttpStatus.CREATED).body("ok");
	}
	
}
