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
@Table(name = "assisted")
public class Assisted extends User {

    @OneToMany(mappedBy = "assisted" , fetch = FetchType.LAZY)
    @JsonManagedReference
    List<RelationAA> relationsAA; //relation assistant to assisted

}
