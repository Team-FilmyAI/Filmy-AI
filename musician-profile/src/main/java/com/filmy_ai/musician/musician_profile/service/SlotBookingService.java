package com.filmy_ai.musician.musician_profile.service;

import com.filmy_ai.musician.musician_profile.dto.SlotBookingDTO;
import com.filmy_ai.musician.musician_profile.exception.ResourceNotFoundException;
import com.filmy_ai.musician.musician_profile.model.MusicianProfile;
import com.filmy_ai.musician.musician_profile.model.SlotBooking;
import com.filmy_ai.musician.musician_profile.repository.MusicianProfileRepository;
import com.filmy_ai.musician.musician_profile.repository.SlotBookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SlotBookingService {


    private final SlotBookingRepository slotRepo;
   private final MusicianProfileRepository profileRepo;

    public SlotBookingService(SlotBookingRepository slotRepo, MusicianProfileRepository profileRepo) {
        this.slotRepo = slotRepo;
        this.profileRepo = profileRepo;
    }

    public SlotBooking addSlot(Long profileId, SlotBookingDTO dto) {
        MusicianProfile profile = profileRepo.findById(profileId).orElseThrow(() -> new ResourceNotFoundException("Profile not found"));
        SlotBooking slot = new SlotBooking();
        slot.setProfile(profile);
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

    private void updateSlotFromDTO(SlotBooking slot, SlotBookingDTO dto) {
        slot.setDate(dto.getDate());
        slot.setStatus(dto.getStatus());
        slot.setMessage(dto.getMessage());
    }
}
