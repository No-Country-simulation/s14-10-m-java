package com.s1410.calme.Domain.Repositories;

import com.s1410.calme.Domain.Entities.RelationAA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RelationAARepository extends JpaRepository<RelationAA, Long> {
    boolean existsByAssistedId(Long assistedId);
    boolean existsByAssistentIdAndAssistedId(Long assistentId, Long assistedId);
    List<RelationAA> findAllByAssistentId(Long assistentId);
    List<RelationAA> findAllByAssistedId(Long assistedId);
    Optional<RelationAA> findByAssistentIdAndAssistedId(Long assistentId, Long assistedId);

}
