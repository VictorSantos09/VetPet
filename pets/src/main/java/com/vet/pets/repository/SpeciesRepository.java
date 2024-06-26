package com.vet.pets.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vet.pets.entities.Species;

public interface SpeciesRepository extends JpaRepository<Species, Long> {
    @Query("SELECT s FROM Species s WHERE s.name = ?1")
    Optional<Species> findByName(String name);
}
