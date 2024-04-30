package com.s1410.calme.Domain.Services;
import com.s1410.calme.Domain.Entities.Appointment;

import java.time.LocalDateTime;

public interface EmailService {

     void sendUserRegistrationMail(String email) ;
     void sendScheduledAppointments();
     void sendAppointmentEmail (Appointment appointment);
     void emailConfirmation(String email, String userName) throws Exception;
     String validateToken(String tokenController, String email);
     void sendPasswordRecoveryMail(String email);
}

