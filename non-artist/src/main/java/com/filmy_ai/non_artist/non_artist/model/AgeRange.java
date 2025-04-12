package com.filmy_ai.non_artist.non_artist.model;

public enum AgeRange {

    AGE_18_24("18-24"),
    AGE_25_35("25-35"),
    AGE_36_45("36-45"),
    AGE_46_PLUS("46+");

    private final String label;

    AgeRange(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}