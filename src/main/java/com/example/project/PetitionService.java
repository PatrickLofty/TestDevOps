package com.example.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PetitionService {
    private final PetitionRepository petitionRepository;

    @Autowired
    public PetitionService(PetitionRepository petitionRepository) {
        this.petitionRepository = petitionRepository;
    }

    public Petition searchForSinglePetition(String query) {
        return petitionRepository
                .findByTitleContainingIgnoreCase(query)
                .orElse(null);
    }

    public List<Petition> getAllPetitions() {
        return petitionRepository.findAll();
    }

    public Petition getPetitionById(int id) {
        return petitionRepository.findById(id).orElse(null);
    }

    public boolean addPetition(Petition petition) {
        petitionRepository.save(petition);
        return true;
    }

    public List<Petition> searchPetitions(String query) {
        return petitionRepository.findByTitleContainingIgnoreCase(query).stream()
                .filter(petition -> petition.getSignatures().size() < 10)
                .collect(Collectors.toList());
    }
}
