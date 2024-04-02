package com.AppTurnos.s1410.Domain.Entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "assisted")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Assisted extends User {

    @OneToMany(mappedBy = "assisted" , fetch = FetchType.LAZY)
    List<RelationAA> relationsAA; //relation assistent to assisted

}
