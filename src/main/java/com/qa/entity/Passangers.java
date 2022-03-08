package com.qa.entity;

import java.util.concurrent.ThreadLocalRandom;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.qa.utils.RandomString;

@Entity
public class Passangers {
	
	@Id
	RandomString reservation = new RandomString(8, ThreadLocalRandom.current());

	private String firstName;
	private String lastName;
	private String passport;
	private String email;
	private boolean premium;
	
	public Passangers() {}

	public Passangers(RandomString reservation, String firstName, String lastName, String passport, String email,
			boolean premium) {
		super();
		this.reservation = reservation;
		this.firstName = firstName;
		this.lastName = lastName;
		this.passport = passport;
		this.email = email;
		this.premium = premium;
	}

	public Passangers(String firstName, String lastName, String passport, String email, boolean premium) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.passport = passport;
		this.email = email;
		this.premium = premium;
	}

	public RandomString getReservation() {
		return reservation;
	}

	public void setReservation(RandomString reservation) {
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
		return "Passangers [reservation=" + reservation + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", passport=" + passport + ", email=" + email + ", premium=" + premium + "]";
	}
	
	
	

}
