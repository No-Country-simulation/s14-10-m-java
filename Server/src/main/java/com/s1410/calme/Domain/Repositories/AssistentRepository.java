package com.s1410.calme.Domain.Repositories;

import com.s1410.calme.Domain.Entities.Assistent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssistentRepository extends JpaRepository<Assistent, Long> {
}
