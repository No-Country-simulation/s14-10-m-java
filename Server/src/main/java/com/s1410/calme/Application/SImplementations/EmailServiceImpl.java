package com.s1410.calme.Application.SImplementations;

import com.s1410.calme.Domain.Dtos.EmailDTO;
import com.s1410.calme.Domain.Services.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;

    @Transactional
    @Override
    public void sendMail (EmailDTO email) throws MessagingException {

        try {
            MimeMessage message =
                    javaMailSender.createMimeMessage();
            MimeMessageHelper helper =
                    new MimeMessageHelper(message,
                            true, "UTF-8");

            helper.setTo(email.toAddress());
            helper.setSubject(email.subject());
            helper.setText(email.body());

            javaMailSender.send(message);

        } catch (Exception e) {
            throw new RuntimeException(" Error " + " al enviar el correo : " + e.getMessage(), e);
        }
    }

}
