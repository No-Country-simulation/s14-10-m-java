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
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;

    private final TemplateEngine templateEngine;

    @Transactional
    @Override
    public void sendMail (EmailDTO email) throws MessagingException {

        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message,true,"UTF-8");

            helper.setTo(email.toAddress());
            helper.setSubject(email.subject());

            // Procesar la plantilla Thymeleaf
            String htmlBody = templateEngine.process("email", prepareContext(email));

            // Establecer el cuerpo del mensaje como HTML
            helper.setText(htmlBody, true);

            javaMailSender.send(message);

        } catch (Exception e) {
            throw new RuntimeException(" Error " + " al enviar el correo : " + e.getMessage(), e);
        }
    }

    private Context prepareContext(EmailDTO email) {
        Context context = new Context();
        // Agregar variables de contexto para la plantilla Thymeleaf
        context.setVariable("message", email.body());
        // Agregar más variables según sea necesario
        return context;
    }
}
