package com.s1410.calme.Application.SImplementations;

import com.s1410.calme.Domain.Dtos.EmailDTO;
import com.s1410.calme.Domain.Services.IEmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


@Service
public class EmailServiceImpl implements IEmailService {


    private final JavaMailSender javaMailSender;

    private final TemplateEngine templateEngine;


    public EmailServiceImpl(JavaMailSender javaMailSender, TemplateEngine templateEngine) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
    }


    public void sendMail (EmailDTO email) throws MessagingException {


        try {
            MimeMessage message =
                    javaMailSender.createMimeMessage();
            MimeMessageHelper helper =
                    new MimeMessageHelper(message,
                            true, "UTF-8");

            helper.setTo(email.getAddressee());
            helper.setTo(email.getSubject());
            //helper.setText(email.getMessage()); en caso de que solamente se quiera enviar un mesaje plano

            Context context = new Context();
            context.setVariable("message", email.getMessage()); //?? se introduce el nombre que se indico dentro de th: en template
            String contentHTML = templateEngine.process("email",context); //aqui va el nombre de tu carpeta template

            helper.setText(contentHTML, true);
            javaMailSender.send(message);



        } catch (Exception e) {
            throw new RuntimeException(" Error " + " al enviar el correo : " + e.getMessage(), e);
        }
    }


}
