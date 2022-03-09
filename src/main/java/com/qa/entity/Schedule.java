package com.qa.entity;

import java.time.LocalTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Schedule {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int flingtNum;
	
	
	private String origin;
	private String destination;
	private LocalTime departure;
	
	public Schedule() {}
	
	public Schedule(String origin, String destination, LocalTime departure) {
		super();
		this.origin = origin;
		this.destination = destination;
		this.departure = departure;
	}
	
	public Schedule(int flightNum, String origin, String destination, LocalTime departure) {
		super();
		this.flingtNum = flightNum;
		this.origin = origin;
		this.destination = destination;
		this.departure = departure;
	}
	
	
	public int getFlingtNum() {
		return flingtNum;
	}
	public void setFlingtNum(int flingtNum) {
		this.flingtNum = flingtNum;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public LocalTime getDeparture() {
		return departure;
	}
	public void setDeparture(LocalTime departure) {
		this.departure = departure;
		
	}
	@Override
	public String toString() {
		return "Schedule [flingtNum=" + flingtNum + ", origin=" + origin + ", destination=" + destination
				+ ", departure=" + departure + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((departure == null) ? 0 : departure.hashCode());
		result = prime * result + ((destination == null) ? 0 : destination.hashCode());
		result = prime * result + flingtNum;
		result = prime * result + ((origin == null) ? 0 : origin.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Schedule other = (Schedule) obj;
		if (departure == null) {
			if (other.departure != null)
				return false;
		} else if (!departure.equals(other.departure))
			return false;
		if (destination == null) {
			if (other.destination != null)
				return false;
		} else if (!destination.equals(other.destination))
			return false;
		if (flingtNum != other.flingtNum)
			return false;
		if (origin == null) {
			if (other.origin != null)
				return false;
		} else if (!origin.equals(other.origin))
			return false;
		return true;
	}


	  
	
}
