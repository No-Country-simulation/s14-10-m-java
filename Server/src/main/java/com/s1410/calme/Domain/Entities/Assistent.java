package com.s1410.calme.Domain.Entities;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "assistents")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public final class Assistent extends User {

    String email;
    String password;

    @OneToMany(mappedBy = "assistent" , fetch = FetchType.LAZY)
    @JsonManagedReference
    List<RelationAA> relationsAA; //relation assistent to assisted

}
