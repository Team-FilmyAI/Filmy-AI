package com.filmyai.backend.dto;

import lombok.Data;

@Data
public class SignupRequestDto {
	
	private String firstName; // Only used for individuals
	private String lastName; // Only used for individuals
	private String businessName; // Only used for businesses
	private String email;
	private String password;
	
	private String accountType; // "individual" or "business"
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getBusinessName() {
		return businessName;
	}
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
//	public AccountType getAccountType() {
//		return accountType;
//	}
//	public void setAccountType(AccountType accountType) {
//		this.accountType = accountType;
//	}


}
