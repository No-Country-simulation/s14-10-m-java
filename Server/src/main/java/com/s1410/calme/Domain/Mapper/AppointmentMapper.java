package com.s1410.calme.Domain.Mapper;

import com.s1410.calme.Domain.Dtos.response.ResponseAppointment;
import com.s1410.calme.Domain.Entities.Appointment;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AppointmentMapper {

    ResponseAppointment appointmentToResponse(Appointment appointment);
    List<ResponseAppointment> appointmentListToResponseList(List<Appointment> appointmentList);
}
