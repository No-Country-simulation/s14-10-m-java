package com.s1410.calme.Domain.Repositories;
import com.s1410.calme.Domain.Entities.Doctor;
import com.s1410.calme.Domain.Utils.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    @Query("SELECT d FROM Doctor d WHERE d.specialty = :specialty")
    Doctor findBySpecialty(@Param("specialty") Specialty specialty);

}
