package com.filmyai.login.Model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyAppUserRepository extends JpaRepository<MyAppUser, Long> {
    
    // Repository interface for MyAppUser entity with a custom method to find a user by email

    MyAppUser findByEmail(String email);
}
