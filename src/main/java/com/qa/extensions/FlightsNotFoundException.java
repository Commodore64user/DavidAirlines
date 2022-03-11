package com.qa.extensions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Can't create reservation as no flights are available")
public class FlightsNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6472674654379131405L;

	public FlightsNotFoundException(String msg) {
		super(msg);
	}

}
