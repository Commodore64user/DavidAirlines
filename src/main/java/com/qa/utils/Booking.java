package com.qa.utils;

import java.util.Random;

public class Booking {
	
	Random r = new Random();
	private int low = 1;
	private int result;	
	public int genBooking(int limit) {
		
		
		if (limit > 1) {
			result = r.nextInt((limit+1)-low)+1;
		} else if (limit == 1) {
			result = 1;
		}
		return result;
	}
	
	

}
