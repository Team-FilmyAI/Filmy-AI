package com.filmyai.login.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActorService {

    @Autowired
    private ActorRepository actorRepository;

    // Method to save Actor
    public void saveActor(Actor actor) {
        actorRepository.save(actor);
    }

    
}