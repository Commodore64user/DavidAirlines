package com.qa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qa.entity.Passengers;
import com.qa.service.PassengersService;

@RestController
public class PassengersController {
	
	private PassengersService service;
	
	@Autowired
	public PassengersController(PassengersService service) {
		this.service = service;
	}
	
	@PostMapping("/createReservation")
	public ResponseEntity<Passengers> createReservation(@RequestBody Passengers passenger) {
		return new ResponseEntity<Passengers>(this.service.createReservation(passenger), HttpStatus.CREATED);
	}
	
	
	@GetMapping("/getPassengers")
	public ResponseEntity<List<Passengers>> getPassengers() {
		return new ResponseEntity<List<Passengers>>(this.service.getPassengers(), HttpStatus.OK);
	}
	
	
	@GetMapping("/getById/{id}")
	public ResponseEntity<Passengers> getById(@PathVariable Integer id) {
		return new ResponseEntity<Passengers>(this.service.getById(id), HttpStatus.OK);
	}
	
	
	@PutMapping("/updatePassenger/{id}")
	public ResponseEntity<Passengers> updatePassenger(@PathVariable Integer id, @RequestBody Passengers passenger) {
		return new ResponseEntity<Passengers>(this.service.updatePassenger(id, passenger), HttpStatus.ACCEPTED);
	}
	
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Boolean> deletePassenger(@PathVariable Integer id) {
		boolean hasDeleted = this.service.deletePassenger(id);
		if (hasDeleted) {
			return new ResponseEntity<Boolean>(this.service.deletePassenger(id), HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<Boolean>(hasDeleted, HttpStatus.EXPECTATION_FAILED);
		}
	}

}
