package com.filmy_ai.musician.musician_profile.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class MusicianPerformanceInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String instrumentPlayed;
    private String vocalRange;
    private String ethnicity;
    private String stagePresence;
    private String ageRange;

    //Relationships

    @OneToOne
    @JoinColumn(name = "profile_id", nullable = false)
    private MusicianProfile profile;
}
