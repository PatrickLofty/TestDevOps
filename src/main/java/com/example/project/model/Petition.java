package com.example.project.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Petition entity with title, description, and signatures.
 */
@Entity
public class Petition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 120)
    private String title;

    @Column(length = 1500)
    private String description;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "petition")
    private List<Signature> signatures = new ArrayList<>();

    /**
     * Default constructor for JPA.
     */
    public Petition() {}

    /**
     * Constructs a new Petition with the specified title and description.
     *
     * @param title The title of the petition.
     * @param description The description of the petition.
     */
    public Petition(String title, String description) {
        this.title = title;
        this.description = description;
    }

    /**
     * Adds a signature to the petition.
     *
     * @param signature The signature to add.
     */
    public void addSignature(Signature signature) {
        this.signatures.add(signature);
    }

    /**
     * Gets the petition's id.
     *
     * @return The id of the petition.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the petition's id.
     *
     * @param id The id to set for the petition.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the title of the petition.
     *
     * @return The title of the petition.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the petition.
     *
     * @param title The title to set for the petition.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the description of the petition.
     *
     * @return The description of the petition.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the petition.
     *
     * @param description The description to set for the petition.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the list of signatures for the petition.
     *
     * @return The list of signatures.
     */
    public List<Signature> getSignatures() {
        return signatures;
    }

    @Override
    public String toString() {
        return "Petition{" + "id=" + id + ", title='" + title + '\'' + ", description='" + description
                + '\'' + ", signatures=" + signatures + '}';
    }
}
