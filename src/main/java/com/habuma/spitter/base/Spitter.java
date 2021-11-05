package com.habuma.spitter.base;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;



@Entity
@Table(name="spitter")
@SequenceGenerator(name="ID_GENERATOR",sequenceName = "spitter_pk_sequense", allocationSize = 1)
public class Spitter {

	
	@Id
	@Column(name="id",updatable = false,nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_GENERATOR")
	private Long id;
	
	
	@Column(name="username",nullable = false)
	@NotEmpty(message = "Username shoude not be empty!")
	@Size(min=1, max=20, message = "Username shoude be between 2 and 20 charactes!")
	@Pattern(regexp="^[a-zA-Z0-9]+$",message="Username mustbealphanumericwithnospaces")
	private String userName;
	
	@Column(name="password",nullable = false)
	@Size(min=6,max=20, message = "The password must be at least 6 characters long")
	private String password;
	
	@Column(name="first_name",nullable = false)
	@NotEmpty(message = "First name shoude not be empty!")
	@Size(min=1, max=20, message = "First name shoude be between 2 and 20 charactes!")
	private String firstName;
	
	@Column(name="last_name",nullable = false)
	@NotEmpty(message = "Last name shoude not be empty!")
	@Size(min=1, max=20, message = "Last name shoude be between 2 and 20 charactes!")
	private String lastName;
	
	@Column(name="email")
	@Email(message = "Email shoude be valid")
	private String email;
	
	//@Transient  //Don't save in DB
	//private MultipartFile image;
	
	public Spitter () {};
	
	public Spitter(String userName,String password, String firstName, String lastName, String email) {
	
		//this.id=id;
		this.userName=userName;
		this.password=password;
		this.firstName=firstName;
		this.lastName=lastName;
		this.email=email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

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

	/*public MultipartFile getImage() {
		return image;
	}

	public void setImage(MultipartFile image) {
		this.image = image;
	}*/
	
	
	
}
