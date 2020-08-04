package com.appsdeveloperblog.photoapp.api.users.ui.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreateUserRequestModel {

	@NotNull(message = "First Name cannot be null")
	@Size(min = 2 , message = "First Name must be more that 2 characters length")
	private String firstName;
	@NotNull(message = "Last Name cannot be null")
	@Size(min = 2 , message = "Last Name must be more that 2 characters length")
	private String lastName;
	@NotNull(message="message cannot be null")
	@Email
	private String email;
	@NotNull(message = "Password cannot be null")
	@Size(min=8 , max=16 , message = "password must be between 8 and 16 characters")
	private String password;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
