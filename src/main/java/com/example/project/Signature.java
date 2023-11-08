/**
 * The Signature class represents a signature with a name and email.
 */
package com.example.project;

import org.springframework.web.bind.annotation.*;



@RestController
public class Signature {
    private String name;
    private String email;

    
   /**
    * The toString() function returns a string representation of the Signature object, including the
    * name and email properties.
    * 
    * @return The `toString()` method is returning a string representation of the object. In this case,
    * it is returning a string that includes the name and email properties of the object.
    */
    @Override
    public String toString() {
        return "Signature{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    
   /**
    * The getName() function returns the name of an object.
    * 
    * @return The method is returning the value of the variable "name".
    */
    public String getName() {
        return name;
    }

    
    /**
     * The function sets the name of an object.
     * 
     * @param name The parameter "name" is a String type parameter.
     */
    public void setName(String name) {
        this.name = name;
    }

   /**
    * The getEmail() function returns the email address.
    * 
    * @return The email address is being returned.
    */
    public String getEmail() {
        return email;
    }

   
    /**
     * The function sets the value of the email variable.
     * 
     * @param email The email parameter is a string that represents the email address.
     */
    public void setEmail(String email) {
        this.email = email;
    }
}

