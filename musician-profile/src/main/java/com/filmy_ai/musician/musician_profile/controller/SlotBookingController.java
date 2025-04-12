package com.filmy_ai.musician.musician_profile.controller;

import com.filmy_ai.musician.musician_profile.dto.SlotBookingDTO;
import com.filmy_ai.musician.musician_profile.model.SlotBooking;
import com.filmy_ai.musician.musician_profile.service.SlotBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/musician/slot")
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
