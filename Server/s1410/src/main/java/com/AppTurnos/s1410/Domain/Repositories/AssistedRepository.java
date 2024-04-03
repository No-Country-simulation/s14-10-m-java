package com.AppTurnos.s1410.Domain.Repositories;

import com.AppTurnos.s1410.Domain.Entities.Assisted;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssistedRepository extends JpaRepository <Assisted, Long>{
}
