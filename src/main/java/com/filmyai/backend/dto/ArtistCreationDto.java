package com.filmyai.backend.dto;

import lombok.Data;

@Data
public class ArtistCreationDto {
		
		private String firstName;
		private String lastName;
		private String email;
		private String contactNumber;
		private String occupation;
		private String location;
		private String profilePhotoPath;
		private String portfolio;
		
		
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
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getContactNumber() {
			return contactNumber;
		}
		public void setContactNumber(String contactNumber) {
			this.contactNumber = contactNumber;
		}
		public String getOccupation() {
			return occupation;
		}
		public void setOccupation(String occupation) {
			this.occupation = occupation;
		}
		public String getLocation() {
			return location;
		}
		public void setLocation(String location) {
			this.location = location;
		}
		public String getProfilePhotoPath() {
			return profilePhotoPath;
		}
		public void setProfilePhotoPath(String profilePhotoPath) {
			this.profilePhotoPath = profilePhotoPath;
		}
		public String getPortfolio() {
			return portfolio;
		}
		public void setPortfolio(String portfolio) {
			this.portfolio = portfolio;
		}
		
		
		
}
