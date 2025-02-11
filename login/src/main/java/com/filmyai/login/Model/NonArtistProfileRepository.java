package com.filmyai.login.Model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NonArtistProfileRepository extends JpaRepository<NonArtistProfile, Long> {

    // Find non-artist profile by user
    NonArtistProfile findByMyAppUser(MyAppUser myAppUser);

}
