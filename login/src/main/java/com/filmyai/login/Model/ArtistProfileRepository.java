package com.filmyai.login.Model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistProfileRepository extends JpaRepository<ArtistProfile, Long> {

    // Find artist profile by user
    ArtistProfile findByMyAppUser(MyAppUser myAppUser);
}
