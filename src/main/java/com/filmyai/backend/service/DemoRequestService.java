package com.filmyai.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.filmyai.backend.model.DemoRequest;
import com.filmyai.backend.repository.DemoRequestRepository;

@Service
public class DemoRequestService {

	@Autowired
	private DemoRequestRepository demoRequestRepository;
	// for request submission
	public DemoRequest saveDemoRequest(DemoRequest demoRequest) {
		return demoRequestRepository.save(demoRequest);
		
	}

}
