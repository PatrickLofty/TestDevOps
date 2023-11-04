package com.example.project;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


/**
 * The type Petition controller.
 */
@Controller
public class PetitionController {
   private final PetitionService petitionService;

    /**
     * Instantiates a new Petition controller.
     *
     * @param petitionService the petition service
     */
    public PetitionController(PetitionService petitionService) {
        this.petitionService = petitionService;
    }

  /*  @GetMapping
    public String createPetition(){
        return "create";
    }*/

    /**
     * Create petition string.
     *
     * @param title       the title
     * @param description the description
     * @return the string
     */
    @PostMapping("/create")
    public String createPetition(@RequestParam String title, @RequestParam String description){
        Petition newPetition= new Petition(title, description);
        petitionService.addPetition(newPetition);
        return "index";
    }

    /**
     * Search form string.
     *
     * @return the string
     */
    @GetMapping("/search")
    public String searchForm() {
        return "search";
    }

    /**
     * Search result string.
     *
     * @param query the query
     * @param model the model
     * @return the string
     */
    @PostMapping("/searchResult")
    public String searchResult(@RequestParam String query, Model model) {
        List<Petition> results = petitionService.searchPetitions(query);
        model.addAttribute("petitions", results);
        return "view";
    }


    /**
     * View petition string.
     *
     * @param id    the id
     * @param model the model
     * @return the string
     */
    @GetMapping("/petition/{id}")
    public String viewPetition(@PathVariable int id, Model model) {
        model.addAttribute("petition", petitionService.getPetitionById(id));
        return "petitionDetail";
    }

}
