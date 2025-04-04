package com.filmyai.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.filmyai.backend.dto.SignupRequestDto;
import com.filmyai.backend.model.MyAppUser;
import com.filmyai.backend.repository.UserRepository;

@Service
public class SignupService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
    public SignupService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
	
	
	public MyAppUser registerUser(SignupRequestDto signupRequest) {
		if (userRepository.findByEmail(signupRequest.getEmail()).isPresent()) {
			throw new RuntimeException("Email already exists!");
		}

		MyAppUser user = new MyAppUser();

		user.setEmail(signupRequest.getEmail());
		user.setPassword(passwordEncoder.encode(signupRequest.getPassword())); // hashed the password
		
		//String accountTypeString = signupRequest.getAccountType();
		//if(signupRequest.getAccountType() == AccountType.User) {
		if ("user".equalsIgnoreCase(signupRequest.getAccountType())) {
			user.setAccountType(MyAppUser.AccountType.User);
			user.setFirstName(signupRequest.getFirstName());
			user.setLastName(signupRequest.getLastName());
			
			System.out.println("Setting account type......"+signupRequest.getFirstName());
		} else {
			//user.setAccountType(signupRequest.getAccountType());
			user.setAccountType(MyAppUser.AccountType.Business);
			user.setBusinessName(signupRequest.getBusinessName());
			
		}
		
		return userRepository.save(user);
	}

}
