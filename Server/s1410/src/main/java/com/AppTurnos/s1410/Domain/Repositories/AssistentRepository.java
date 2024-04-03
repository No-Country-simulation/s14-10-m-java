package com.AppTurnos.s1410.Domain.Repositories;

import com.AppTurnos.s1410.Domain.Entities.Assistent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssistentRepository extends JpaRepository<Assistent, Long> {
}
