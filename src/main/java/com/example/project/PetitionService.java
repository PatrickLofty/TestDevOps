package com.example.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
public class PetitionService {
    private final PetitionRepository petitionRepository;
    private final List<Petition> petitions = new ArrayList<>();

    @Autowired
    public PetitionService(PetitionRepository petitionRepository) {
        this.petitionRepository = petitionRepository;
        petitions.add(new Petition("Increase grants for Electric Vehicles", "We need to increase the grants for electric vehicles to encourage people to buy them"));
        petitions.add(new Petition("Increase grants for Home Energy Upgrades", "We need to increase the grants for home energy upgrades to prevent energy poverty"));
    }

    public Petition searchForSinglePetition(String query) {
        Optional<Petition> petition = petitionRepository.findByTitleContainingIgnoreCase(query);
        return petition.orElse(null);
    }

    public List<Petition> getAllPetitions(){
        return petitions;
    }

    public Petition getPetitionById(int id){
        return petitions.stream()
                .filter(petition -> petition.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public Petition getPetitionByTitle(String title){
        return petitions.stream()
                .filter(petition -> Objects.equals(petition.getTitle(), title))
                .findFirst()
                .orElse(null);
    }

    public Petition getPetitionByDescription(String description){
        return petitions.stream()
                .filter(petition -> Objects.equals(petition.getDescription(), description))
                .findFirst()
                .orElse(null);
    }

    public Petition getPetitionBySignatures(List<Signature> signatures){
        return petitions.stream()
                .filter(petition -> Objects.equals(petition.getSignatures(), signatures))
                .findFirst()
                .orElse(null);
    }

    public boolean addPetition(Petition petition){
        return petitions.add(petition);
    }

    public List<Petition> searchPetitions() { //String query inside search ()
        List<Petition> list = new ArrayList<>();
        for (Petition petition : petitions) {
            if (petition.getSignatures().size() < 10) {
                list.add(petition);
            }
        }
        return list;
    }
}
