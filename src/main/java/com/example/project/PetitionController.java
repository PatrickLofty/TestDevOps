package com.example.project;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class PetitionController {
   private final PetitionService petitionService;

    public PetitionController(PetitionService petitionService) {
        this.petitionService = petitionService;
    }

  /*  @GetMapping
    public String createPetition(){
        return "create";
    }*/

    @PostMapping("/create")
    public String createPetition(@RequestParam String title, @RequestParam String description){
        Petition newPetition= new Petition(title, description);
        petitionService.addPetition(newPetition);
        return "Create";
    }
    @GetMapping("/search")
    public String searchForm() {
        return "search";
    }

    @PostMapping("/searchResult")
    public String searchResult(@RequestParam String query, Model model) {
        List<Petition> results = petitionService.searchPetitions(query);
        model.addAttribute("petitions", results);
        return "view";
    }


    @GetMapping("/petition/{id}")
    public String viewPetition(@PathVariable int id, Model model) {
        model.addAttribute("petition", petitionService.getPetitionById(id));
        return "petitionDetail";
    }

}
