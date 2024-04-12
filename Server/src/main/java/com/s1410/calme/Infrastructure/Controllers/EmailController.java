package com.s1410.calme.Infrastructure.Controllers;

import com.s1410.calme.Domain.Dtos.EmailDTO;
import com.s1410.calme.Domain.Services.EmailService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/email")
@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
public class EmailController {

    private final EmailService emailService;

    @PostMapping("/send")
    public ResponseEntity<String> sendMail(@RequestBody EmailDTO email) throws MessagingException {
        emailService.sendMail(email);

        return new ResponseEntity<>("Correo enviado", HttpStatus.OK);
    }


}
