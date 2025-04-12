package com.filmy_ai.musician.musician_profile;

import com.filmy_ai.musician.musician_profile.dto.ExperienceDTO;
import com.filmy_ai.musician.musician_profile.exception.ResourceNotFoundException;
import com.filmy_ai.musician.musician_profile.model.Experience;
import com.filmy_ai.musician.musician_profile.model.MusicianProfile;
import com.filmy_ai.musician.musician_profile.repository.ExperienceRepository;
import com.filmy_ai.musician.musician_profile.repository.MusicianProfileRepository;
import com.filmy_ai.musician.musician_profile.service.ExperienceService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExperienceServiceTest {

    @Mock
    private ExperienceRepository experienceRepo;

    @Mock
    private MusicianProfileRepository profileRepo;

    @InjectMocks
    private ExperienceService experienceService;

    @Test
    void addExperience_shouldSaveExperienceSuccessfully() {
        // Arrange
        Long profileId = 1L;
        ExperienceDTO dto = new ExperienceDTO();
        dto.setSongTitle("Hasi");
        dto.setSingers("Shreya Ghoshal");
        dto.setGenre("Pop");
        dto.setMonth("April");
        dto.setYear("2024");
        dto.setPosterUrl("some-url");

        MusicianProfile profile = new MusicianProfile();
        profile.setId(profileId);

        when(profileRepo.findById(profileId)).thenReturn(Optional.of(profile));
        when(experienceRepo.save(any(Experience.class))).thenAnswer(i -> i.getArgument(0));

        // Act
        Experience saved = experienceService.addExperience(profileId, dto);

        // Assert
        assertEquals("Hasi", saved.getSongTitle());
        assertEquals("Pop", saved.getGenre());
        assertEquals("April", saved.getMonth());
        assertEquals(profile, saved.getProfile());

        verify(experienceRepo, times(1)).save(any());
    }

    @Test
    void addExperience_shouldThrowIfProfileNotFound() {
        // Arrange
        Long profileId = 999L;
        ExperienceDTO dto = new ExperienceDTO();
        when(profileRepo.findById(profileId)).thenReturn(Optional.empty());

        // Act + Assert
        assertThrows(ResourceNotFoundException.class, () -> {
            experienceService.addExperience(profileId, dto);
        });

        verify(experienceRepo, never()).save(any());
    }
}
