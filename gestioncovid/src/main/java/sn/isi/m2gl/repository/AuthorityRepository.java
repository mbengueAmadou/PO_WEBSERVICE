package sn.isi.m2gl.repository;

import sn.isi.m2gl.domain.Authority;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Spring Data JPA repository for the {@link Authority} entity.
 */
public interface AuthorityRepository extends JpaRepository<Authority, String> {
    Authority findByName(String name);
}
