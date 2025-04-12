package com.filmy_ai.musician.musician_profile.repository;

import com.filmy_ai.musician.musician_profile.model.MusicianPerformanceInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MusicianPerformanceInfoRepository extends JpaRepository<MusicianPerformanceInfo, Long> {
}
