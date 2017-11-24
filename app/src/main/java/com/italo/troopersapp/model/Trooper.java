package com.italo.troopersapp.model;

import java.io.Serializable;

/**
 * Created by italo on 18/11/2017.
 */

public class Trooper implements Serializable {

    private int id;
    private String name;
    private String description;
    private String imageUrl;
    private Affiliation affiliation;

    public Trooper(int id, String name, String imageUrl, String description, Affiliation affiliation) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.affiliation = affiliation;
    }

    public Trooper() {

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Affiliation getAffiliation() {
        return affiliation;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setAffiliation(Affiliation affiliation) {
        this.affiliation = affiliation;
    }
}
