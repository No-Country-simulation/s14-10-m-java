package com.s1410.calme;
import com.s1410.calme.Application.SImplementations.ApiWhatsappServiceImpl;
import com.s1410.calme.Domain.Repositories.AppointmentRepository;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@SecurityScheme(name = "Bearer Authentication", scheme = "bearer",bearerFormat ="JWT" , type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)

public class CalmeApplication {


	public static void main(String[] args) {
		SpringApplication.run(CalmeApplication.class, args);
	}

	@Autowired
	ApiWhatsappServiceImpl apiWhatsappServiceImpl;
	@PostConstruct
	String asdas (){
		return apiWhatsappServiceImpl.createMessageTest();
	}


}
