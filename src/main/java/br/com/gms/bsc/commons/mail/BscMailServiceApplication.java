package br.com.gms.bsc.commons.mail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class BscMailServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BscMailServiceApplication.class, args);
	}

}
