package com.matheuscordeiro.bffageofempiresapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BffAgeOfEmpiresApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BffAgeOfEmpiresApiApplication.class, args);
	}

}
