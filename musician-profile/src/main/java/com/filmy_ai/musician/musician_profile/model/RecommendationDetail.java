package com.filmy_ai.musician.musician_profile.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class RecommendationDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Organization name is required")
    private String organizationName;

    private String workedAt;

    @NotBlank(message = "Recommendation text is required")
    @Column(columnDefinition = "TEXT")
    private String recommendationText;

    @NotBlank(message = "Date is required")
    private String recommendationDate;

    private Boolean visible = true;

    @ManyToOne
    @JoinColumn(name = "profile_id", nullable = false)
    private MusicianProfile profile;
}
