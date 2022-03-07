package com.qa.entity;

import java.sql.Time;

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
	private Time departure;
	
	public Schedule(String origin, String destination, Time departure) {
		super();
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
	public Time getDeparture() {
		return departure;
	}
	public void setDeparture(Time departure) {
		this.departure = departure;
	}
	@Override
	public String toString() {
		return "Schedule [flingtNum=" + flingtNum + ", origin=" + origin + ", destination=" + destination
				+ ", departure=" + departure + "]";
	}
	  
	
}
