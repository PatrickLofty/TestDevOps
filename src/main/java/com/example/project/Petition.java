package com.example.project;

import java.util.ArrayList;
import java.util.List;


public class Petition  {
    private static int idCounter =0;
    private int id;
    private String title;
    private String description;
    private List<Signature> signatures = new ArrayList<>();

    public Petition(String title, String description){
        this.id= idCounter++;
        this.description = description;
        this.title = title;
    }

    public static int getIdCounter() {
        return idCounter;
    }

    public static void setIdCounter(int idCounter) {
        Petition.idCounter = idCounter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Signature> getSignatures() {
        return signatures;
    }

    public void setSignatures(List<Signature> signatures) {
        this.signatures = signatures;
    }




}
