package com.s1410.calme.Application.SImplementations;

import com.s1410.calme.Domain.Entities.Appointment;
import com.s1410.calme.Domain.Repositories.AppointmentRepository;
import com.s1410.calme.Domain.Services.EmailService;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;

    private final TemplateEngine templateEngine;

    private final AppointmentRepository appointmentRepository;

    @Transactional
    @Override
    public void sendUserRegistrationMail(String email) {

        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(email);
            helper.setSubject("Resgistrado con Exito!");

            Context context = new Context();
            // Agregar variables de contexto para la plantilla Thymeleaf
            context.setVariable("message", "Gracias por registrarse a Calme. Te registraste con el mail" + email);


            // Procesar la plantilla Thymeleaf
            String htmlBody = templateEngine.process("email", context);

            // Establecer el cuerpo del mensaje como HTML
            helper.setText(htmlBody, true);

            javaMailSender.send(message);

        } catch (Exception e) {
            throw new RuntimeException(" Error " + " al enviar el correo : " + e.getMessage(), e);
        }
    }

    @Override
    @Scheduled(fixedRate = 5000)
  //  @Scheduled(cron = "0 0 0 * * *"))
    public void sendAppointmentEmail() {


       LocalDate today = LocalDate.now();

       LocalDateTime startDataTime =  today.plusDays(2).atStartOfDay();
       LocalDateTime endDataTime = today.plusDays(3).atStartOfDay();
    //   System.out.println("La fecha Actual es: " + startDataTime + " " + endDataTime);
       List<Appointment> appointmentList = appointmentRepository.findAll();

     List<Appointment> newAppointmentList = appointmentList.stream().filter(appointment -> {

        return appointment.getDate().isAfter(startDataTime) && appointment.getDate().isBefore(endDataTime);
       //  return true;
          }).toList();

     newAppointmentList.forEach(appointment -> System.out.println( appointment.
             getDate( ) + "  " + appointment.getAssistent().getEmail()));

 //      try {
 //          MimeMessage message = javaMailSender.createMimeMessage();
 //          MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

 //          helper.setTo(email);
 //          helper.setSubject("Resgistrado con Exito!");

 //          Context context = new Context();
 //          // Agregar variables de contexto para la plantilla Thymeleaf
 //          context.setVariable("message", "Agendaste una cita para " + date);
 //          // Agregar más variables según sea necesario

 //          // Procesar la plantilla Thymeleaf
 //          String htmlBody = templateEngine.process("email", context);

 //          // Establecer el cuerpo del mensaje como HTML
 //          helper.setText(htmlBody, true);

 //          javaMailSender.send(message);

 //      } catch (Exception e) {
 //          throw new RuntimeException(" Error " + " al enviar el correo : " + e.getMessage(), e);
 //      }
    }
}
