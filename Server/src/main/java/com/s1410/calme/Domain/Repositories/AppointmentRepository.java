package com.s1410.calme.Domain.Repositories;

import com.s1410.calme.Domain.Entities.Appointment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface AppointmentRepository extends JpaRepository<Appointment,Long> {

    @Query(value = "SELECT a FROM Appointment a where a.active = ?1",
            countQuery = "SELECT count(*) FROM Appointment a WHERE a.active = ?1")
    Page<Appointment> findAppointmentsByActivePageable(Boolean active, Pageable pageable);

    @Query(value = "SELECT a FROM Appointment  a where  a.active = ?1 AND a.date between ?2 AND ?3 ")
    Page<Appointment> findAppointmentsBetweenDates(Boolean active, LocalDateTime startDate, LocalDateTime finishDate,  Pageable pageable);


    @Query("SELECT a FROM Appointment a WHERE a.doctor.id = :doctorId AND a.active = :active")
    Page<Appointment> findAppointmentByDoctorId(Long doctorId, Boolean active, Pageable pageable);

    @Query("SELECT a FROM Appointment a WHERE a.assistent.id = :assistantId AND a.active = :active")
    Page<Appointment> findAppointmentAssistantById(Long assistantId, Boolean active, Pageable pageable);

    @Query("SELECT a FROM Appointment a WHERE a.assisted.id = :assistedId AND a.active = :active")
    Page<Appointment> findApppointmentAssistedById(Long assistedId, Boolean active, Pageable pageable);

    @Query("SELECT a FROM Appointment a WHERE a.doctor.id = :doctorId AND a.active = :active AND a.date BETWEEN :startDate AND :finishDate")
    Page<Appointment> findAppointmentDoctorByBetweenDates(Long doctorId, Boolean active,
                                                   LocalDateTime startDate, LocalDateTime finishDate, Pageable pageable);


}
