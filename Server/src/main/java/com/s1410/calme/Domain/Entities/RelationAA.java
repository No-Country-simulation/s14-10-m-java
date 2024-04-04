package com.s1410.calme.Domain.Entities;


import com.s1410.calme.Domain.Utils.RelationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "relations-assistent-assisted")
public class RelationAA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assisted_id")
    Assisted assisted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assistent_id")
    Assistent assistent;

    RelationType relationType;

}
