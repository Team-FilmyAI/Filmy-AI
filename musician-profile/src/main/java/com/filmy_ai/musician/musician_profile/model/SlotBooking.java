package com.filmy_ai.musician.musician_profile.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class SlotBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private SlotStatus status; // AVAILABLE or BOOKED

    private String message;

    @ManyToOne
    @JoinColumn(name = "profile_id", nullable = false)
    private MusicianProfile profile;
}
