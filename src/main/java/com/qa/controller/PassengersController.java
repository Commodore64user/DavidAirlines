package com.qa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.qa.service.PassengersService;

@RestController
public class PassengersController {
	
	private PassengersService service;
	
	@Autowired
	public PassengersController(PassengersService service) {
		this.service = service;
	}

}
