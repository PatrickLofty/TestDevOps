package com.example.project.model;

import jakarta.persistence.*;

/**
 * Represents a signature for a petition.
 */
@Entity
public class Signature {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;

    @ManyToOne
    @JoinColumn(name="petition_id", nullable=false)
    private Petition petition;

    /**
     * Default constructor for JPA.
     */
    public Signature() {}

    /**
     * Constructs a new Signature with the specified name and email.
     *
     * @param name The name of the person signing the petition.
     * @param email The email of the person signing the petition.
     */
    public Signature(String name, String email) {
        this.name = name;
        this.email = email;
    }

    /**
     * Gets the id of the signature.
     *
     * @return The id of the signature.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the id of the signature.
     *
     * @param id The id to set for the signature.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the name of the person who signed the petition.
     *
     * @return The name of the person.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the person who signed the petition.
     *
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the email of the person who signed the petition.
     *
     * @return The email of the person.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the person who signed the petition.
     *
     * @param email The email to set.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the petition associated with this signature.
     *
     * @return The associated petition.
     */
    public Petition getPetition() {
        return petition;
    }

    /**
     * Sets the petition associated with this signature.
     *
     * @param petition The petition to associate.
     */
    public void setPetition(Petition petition) {
        this.petition = petition;
    }

    @Override
    public String toString() {
        return "Signature{" + "id=" + id + ", name='" + name + '\'' + ", email='" + email + '\'' + '}';
    }

    public void addPetition(Petition petition) {
        this.petition = petition;
    }
}
