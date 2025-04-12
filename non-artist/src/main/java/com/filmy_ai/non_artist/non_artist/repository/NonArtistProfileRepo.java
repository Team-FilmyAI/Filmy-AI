package com.filmy_ai.non_artist.non_artist.repository;

import com.filmy_ai.non_artist.non_artist.model.NonArtistProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NonArtistProfileRepo extends JpaRepository<NonArtistProfile, Long> {
}
