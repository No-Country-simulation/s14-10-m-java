package com.s1410.calme.Infrastructure.Controllers;


import com.s1410.calme.Application.SImplementations.EmailServiceImpl;
import com.s1410.calme.Domain.Services.EmailService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/email")
public class EmailController {

    public final EmailService emailService;

    @GetMapping("/emailValidation/{token}/{email}")
    /*/emailValidation/token=askjdajs/email=lalala@gmail.com*/
    String EmailValidation(@PathVariable("token") String token, @PathVariable("email") String email) throws Exception {
        emailService.validateToken(token, email);
        return emailService.validateToken(token, email);
    }

}
