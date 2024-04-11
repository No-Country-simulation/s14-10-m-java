package com.s1410.calme.Domain.Mapper;

import com.s1410.calme.Domain.Dtos.response.ResponseAppointment;
import com.s1410.calme.Domain.Entities.Appointment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AppointmentMapper {

    ResponseAppointment appointmentToResponse(Appointment appointment);
}
