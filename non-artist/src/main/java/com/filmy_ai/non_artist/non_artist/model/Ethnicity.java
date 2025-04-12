package com.filmy_ai.non_artist.non_artist.model;

public enum Ethnicity {
    AFRICAN_AMERICAN("African American"),
    ASIAN("Asian"),
    CAUCASIAN("Caucasian"),
    HISPANIC("Hispanic"),
    OTHER("Other");

    private final String label;

    Ethnicity(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}

