package com.hoangmn.model;

import java.io.Serializable;

public class Movie implements Serializable {

    private int id;
    private String title;
    private String description;
    private String trailer;

    public Movie() {
    }

    public Movie(int id, String title, String description, String trailer) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.trailer = trailer;
    }

    public Movie(String title, String description, String trailer) {
        this.title = title;
        this.description = description;
        this.trailer = trailer;
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

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

}
