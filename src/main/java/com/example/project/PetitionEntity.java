package com.example.project;

import java.util.ArrayList;
import java.util.List;


public class Petition  {
    // declaring and initialising instance variables for the `Petition` class.
    private static int idCounter =0;
    private int id;
    private String title;
    private String description;
    private List<Signature> signatures = new ArrayList<>();

    // The `public Petition(String title, String description)` is a constructor method for the
    // `Petition` class. It is used to create a new instance of the `Petition` class with the provided
    // `title` and `description` parameters.
    public Petition(String title, String description){
        this.id= idCounter++;
        this.description = description;
        this.title = title;
    }



    
   /**
    * The function returns the value of the id variable.
    * 
    * @return The method is returning the value of the variable "id".
    */
    public int getId() {
        return id;
    }


    /**
     * The getTitle() function returns the title of an object.
     * @return The method is returning the value of the variable "title".
     */
    public String getTitle() {
        return title;
    }

    /**
     * The function sets the title of an object.
     * @param title The title parameter is a string that represents the title of something, such as a
     * book, movie, or article.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * The getDescription() function returns the description of an object.
     * 
     * @return The method is returning a String value.
     */
    public String getDescription() {
        return description;
    }



    /**
     * The function returns a list of Signature objects.
     * 
     * @return A List of Signature objects is being returned.
     */
    public List<Signature> getSignatures() {
        return signatures;
    }


    /**
     * The function adds a signature to a collection if it is not already present and returns true,
     * otherwise it returns false.
     * 
     * @param signature The parameter "signature" is of type Signature, which represents a signature
     * object.
     * @return The method is returning a boolean value. If the signature is already present in the list
     * of signatures, it returns false. Otherwise, it adds the signature to the list and returns true.
     */
    public boolean addSignature(Signature signature) {
        if (this.signatures.contains(signature)) {
            return false;
        } else {
            this.signatures.add(signature);
            return true;
        }
    }



}
