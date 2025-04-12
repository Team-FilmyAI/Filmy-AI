package com.filmy_ai.non_artist.non_artist.dto;

import com.filmy_ai.non_artist.non_artist.model.SlotStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class SlotBookingDTO {
    private LocalDate date;
    private SlotStatus status;
    private String message;
}
