package com.example.project;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PetitionController {
   private final PetitionService petitionService;

    public PetitionController(PetitionService petitionService) {
        this.petitionService = petitionService;
    }

    @GetMapping
    public String createPetition(){
        return "create";
    }

    @PostMapping("/view")
    public String createPetition(@RequestParam String title, @RequestParam String description){
        Petition petition= new Petition(title, description);
        petitionService.addPetition(petition);
        return "view";
    }
    @GetMapping("/search")
    public String searchForm() {
        return "search";
    }

    @PostMapping("/searchResult")
    public String searchResult(@RequestParam String query, Model model) {
        model.addAttribute("results", petitionService.searchPetitions(query));
        return "searchResult";
    }

    @GetMapping("/petition/{id}")
    public String viewPetition(@PathVariable int id, Model model) {
        model.addAttribute("petition", petitionService.getPetitionById(id));
        return "petitionDetail";
    }

}
