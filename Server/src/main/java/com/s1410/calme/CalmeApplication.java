package com.s1410.calme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class CalmeApplication {

	public static void main(String[] args) {
		SpringApplication.run(CalmeApplication.class, args);
	}

}
