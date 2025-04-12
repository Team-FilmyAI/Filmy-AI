package com.filmy_ai.non_artist.non_artist.service;

import com.filmy_ai.non_artist.non_artist.dto.SlotBookingDTO;
import com.filmy_ai.non_artist.non_artist.exception.ResourceNotFoundException;
import com.filmy_ai.non_artist.non_artist.model.NonArtistProfile;
import com.filmy_ai.non_artist.non_artist.model.SlotBooking;
import com.filmy_ai.non_artist.non_artist.repository.NonArtistProfileRepo;
import com.filmy_ai.non_artist.non_artist.repository.SlotBookingRepository;
import org.springframework.stereotype.Service;

@Service
public class SlotBookingService {


    private final SlotBookingRepository slotRepo;
    private final NonArtistProfileRepo nonArtistProfileRepo;

    public SlotBookingService(SlotBookingRepository slotRepo, NonArtistProfileRepo nonArtistProfileRepo) {
        this.slotRepo = slotRepo;
        this.nonArtistProfileRepo = nonArtistProfileRepo;
    }

    private void updateSlotFromDTO(SlotBooking slot, SlotBookingDTO dto) {
        slot.setDate(dto.getDate());
        slot.setStatus(dto.getStatus());
        slot.setMessage(dto.getMessage());
    }

    public SlotBooking addSlot(Long profileId, SlotBookingDTO dto) {
        NonArtistProfile profile = nonArtistProfileRepo.findById(profileId).orElseThrow(() -> new ResourceNotFoundException("Profile not found"));
        SlotBooking slot = new SlotBooking();
        slot.setNonArtistProfile(profile);
        updateSlotFromDTO(slot, dto);
        return slotRepo.save(slot);
    }

    public SlotBooking updateSlot(Long id, SlotBookingDTO dto) {
        SlotBooking slot = slotRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Slot not found"));
        updateSlotFromDTO(slot, dto);
        return slotRepo.save(slot);
    }

    public void deleteSlot(Long id) {
        slotRepo.deleteById(id);
    }
}
