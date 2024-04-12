package com.s1410.calme.Domain.Services;

import com.s1410.calme.Domain.Dtos.EmailDTO;
import jakarta.mail.MessagingException;

public interface EmailService {
     void sendMail(EmailDTO email) throws MessagingException;
}

