package com.s1410.calme.Domain.Services;

import org.springframework.http.ResponseEntity;

public interface DataBaseService {

    /* INSERT USERS */
    String insertAssistant();
    String insertDoctor();
    String insertAssisted();
    String insertAppointment();
    ResponseEntity<?> insertAll();

}
