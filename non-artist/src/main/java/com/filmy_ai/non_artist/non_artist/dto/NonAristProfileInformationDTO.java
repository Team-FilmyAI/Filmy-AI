package com.filmy_ai.non_artist.non_artist.dto;



import com.filmy_ai.non_artist.non_artist.model.AgeRange;
import com.filmy_ai.non_artist.non_artist.model.Ethnicity;
import lombok.Data;

@Data
public class NonAristProfileInformationDTO {

    private String style;
    private String experience;
    private Ethnicity ethnicity;
    private String specialty;
    private String themes;
    private AgeRange AgeRange;
}
