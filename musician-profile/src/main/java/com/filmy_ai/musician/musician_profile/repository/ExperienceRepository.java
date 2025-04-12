package com.filmy_ai.musician.musician_profile.repository;

import com.filmy_ai.musician.musician_profile.model.Experience;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExperienceRepository extends JpaRepository<Experience, Long> {
}
