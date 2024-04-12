package com.s1410.calme.Infrastructure.Controllers;

import com.s1410.calme.Domain.Dtos.EmailDTO;
import com.s1410.calme.Domain.Services.IEmailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class EmailController {


    IEmailService emailService;

    @PostMapping("/send-Email")
    public ResponseEntity<String> sendMail(@RequestBody EmailDTO email) throws MessagingException {
        emailService.sendMail(email);

        return new ResponseEntity<>("Correo enviado", HttpStatus.OK);
    }


}
