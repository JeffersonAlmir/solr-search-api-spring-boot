package com.example.sorl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableRetry
public class SorlApplication {

	public static void main(String[] args) {
		SpringApplication.run(SorlApplication.class, args);
	}

}
