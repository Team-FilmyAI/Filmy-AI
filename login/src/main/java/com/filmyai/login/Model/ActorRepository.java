package com.filmyai.login.Model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Long> {


    // Find actor by ArtistProfile
    Actor findByArtistProfile(ArtistProfile artistProfile);


}
