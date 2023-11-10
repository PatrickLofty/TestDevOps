package com.example.project.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Petition {
    private static final AtomicInteger idCounter = new AtomicInteger(0);
    private final int id;
    private String title;
    private String description;
    private final List<Signature> signatures = new ArrayList<>();

    public Petition(String title, String description) {
        this.id = idCounter.getAndIncrement();
        this.title = title;
        this.description = description;
    }

    public Petition() {
        this.id = idCounter.getAndIncrement();
    }

    public int getId() {
        return id;
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

    public Collection<Object> getSignatures() {
        return Collections.emptyList();
    }

    public boolean addSignature(Signature signature) {
        return false;
    }
}
