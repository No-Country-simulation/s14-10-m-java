package com.s1410.calme.Domain.Entities;
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
    String firstName;
    String secondName;
    String lastName;
    Boolean active = true; // logic delete

}
