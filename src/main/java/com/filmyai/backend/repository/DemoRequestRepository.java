package com.filmyai.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.filmyai.backend.model.DemoRequest;

public interface DemoRequestRepository extends JpaRepository<DemoRequest, Long> {
	

}
