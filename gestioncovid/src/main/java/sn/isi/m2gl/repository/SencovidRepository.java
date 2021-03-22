package sn.isi.m2gl.repository;

import sn.isi.m2gl.domain.Sencovid;

import org.springframework.data.jpa.repository.*;
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
