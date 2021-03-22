package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Sencovid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Sencovid entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SencovidRepository extends JpaRepository<Sencovid, Long> {

    @Query("select c  from Sencovid c where c.id=:id")
    Sencovid getCovidInfoById(Long id);
}
