package com.s1410.calme.Domain.Services;
import com.s1410.calme.Domain.Entities.Assistent;

import java.time.LocalDateTime;
import java.util.Date;

public interface EmailService {

     void sendUserRegistrationMail(String email) ;
     void sendScheduledAppointments();
     void sendAppointmentEmail (String email, LocalDateTime date);
     void sendPasswordRecoveryMail(String email);
}

