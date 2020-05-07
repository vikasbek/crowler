package com.crowler.crowler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class CrowlerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrowlerApplication.class, args);
	}

}
