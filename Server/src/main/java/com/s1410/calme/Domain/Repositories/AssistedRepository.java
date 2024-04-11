package com.s1410.calme.Domain.Repositories;
import com.s1410.calme.Domain.Entities.Assisted;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AssistedRepository extends JpaRepository <Assisted, Long>{

    Optional<Assisted> findByDNI(String dni);

}
