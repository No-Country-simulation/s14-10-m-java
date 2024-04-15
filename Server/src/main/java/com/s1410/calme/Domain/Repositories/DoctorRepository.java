package com.s1410.calme.Domain.Repositories;
import com.s1410.calme.Domain.Entities.Doctor;
import com.s1410.calme.Domain.Utils.Specialty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    @Query("SELECT d FROM Doctor d WHERE d.active = true AND d.specialty = :specialty")
    List<Doctor> findBySpecialty(@Param("specialty") Specialty specialty);

    @Query("SELECT d FROM Doctor d WHERE d.active = true AND d.morning = true")
    Page<Doctor> findByMorningAvailabilityAndEnabled(Pageable paging);

    @Query("SELECT d FROM Doctor d WHERE d.active = true AND d.afternoon = true")
    Page<Doctor> findByAfternoonAvailabilityAndEnabled(Pageable paging);

    @Query("SELECT d FROM Doctor d WHERE d.active = true AND d.night = :night")
    Page<Doctor> findByNightAvailabilityAndEnabled(Pageable paging);

    Optional<Doctor> findByEmail(String email);
    Page<Doctor> findAllByActive(Boolean active, Pageable paging);

    @Query("SELECT d FROM Doctor d WHERE d.active = true AND d.postalCode = :postalCode")
    List<Doctor> readAllDoctorsBySamePostalCode(@Param("postalCode") Integer postalCode);

    @Query("SELECT d FROM Doctor d WHERE d.active = true ORDER BY d.lastName ASC")
    Page<Doctor> findBySurnameAsc(Pageable paging);

    @Query("SELECT d FROM Doctor d WHERE d.active = true ORDER BY d.lastName DESC")
    Page<Doctor> findBySurnameDesc(Pageable paging);

}
