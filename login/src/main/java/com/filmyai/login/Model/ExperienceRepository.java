package com.filmyai.login.Model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ExperienceRepository extends JpaRepository<Experience, Long> {

    
    List<Experience> findByArtistProfile(ArtistProfile artistProfile);

    Experience findByExperienceId(Long experienceId);
}
