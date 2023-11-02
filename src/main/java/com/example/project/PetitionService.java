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

    /**
     * Instantiates a new Petition service.
     */
//seed data
    public PetitionService(){
        petitions.add(new Petition("Increase grants for Electric Vehicles", "We need to increase the grants for electric vehicles to encourage people to buy them"));
        petitions.add(new Petition("Increase grants for Home Energy Upgrades", "We need to increase the grants for home energy upgrades to prevent energy poverty"));

    }

    /**
     * Get all petitions list.
     *
     * @return the list
     */
    public List<Petition> getAllPetitions(){
        return petitions;
    }

    /**
     * Get petition by id petition.
     *
     * @param id the id
     * @return the petition
     */
    public Petition getPetitionById(int id){
        return petitions.stream()
                .filter(petition -> petition
                        .getId() == id)
                        .findFirst()
                        .orElse(null);
    }
    public Petition getPetitionByTitle(String title){
        return petitions.stream()
                .filter(petition -> Objects.equals(petition
                        .getTitle(), title))
                        .findFirst()
                        .orElse(null);
    }
    public Petition getPetitionByDescription(String description){
        return petitions.stream()
                .filter(petition -> Objects.equals(petition
                        .getDescription(), description))
                        .findFirst()
                        .orElse(null);
    }
    public Petition getPetitionBySignatures(List<Signature> signatures){
        return petitions.stream()
                .filter(petition -> petition
                        .getSignatures() == signatures)
                        .findFirst()
                        .orElse(null);
    }


    /**
     * Add petition.
     *
     * @param petition the petition
     */
    public void addPetition(Petition petition){
        petitions.add(petition);
    }

    /**
     * Search petitions list.
     *
     * @param query the query
     * @return the list
     */
    public List<Petition> searchPetitions(String query) {
        return petitions.stream()
                .filter(petition -> petition
                        .getSignatures()
                        .size() < 10)
                        .toList();
    }

}
