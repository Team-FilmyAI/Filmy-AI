package com.filmy_ai.musician.musician_profile.dto;

import com.filmy_ai.musician.musician_profile.model.SlotStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class SlotBookingDTO {
    private LocalDate date;
    private SlotStatus status;
    private String message;
}
