package com.s1410.calme.Domain.Repositories;
import com.s1410.calme.Domain.Entities.Assistent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AssistentRepository extends JpaRepository<Assistent, Long> {
    Page<Assistent> findAllByActive(Boolean active, Pageable paging);
    Optional<Assistent> findByEmail(String email);
}
