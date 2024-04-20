package com.s1410.calme.Domain.Repositories;

import com.s1410.calme.Domain.Entities.Appointment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment,Long> {

    @Query(value = "SELECT a FROM Appointment a where a.active = ?1",
            countQuery = "SELECT count(*) FROM Appointment a WHERE a.active = ?1")
    Page<Appointment> findAppointmentsByActivePageable(Boolean active, Pageable pageable);

    @Query(value = "SELECT a FROM Appointment  a where  a.active = ?1 AND a.date between ?2 AND ?3 ")
    Page<Appointment> findAppointmentsBetweenDates(Boolean active, LocalDateTime startDate, LocalDateTime finishDate,  Pageable pageable);

    @Query(value = "SELECT * FROM appointment WHERE doctor_id = :doctorId " +
            "AND active = :active AND date BETWEEN :startDate AND :endDate",
            nativeQuery = true)
    Page<Appointment> findAppointmentsByDoctorIdAndDateRange(
            @Param("doctorId") Long doctorId,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
            @Param("active") Boolean active,
            Pageable pageable
    );

    @Query(value = "SELECT * FROM appointment WHERE assistent_id = :assistentId " +
            "AND active = :active AND date BETWEEN :startDate AND :endDate",
            nativeQuery = true)
    Page<Appointment> findAppointmentsByAssistentIdAndDateRange(
            @Param("assistentId") Long assistentId,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
            @Param("active") Boolean active,
            Pageable pageable
    );

    @Query(value = "SELECT * FROM appointment WHERE assisted_id= :assistedId " +
            "AND active = :active AND date BETWEEN :startDate AND :endDate",
            nativeQuery = true)
    Page<Appointment> findAppointmentsByAssistedIdAndDateRange(
            @Param("assistedId") Long assistedId,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
            @Param("active") Boolean active,
            Pageable pageable
    );

    @Query("SELECT a FROM Appointment a WHERE a.doctor.id = :doctorId AND a.active = :active")
    List<Appointment> findAppointmentByDoctorId(Long doctorId, Boolean active);

    @Query("SELECT a FROM Appointment a WHERE a.assistent.id = :assistantId AND a.active = :active")
    Page<Appointment> findAppointmentByAssistantById(Long assistantId, Boolean active, Pageable pageable);

    @Query(value = "SELECT a FROM Appointment a where a.active = :active AND year(a.date) = :year " +
            " AND month(a.date) = :month AND day(a.date) = :day")
    List<Appointment> findAppointmentsByDateByActive(Boolean active, int day, int month, int year);

    @Query("SELECT a FROM Appointment a WHERE a.assisted.id = :assistedId AND a.active = :active")
    Page<Appointment> findAppointmentByAssistedById(Long assistedId, Boolean active, Pageable pageable);

    @Query(value = "SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END FROM appointment WHERE doctor_id = :doctorId AND assistent_id = :assistentId AND DATE(appointment.date) = DATE(:date)", nativeQuery = true)
    boolean existsByDoctorAndAssistent(@Param("doctorId") Long doctorId,@Param("assistentId") Long assistentId,@Param("date") LocalDateTime date);

    @Query(value = "SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END FROM appointment WHERE doctor_id = :doctorId AND assisted_id = :assistedId AND DATE(appointment.date) = DATE(:date)", nativeQuery = true)
    boolean existsByDoctorAndAssisted(@Param("doctorId") Long doctorId,@Param("assistedId") Long assistedId,@Param("date") LocalDateTime date);
}
