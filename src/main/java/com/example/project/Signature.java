package com.example.project;

public class Signature{
    private String name;
    private String email;
    public Signature(String name, String email){
        this.email = email;
        this.name = name;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

