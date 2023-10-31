package com.example.project;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PetitionService {
    private List<Petition> petitions = new ArrayList<>();

            //seed data
    public PetitionService(){
        petitions.add(new Petition("Increase grants for Electric Vehicles", "We need to increase the grants for electric vehicles to encourage people to buy them"));
        petitions.add(new Petition("Increase grants for Home Energy Upgrades", "We need to increase the grants for home energy upgrades to prevent energy poverty"));

    }
    public List<Petition> getAllPetitions(){
        return petitions;
    }

    public Petition getPetitionById(int id){
        return petitions.stream()
                .filter(petition -> petition
                        .getId() == id)
                        .findFirst()
                        .orElse(null);
    }
    public void addPetition(Petition petition){
        petitions.add(petition);
    }

    public List<Petition> searchPetitions(String query) {
        return petitions.stream()
                .filter(petition -> petition
                        .getSignatures()
                        .size() < 10)
                        .toList();
    }

}
