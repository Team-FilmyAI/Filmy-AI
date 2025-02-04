package com.filmyai.login.Model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistProfileRepository extends JpaRepository<ArtistProfile, Long> {

    // Find profile by user ID
    ArtistProfile findByUserId(Long userId);
}
