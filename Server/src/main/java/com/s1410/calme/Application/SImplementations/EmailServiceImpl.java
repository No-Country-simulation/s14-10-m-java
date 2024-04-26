package com.s1410.calme.Application.SImplementations;

import com.s1410.calme.Domain.Entities.Appointment;
import com.s1410.calme.Domain.Entities.Assistent;
import com.s1410.calme.Domain.Entities.Doctor;
import com.s1410.calme.Domain.Entities.User;
import com.s1410.calme.Domain.Repositories.AppointmentRepository;
import com.s1410.calme.Domain.Repositories.AssistentRepository;
import com.s1410.calme.Domain.Repositories.DoctorRepository;
import com.s1410.calme.Domain.Services.EmailService;
import jakarta.annotation.PostConstruct;
import jakarta.mail.internet.MimeMessage;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;
    private final AppointmentRepository appointmentRepository;
    private final AssistentRepository assistentRepository;
    private final DoctorRepository doctorRepository;

    String token = token();

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
            String htmlBody = templateEngine.process("templateWelcomeRegister", context);

            // Establecer el cuerpo del mensaje como HTML
            helper.setText(htmlBody, true);

            javaMailSender.send(message);

        } catch (Exception e) {
            throw new RuntimeException(" Error " + " al enviar el correo : " + e.getMessage(), e);
        }
    }
    @Override
    @Scheduled(cron = "0 0 0 * * *")
    public void sendScheduledAppointments() {

        getAppointmentsToSendReminders().forEach(appointment -> {

            sendAppointmentEmail(appointment.getAssistent().getEmail(), appointment.getDate());

            
        });
    }
    @Override
    public void sendAppointmentEmail(String email, LocalDateTime date) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(email);
            helper.setSubject("Resgistrado con Exito!");

            Context context = new Context();
            // Agregar variables de contexto para la plantilla Thymeleaf
            context.setVariable("message", "Agendaste una cita para " + date);
            // Agregar más variables según sea necesario

            // Procesar la plantilla Thymeleaf
            String htmlBody = templateEngine.process("templateScheduledAppointment", context);

            // Establecer el cuerpo del mensaje como HTML
            helper.setText(htmlBody, true);

            javaMailSender.send(message);

        } catch (Exception e) {
            throw new RuntimeException(" Error " + " al enviar el correo : " + e.getMessage(), e);
        }
    }

    @Override
    public void sendPasswordRecoveryMail(String email, User user) {

        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(email);
            helper.setSubject("Recuperacion de contrasena");

            Context context = new Context();
            // Agregar variables de contexto para la plantilla Thymeleaf

            context.setVariable("nameUsuario", user.getFirstName() + " " + user.getLastName() + " " + user.getSecondName() );
            String jwtCode = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9";
            context.setVariable("messageRecovery", "http://localhost:8080/resetPassword?email=" + email + "&jwt=" + jwtCode);
            // Procesar la plantilla Thymeleaf
            String htmlBody = templateEngine.process("templateForgetPassword", context);

            // Establecer el cuerpo del mensaje como HTML
            helper.setText(htmlBody, true);

            javaMailSender.send(message);

        } catch (Exception e) {
            throw new RuntimeException(" Error " + " al enviar el correo de recuperacion, reenvie el correo : " + e.getMessage(), e);
        }
    }

    public List<Appointment> getAppointmentsToSendReminders() {

        LocalDate today = LocalDate.now();

        LocalDateTime startDataTime = today.plusDays(2).atStartOfDay();
        LocalDateTime endDataTime = today.plusDays(3).atStartOfDay();

        List<Appointment> appointmentList = appointmentRepository.findAll();

        List<Appointment> newAppointmentList = appointmentList.stream().filter(appointment -> {

            return appointment.getDate().isAfter(startDataTime) && appointment.getDate().isBefore(endDataTime);

        }).toList();

        return newAppointmentList;
    }


    /*
    * 1. se registra exitosamente.
    * 2. generar token dentro de confirmationEmail() y se manda el mail.
    * 3. el usuario ve el email y toca el boton con un url personalizado.
    * 4. llega a nuestro controlador con el token por parametro.
    * 5. compara el token con el token del servidor.
    * */

    public void emailConfirmation(String email, String userName) throws Exception {
        try {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setTo(email);
        helper.setSubject("CALME : Valida tu usuario.");

        Context context = new Context();
        context.setVariable("nombreUsuario",userName);
        context.setVariable("validationLink", buildEndpoint());
        String htmlBody = templateEngine.process("emailValidation", context);

        helper.setText(htmlBody, true);
        javaMailSender.send(message);
        }catch (Exception e) {
            throw new Exception("Error en la validación del correo electrónico: " + e.getMessage());
        }
    }


    @Transactional
    public void validateToken(String tokenController, String email){
        if(tokenController.equals(token)){
            //Esta función está llamada así para evitar referencia cíclica con assistentService.
            Optional<Assistent> assistentValidating = assistentRepository.findByEmail(email);
            if(assistentValidating.isPresent()) {
                assistentValidating.get().setValidUser(true);
                assistentRepository.save(assistentValidating.get());
                
            } else { Doctor doctorValidating = doctorRepository.findByEmail(email)
                    .orElseThrow(() -> new EntityNotFoundException(email));
                    doctorValidating.setValidUser(true);
                    doctorRepository.save(doctorValidating);
                
                }
        }else { throw new RuntimeException("Usuario no validado!");}
    }

    String buildEndpoint(){
        String urlPersonalizado = "http://localhost:8080/email/emailValidation/" + token + "/juan.ortega.it@gmail.com";
        return urlPersonalizado;
    }
    String token(){
        String token = UUID.randomUUID().toString();
        return token;
    }


/*    @PostConstruct
    void senMessage() throws Exception {
        emailConfirmation("juan.ortega.it@gmail.com","Juan Ortega");
    }*/


}
