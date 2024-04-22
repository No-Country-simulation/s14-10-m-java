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
    String imageUrl = "https://t4.ftcdn.net/jpg/00/64/67/27/360_F_64672736_U5kpdGs9keUll8CRQ3p3YaEv2M6qkVY5.jpg";

}
