package com.filmy_ai.musician.musician_profile.repository;

import com.filmy_ai.musician.musician_profile.model.MusicianProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MusicianProfileRepository extends JpaRepository<MusicianProfile, Long> {
}
