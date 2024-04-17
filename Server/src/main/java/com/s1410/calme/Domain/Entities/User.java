package com.s1410.calme.Domain.Entities;
import com.s1410.calme.Domain.Utils.RolesEnum;
import jakarta.persistence.*;

import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;


import java.time.LocalDate;
import java.util.List;

@Data
@MappedSuperclass
public abstract class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long Id;
    String DNI; // Example 38518932 (without dots)
    LocalDate dateOfBirth;
    String firstName;
    String secondName;
    String lastName;
    Long phoneNumber;
    Boolean active = true; // logic delete
}
