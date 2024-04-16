package com.s1410.calme.Domain.Entities;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "assisted")
public class Assisted {

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

    @OneToMany(mappedBy = "assisted" , fetch = FetchType.LAZY)
    @JsonManagedReference
    List<RelationAA> relationsAA; //relation assistant to assisted

}
