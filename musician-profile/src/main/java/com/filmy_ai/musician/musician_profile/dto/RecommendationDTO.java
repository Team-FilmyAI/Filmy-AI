package com.filmy_ai.musician.musician_profile.dto;

import lombok.Data;

@Data
public class RecommendationDTO {
    private String organizationName;
    private String workedAt;
    private String recommendationText;
    private String recommendationDate;
    private Boolean visible;
}
