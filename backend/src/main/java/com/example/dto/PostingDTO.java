package com.example.dto;

import org.springframework.data.annotation.Id;

import com.example.entity.Loc;

public class PostingDTO {

    private String title;
    private String notes;
    private int portions;
    //private Loc location;

    // getter
    public String getTitle() {
        return title;
    }

    public String getNotes() {
        return notes;
    }

    public int getPortions() {
        return portions;
    }
/* 
    public Loc getLocation() {
        return location;
    }
*/
    // setters
    public void setTitle(String title) {
        this.title = title;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setPortions(int portions) {
        this.portions = portions;
    }
/* 
    public void setLocation(Loc location) {
        this.location = location;
    }
        */
}
