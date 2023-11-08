package com.example.project;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The type Petition controller.
 */
@RestController
public class PetitionController {
   private final PetitionService petitionService;
    private static final String ERROR_MESSAGE = "errorMessage";
    
    public PetitionController(PetitionService petitionService) {
        this.petitionService = petitionService;
    }

  
    @PostMapping("/create")
    public String createPetition(@RequestParam String title, @RequestParam String description, Model model){
        Petition newPetition= new Petition(title, description);
        boolean isAdded = petitionService.addPetition(newPetition);
        if (isAdded) {
            return "";
        } else {
            model.addAttribute(ERROR_MESSAGE, "There was a problem creating the petition, please try again");
            return "petitionDetailAndSign";
            
        }
    }

    @GetMapping("/create")
    public String showForm(Model model) {
        model.addAttribute("petition", new Petition());
        return "index";
    }

    @PostMapping("/create")
    public void submitForm(Petition petition) {
        petitionService.addPetition(petition);// Return to a success page
    }

    
    @GetMapping("/search")
    public String searchForm() {
        return "SearchPetitionsForm";
    }

    
    @PostMapping("/searchResult")
    public String searchResult(@RequestParam String query, Model model) {
        List<Petition> results = petitionService.searchPetitions();
        if (results.isEmpty()) {
            model.addAttribute(ERROR_MESSAGE, "No results found");
        } else {
            model.addAttribute("petitions", results);
            return "viewAllPetitions";
        }
        return query;
    }


    
   /* @GetMapping("/petition/{id}")
    public String viewPetition(@PathVariable int id, Model model) {
        Petition petition = petitionService.getPetitionById(id);
        if (petition == null) {
            model.addAttribute("errorMessage", "Petition not found");
            return "error";
        } else {
            model.addAttribute("petition", petition);
            return "petitionDetail";
        }
    }*/

    @PostMapping("/petition/{id}/sign")
    public String signPetition(@PathVariable int id, @ModelAttribute Signature signature, Model model) {
        Petition petition = petitionService.getPetitionById(id);
        if (petition == null) {
            model.addAttribute(ERROR_MESSAGE, "Petition not found");
            return "error";
        } else {
            boolean signatureAdded = petition.addSignature(signature);
            if (!signatureAdded) {
                model.addAttribute("signatureError", "Signature already exists.");
            }
            model.addAttribute("petition", petition);
            return "petitionDetail"; // The name of the HTML page to return
        }
    }

    @GetMapping("/search")
    public String searchAndViewPetitions(@RequestParam(required = false) String query, Model model) {
        List<Petition> petitions;
        if (query != null && !query.isEmpty()) {
            petitions = petitionService.searchPetitions();
        } else {
            petitions = petitionService.getAllPetitions(); // Or a subset if you prefer
        }
        model.addAttribute("petitions", petitions);
        return "searchAndViewPetitions"; // The HTML page
    }



}
