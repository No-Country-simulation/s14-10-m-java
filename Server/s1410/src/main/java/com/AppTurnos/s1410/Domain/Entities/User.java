package com.AppTurnos.s1410.Domain.Entities;
import jakarta.persistence.*;

import lombok.Data;


import java.time.LocalDate;
import java.util.List;

@Data
@MappedSuperclass
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long Id;
    String DNI; // Example 38518932 (without dots)
    LocalDate dateOfBirth;
    //@OneToMany(mappedBy = "users")
    //List<Appointment> AppointmentList;
    Boolean active; // logic delete

}
