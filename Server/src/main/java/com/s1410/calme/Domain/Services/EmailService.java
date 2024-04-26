package com.s1410.calme.Domain.Services;
import com.s1410.calme.Domain.Entities.User;

import java.time.LocalDateTime;

public interface EmailService {

     void sendUserRegistrationMail(String email) ;
     void sendScheduledAppointments();
     void sendAppointmentEmail (String email, LocalDateTime date);
     void emailConfirmation(String email, String userName) throws Exception;
     void validateToken(String tokenController, String email);
     void sendPasswordRecoveryMail(String email,User user);
}

