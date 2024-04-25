package com.s1410.calme.Domain.Repositories;

import com.s1410.calme.Domain.Entities.RelationAA;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RelationAARepository extends JpaRepository<RelationAA, Long> {
    boolean existsByAssistedId(Long assistedId);
    boolean existsByAssistentIdAndAssistedId(Long assistentId, Long assistedId);
    @Query("SELECT ra FROM RelationAA ra WHERE ra.assistent.id = :assistentId")
    Page<RelationAA> findByAssistentId(@Param("assistentId") Long assistentId, Pageable pageable);
    List<RelationAA> findAllByAssistedId(Long assistedId);
    Optional<RelationAA> findByAssistentIdAndAssistedId(Long assistentId, Long assistedId);

}
