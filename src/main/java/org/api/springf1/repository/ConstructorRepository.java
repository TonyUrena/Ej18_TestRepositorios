package org.api.springf1.repository;

import org.api.springf1.model.Constructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConstructorRepository extends JpaRepository<Constructor, Long> {
    Optional<Constructor> findByRefIgnoreCase(String ref);
    void deleteByRefIgnoreCase(String ref);
}
