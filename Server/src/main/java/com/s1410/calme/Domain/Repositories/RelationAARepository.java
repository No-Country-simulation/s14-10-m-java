package com.s1410.calme.Domain.Repositories;

import com.s1410.calme.Domain.Entities.RelationAA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelationAARepository extends JpaRepository<RelationAA, Long> {

    boolean existsByAssistedId(Long assistedId);

}
