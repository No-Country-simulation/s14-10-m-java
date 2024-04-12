package com.s1410.calme.Domain.Services;

import com.s1410.calme.Domain.Dtos.EmailDTO;
import jakarta.mail.MessagingException;


public interface IEmailService {

     void sendMail(EmailDTO email) throws MessagingException;
}

