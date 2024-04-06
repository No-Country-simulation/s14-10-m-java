package com.s1410.calme.Domain.Repositories;

import com.s1410.calme.Domain.Entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment,Long> {

}
