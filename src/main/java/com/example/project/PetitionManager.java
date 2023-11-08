/**
 * The `PetitionService` class is a service class that manages a list of petitions and provides methods
 * to retrieve, add, and search for petitions.
 */
package com.example.project;

import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The type Petition service.
 */
@Service
public class PetitionService {
    private List<Petition> petitions = new ArrayList<>();

    @Autowired
    public PetitionService(PetitionRepository petitionRepository) {
        this.petitionRepository = petitionRepository;
    }

    public Petition searchForSinglePetition(String query) {
        // This assumes that there's a method in the repository that can find a petition by a search term.
        // The actual implementation would depend on how your data is structured and how you want to search for it.
        Optional<Petition> petition = petitionRepository.findByTitleContainingIgnoreCase(query);
        return petition.orElse(null); // Returns the petition if found, or null otherwise
    }

    // The `public PetitionService()` is a constructor for the `PetitionService` class. It is called
    // when an instance of the `PetitionService` class is created.
    public PetitionService(){
        petitions.add(new Petition("Increase grants for Electric Vehicles", "We need to increase the grants for electric vehicles to encourage people to buy them"));
        petitions.add(new Petition("Increase grants for Home Energy Upgrades", "We need to increase the grants for home energy upgrades to prevent energy poverty"));

    }

    
    /**
     * The function getAllPetitions returns a list of all petitions.
     * 
     * @return A List of Petition objects.
     */
    public List<Petition> getAllPetitions(){
        return petitions;
    }
    
    /**
     * The function returns a Petition object from a list of petitions based on the given id, or null
     * if no matching petition is found.
     * 
     * @param id The parameter "id" is an integer representing the unique identifier of the petition
     * that we want to retrieve.
     * @return The method is returning a Petition object.
     */
    public Petition getPetitionById(int id){
        return petitions.stream()
                .filter(petition -> petition
                        .getId() == id)
                        .findFirst()
                        .orElse(null);
    }

    /**
     * The function returns a Petition object from a list of petitions based on its title.
     * 
     * @param title The title of the petition that you want to retrieve.
     * @return The method is returning a Petition object.
     */
    public Petition getPetitionByTitle(String title){
        return petitions.stream()
                .filter(petition -> Objects.equals(petition
                        .getTitle(), title))
                        .findFirst()
                        .orElse(null);
    }

    /**
     * The function returns a Petition object from a list of petitions based on a given description.
     * 
     * @param description A string representing the description of the petition to search for.
     * @return The method is returning a Petition object.
     */
    public Petition getPetitionByDescription(String description){
        return petitions.stream()
                .filter(petition -> Objects.equals(petition
                        .getDescription(), description))
                        .findFirst()
                        .orElse(null);
    }

    /**
     * The function returns a Petition object from a list of Petitions based on a given list of
     * Signatures.
     * 
     * @param signatures A list of Signature objects.
     * @return The method is returning a Petition object.
     */
    public Petition getPetitionBySignatures(List<Signature> signatures){
        return petitions.stream()
                .filter(petition -> petition
                        .getSignatures() == signatures)
                        .findFirst()
                        .orElse(null);
    }

    /**
     * The function adds a Petition object to a list of petitions.
     * 
     * @param petition The parameter "petition" is of type Petition, which means it is an object
     * representing a petition.
     */
    public void addPetition(Petition petition){
        petitions.add(petition);
    }

    /**
     * The function searches for petitions with less than 10 signatures and returns a list of matching
     * petitions.
     * 
     * @param query The "query" parameter is a string that represents the search query used to filter
     * the list of petitions.
     * @return The method is returning a list of Petition objects.
     */
    public List<Petition> searchPetitions(String query) {
        return petitions.stream()
                .filter(petition -> petition
                        .getSignatures()
                        .size() < 10)
                        .toList();
    }

}
