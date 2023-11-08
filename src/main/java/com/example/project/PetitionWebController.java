package com.example.project;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * The type Petition controller.
 */
@Controller
public class PetitionController {
   private final PetitionService petitionService;

    
    public PetitionController(PetitionService petitionService) {
        this.petitionService = petitionService;
    }

  
    @PostMapping("/create")
    public String createPetition(@RequestParam String title, @RequestParam String description){
        Petition newPetition= new Petition(title, description);
        boolean isAdded = petitionService.addPetition(newPetition);
        if (isAdded) {
            return "";
        } else {
            model.addAttribute("errorMessage", "There was a problem creating the petition, please try again");
            return "petitionDetail"
            
        }
    }

    
    @GetMapping("/search")
    public String searchForm() {
        return "search";
    }

    
    @PostMapping("/searchResult")
    public String searchResult(@RequestParam String query, Model model) {
        List<Petition> results = petitionService.searchPetitions(query);
        if (results.isEmpty()) {
            model.addAttribute("errorMessage", "No results found");
        } else {
            model.addAttribute("petitions", results);
            return "view";
        }
    }


    
    @GetMapping("/petition/{id}")
    public String viewPetition(@PathVariable int id, Model model) {
        Petition petition = petitionService.getPetitionById(id);
        if (petition == null) {
            model.addAttribute("errorMessage", "Petition not found");
            return "error";
        } else {
            model.addAttribute("petition", petition);
            return "petitionDetail";
        }
    }

    @PostMapping("/petition/{id}/sign")
    public String signPetition(@PathVariable int id, @ModelAttribute Signature signature, Model model) {
        Petition petition = petitionService.getPetitionById(id);
        if (petition == null) {
            model.addAttribute("errorMessage", "Petition not found");
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
            petitions = petitionService.searchPetitions(query);
        } else {
            petitions = petitionService.getAllPetitions(); // Or a subset if you prefer
        }
        model.addAttribute("petitions", petitions);
        return "searchAndViewPetitions"; // The HTML page
    }



}
