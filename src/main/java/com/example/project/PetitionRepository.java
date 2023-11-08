package com.example.project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PetitionRepository extends JpaRepository<Petition, Integer> {
    Optional<Petition> findByTitleContainingIgnoreCase(String title);
}
