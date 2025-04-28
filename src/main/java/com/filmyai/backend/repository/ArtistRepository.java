package com.filmyai.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.filmyai.backend.model.Artist;
import com.filmyai.backend.model.MyAppUser;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long> {

	Optional<Artist> findByEmail(String email); // To check if a profile already exists

//	Optional<Artist> findByMyAppUser(MyAppUser myAppUser);

}
