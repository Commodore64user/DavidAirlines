package com.qa.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



@Entity
public class Passengers {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	
	private String reservation; // = new RandomString(8);
	private String firstName;
	private String lastName;
	private String passport;
	private String email;
	private boolean premium;
	
	public Passengers() {}

	public Passengers(int id, String reservation, String firstName, String lastName, String passport, String email,
			boolean premium) {
		super();
		this.id = id;
		this.reservation = reservation;
		this.firstName = firstName;
		this.lastName = lastName;
		this.passport = passport;
		this.email = email;
		this.premium = premium;
	}

	public Passengers(String reservation, String firstName, String lastName, String passport, String email, boolean premium) {
		super();
		this.reservation = reservation;
		this.firstName = firstName;
		this.lastName = lastName;
		this.passport = passport;
		this.email = email;
		this.premium = premium;
	}
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getReservation() {
		return reservation;
	}

	public void setReservation(String reservation) {
		this.reservation = reservation;
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

	public String getPassport() {
		return passport;
	}

	public void setPassport(String passport) {
		this.passport = passport;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isPremium() {
		return premium;
	}

	public void setPremium(boolean premium) {
		this.premium = premium;
	}

	@Override
	public String toString() {
		return "Passangers [id=" + id + ", reservation=" + reservation + ", firstName=" + firstName + ", lastName="
				+ lastName + ", passport=" + passport + ", email=" + email + ", premium=" + premium + "]";
	}


	
	
	

}
