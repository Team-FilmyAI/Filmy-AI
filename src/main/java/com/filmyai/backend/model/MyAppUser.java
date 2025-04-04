package com.filmyai.backend.model;


import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PreUpdate;

@Entity
@Table(name = "users")
public class MyAppUser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long user_id;
	private String firstName; // Only used for individuals
	private String lastName; // Only used for individuals
	private String businessName; // Only used for businesses
	private String email;
	private String password;
	
	@Enumerated(EnumType.STRING)
	//@Column(name = "account_type")
	private AccountType accountType; // "individual" or "business"

    @Column(name = "created_at", updatable = false)
    private LocalDateTime created_at = LocalDateTime.now().withNano(0);;

    @Column(name = "updated_at")
    private LocalDateTime updated_at = LocalDateTime.now().withNano(0);;

    @PreUpdate
    public void setUpdatedAt() {
        this.updated_at = LocalDateTime.now();
    }
	
    // New fields included for the forgot password
    @Column(name = "reset_token", nullable = true)
    private String resetToken;

    @Column(name = "reset_token_expiration", nullable = true)
    private LocalDateTime resetTokenExpiration;
    
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
	public AccountType getUserType() {
		return accountType;
	}
	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}
	public LocalDateTime getCreated_at() {
		return created_at;
	}
	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}
	public LocalDateTime getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(LocalDateTime updated_at) {
		this.updated_at = updated_at;
	}
	
	public enum AccountType {
        User, Business
    }

    // Getters and setters for the forgot password
    public String getResetToken() {
        return resetToken;
    }

    public void setResetToken(String resetToken) {
        this.resetToken = resetToken;
    }

    public LocalDateTime getResetTokenExpiration() {
        return resetTokenExpiration;
    }

    public void setResetTokenExpiration(LocalDateTime resetTokenExpiration) {
        this.resetTokenExpiration = resetTokenExpiration;
    }
}
