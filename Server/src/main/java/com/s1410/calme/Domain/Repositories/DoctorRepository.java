package com.s1410.calme.Domain.Repositories;
import com.s1410.calme.Domain.Entities.Assistent;
import com.s1410.calme.Domain.Entities.Doctor;
import com.s1410.calme.Domain.Utils.Availability;
import com.s1410.calme.Domain.Utils.Specialty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    @Query("SELECT d FROM Doctor d WHERE d.specialty = :specialty")
    Doctor findBySpecialty(@Param("specialty") Specialty specialty);

    @Query("SELECT d FROM Doctor d WHERE d.availability = :availability")
    Doctor findByAvailability(@Param("availability")Availability availability);

    Optional<Doctor> findByEmail(String email);
    Page<Doctor> findAllByActive(Boolean active, Pageable paging);

}
