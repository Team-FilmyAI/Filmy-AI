package com.filmy_ai.musician.musician_profile.repository;

import com.filmy_ai.musician.musician_profile.model.RecommendationDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecommendationDetailRepository extends JpaRepository<RecommendationDetail, Long> {
}
