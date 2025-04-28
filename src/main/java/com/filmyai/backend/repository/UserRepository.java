package com.filmyai.backend.repository;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.filmyai.backend.model.MyAppUser;

@Repository
public interface UserRepository extends JpaRepository<MyAppUser, Long> {
	Optional<MyAppUser> findByEmail(String email); // To check if a user already exists
	
	Optional<MyAppUser> findByResetToken(String resetToken);

}
