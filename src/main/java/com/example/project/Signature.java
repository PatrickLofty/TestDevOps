package com.example.project;

/**
 * The type Signature.
 */
public class Signature {
    private String name;
    private String email;

    /**
     * Instantiates a new Signature.
     *
     */
    @Override
    public String toString() {
        return "Signature{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }
}

