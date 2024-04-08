package com.s1410.calme.Domain.Services;

import com.s1410.calme.Domain.Dtos.request.RequestCreateAppointment;
import com.s1410.calme.Domain.Dtos.response.ResponseAppointment;
import org.springframework.http.ResponseEntity;

public interface AppointmentService {

    ResponseEntity<ResponseAppointment> createAppointment(RequestCreateAppointment requestCreateAppointment);


    //TODO: Implement Methods
    //Get All
    //Post Appointment
    //Delete Appointment
    //DeActivate Appointment
    //Activate Appointment
    //Update Appointment
    //Get Appointment By Id
}
