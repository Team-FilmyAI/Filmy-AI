package com.filmy_ai.musician.musician_profile.repository;

import com.filmy_ai.musician.musician_profile.model.SlotBooking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SlotBookingRepository extends JpaRepository<SlotBooking, Long> {
}
