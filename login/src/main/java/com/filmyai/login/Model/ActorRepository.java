package com.filmyai.login.Model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Long> {
    
    // Method to find an actor by the profile ID
    Actor findByArtistProfile_ProfileId(Long profileId);

}
