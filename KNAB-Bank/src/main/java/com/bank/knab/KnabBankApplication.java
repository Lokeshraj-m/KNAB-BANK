package com.bank.knab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class KnabBankApplication {

	public static void main(String[] args) {
		SpringApplication.run(KnabBankApplication.class, args);
	}

}
