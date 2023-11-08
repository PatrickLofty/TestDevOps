import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetitionRepository extends JpaRepository<Petition, Long> {
    // This method signature assumes that you want to search petitions by title.
    // It will find any petitions where the title contains the search query, ignoring case.
    Optional<Petition> findByTitleContainingIgnoreCase(String title);
}