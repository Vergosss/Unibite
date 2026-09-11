package com.example.entity;

import java.util.List;

public class Loc {
    private String type;
    private List<Float> coordinates;

    //
    public Loc(String type, List<Float> coordinates) {
        this.type = type;
        this.coordinates = coordinates;
    }

    // getters
    public String getType() {
        return type;
    }

    public List<Float> getCoordinates() {
        return coordinates;
    }

    // setters
    public void setType(String type) {
        this.type = type;
    }

    public void setCoordinates(List<Float> coordinates) {
        this.coordinates = coordinates;
    }
}
