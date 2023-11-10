package com.example.project.repository;

import com.example.project.model.Petition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PetitionRepository extends JpaRepository<Petition, Integer> {

    Optional<Object> findByTitleContainingIgnoreCase(String query);
}
