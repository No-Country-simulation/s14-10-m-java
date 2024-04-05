package com.s1410.calme.Domain.Entities;
import com.s1410.calme.Domain.Utils.Specialty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Doctor extends User{

    private Long licenseNumber;
    private String address;
    @Enumerated(EnumType.STRING)
    private Specialty specialty;
    private String email;
    private String password;

}
