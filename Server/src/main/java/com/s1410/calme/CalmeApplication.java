package com.s1410.calme;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.s1410.calme.Application.SImplementations.ApiWhatsappServiceImpl;
import com.s1410.calme.Domain.Dtos.whatsapp.ResponseWhatsapp;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
@SecurityScheme(name = "Bearer Authentication", scheme = "bearer",bearerFormat ="JWT" , type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)

public class CalmeApplication {


	public static void main(String[] args) {
		SpringApplication.run(CalmeApplication.class, args);
	}

	@Autowired
	ApiWhatsappServiceImpl apiWhatsappServiceImpl;

	List<ResponseWhatsapp> senMessage() throws JsonProcessingException {
		return apiWhatsappServiceImpl.createMessageTest();
	}


}
