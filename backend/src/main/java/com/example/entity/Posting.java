package com.example.entity;

import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime; // Import the LocalDateTime class
import java.time.format.DateTimeFormatter; // Import the DateTimeFormatter class

@Document(collection = "postings")
public class Posting {
    @Id
    private String id;
    private String title;
    private String notes;
    // private LocalDateTime creationDate;
    private String creationDate;
    private int portions;
    private Loc location;

    // getters
    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getNotes() {
        return notes;
    }

    /*
     * public String getCreationDate() {
     * DateTimeFormatter format =
     * DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
     * return creationDate.format(format);
     * 
     * }
     */
    public String getCreationDate() {
        return this.creationDate;
    }

    public int getPortions() {
        return portions;
    }

    public Loc getLocation() {
        return location;
    }

    // setters
    public void setTitle(String title) {
        this.title = title;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    /*
     * public void setCreationDate(String creationDate) {
     * DateTimeFormatter formatter =
     * DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
     * this.creationDate = LocalDateTime.parse(creationDate, formatter);
     * }
     */
    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public void setPortions(int portions) {
        this.portions = portions;
    }

    public void setLocation(Loc location) {
        this.location = location;
    }
}