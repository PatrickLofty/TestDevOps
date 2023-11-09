package com.example.project;

import org.apache.catalina.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping;


import java.util.List;

@Controller
public class PetitionController {
    private final PetitionService petitionService;
    private static final String ERROR_MESSAGE = "errorMessage";
    private final BeanNameUrlHandlerMapping name;

    public PetitionController(PetitionService petitionService, BeanNameUrlHandlerMapping name) {
        this.petitionService = petitionService;
        this.name = name;
    }

    @PostMapping("/create")
    public String createPetition(@RequestParam String title, @RequestParam String description, Model model) {
        Petition newPetition = new Petition(title, description);
        boolean isAdded = petitionService.addPetition(newPetition);
        if (isAdded) {
            return "redirect:/viewAllPetitions";
        } else {
            model.addAttribute(ERROR_MESSAGE, "There was a problem creating the petition, please try again");
            return "viewAllPetitions";
        }
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("name", new name()); // Ensure this object has an 'email' field
        return "registration";
    }


    @GetMapping("/create")
    public String showCreatePetitionForm(Model model) {
        model.addAttribute("petition", new Petition());
        return "index";
    }

    @GetMapping("/viewAllPetitions")
    public String showForm(Model model) {
        List<Petition> allPetitions = petitionService.getAllPetitions();
        model.addAttribute("petitions", allPetitions);
        return "viewAllPetitions";
    }

    @PostMapping("/searchResult")
    public String searchResult(@RequestParam String query, Model model) {
        List<Petition> results = petitionService.searchPetitions(query);
        if (results.isEmpty()) {
            model.addAttribute(ERROR_MESSAGE, "No results found");
            return "searchAndViewPetitions";
        } else {
            model.addAttribute("petitions", results);
            return "viewAllPetitions";
        }
    }

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
            return "petitionDetail";
        }
    }

    @GetMapping("/search")
    public String searchAndViewPetitions(@RequestParam(required = false) String query, Model model) {
        List<Petition> petitions;
        if (query != null && !query.isEmpty()) {
            petitions = petitionService.searchPetitions(query);
        } else {
            petitions = petitionService.getAllPetitions();
        }
        model.addAttribute("petitions", petitions);
        return "searchAndViewPetitions";
    }
}
