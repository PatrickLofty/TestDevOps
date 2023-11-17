package com.example.project.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.project.model.Petition;

/**
 * JPA repository for Petition entities.
 */
public interface PetitionRepository extends JpaRepository<Petition, Long> {

    /**
     * Finds petitions where the title contains the given string.
     *
     * @param title The search query for the title.
     * @return A list of petitions that contain the query in the title.
     */
    List<Petition> findByTitleContaining(String title);

    /**
     * Finds petitions where the description contains the given string.
     *
     * @param description The search query for the description.
     * @return A list of petitions that contain the query in the description.
     */
    List<Petition> findByDescriptionContaining(String description);

    /**
     * Finds petitions where either the title or the description contains the given string.
     *
     * @param title The search query for the title.
     * @param description The search query for the description.
     * @return A list of petitions that contain the query in either the title or the description.
     */
    List<Petition> findByTitleContainingOrDescriptionContaining(String title, String description);
}
