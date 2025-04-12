package com.filmy_ai.non_artist.non_artist.controller;

import com.filmy_ai.non_artist.non_artist.dto.SlotBookingDTO;
import com.filmy_ai.non_artist.non_artist.model.SlotBooking;
import com.filmy_ai.non_artist.non_artist.service.SlotBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/nonartist/slot")
public class SlotBookingController {

    @Autowired
    private SlotBookingService slotBookingService;


    @PostMapping("create/{profileId}")
    public ResponseEntity<Long> addSlot(@PathVariable Long profileId, @RequestBody SlotBookingDTO dto) {
        SlotBooking slot = slotBookingService.addSlot(profileId, dto);
        return ResponseEntity.ok(slot.getId());
    }

    @PutMapping("update/{id}")
    public ResponseEntity<SlotBooking> updateSlot(@PathVariable Long id, @RequestBody SlotBookingDTO dto) {
        slotBookingService.updateSlot(id, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSlot(@PathVariable Long id) {
        slotBookingService.deleteSlot(id);
        return ResponseEntity.noContent().build();
    }

}
