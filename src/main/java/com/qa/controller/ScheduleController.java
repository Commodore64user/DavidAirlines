package com.qa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qa.entity.Schedule;
import com.qa.service.ScheduleService;

@RestController
public class ScheduleController {
	
	private ScheduleService service;
	
	@Autowired
	public ScheduleController(ScheduleService service) {
		this.service = service;
	}
	
	
	@PostMapping("/createFlight")
	public ResponseEntity<Schedule> createFlight(@RequestBody Schedule schedule) {
		return new ResponseEntity<Schedule>(this.service.createFlight(schedule), HttpStatus.CREATED);
	}
	
	
	@GetMapping("/getAllFlights")
	public ResponseEntity<List<Schedule>> getAll() {
		return new ResponseEntity<List<Schedule>>(this.service.getAllFlights(), HttpStatus.OK);
	}
	
	
	@GetMapping("/getByflightNum/{id}")
	public ResponseEntity<Schedule> getByflightNum(@PathVariable Integer id) {
		return new ResponseEntity<Schedule>(this.service.getByflightNum(id), HttpStatus.OK);
	}
	
	
	@PutMapping("/updateSchedule/{id}")
	public ResponseEntity<Schedule> updateSchedule(@PathVariable Integer id, @RequestBody Schedule schedule) {
		return new ResponseEntity<Schedule>(this.service.updateSchedule(id, schedule), HttpStatus.ACCEPTED);
	}
	
	
	@PatchMapping("/updateDelayedFlight/{id}")
	public ResponseEntity<Schedule> updateDelayedFlight(@PathVariable Integer id, @RequestBody Schedule schedule) {
		return new ResponseEntity<Schedule>(this.service.updateDelayedFlight(id, schedule), HttpStatus.ACCEPTED);
	}
	
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Boolean> deleteByFlightNum(@PathVariable Integer id) {
		boolean hasDeleted = this.service.deleteFlight(id);
		if (hasDeleted) {
			return new ResponseEntity<Boolean>(this.service.deleteFlight(id), HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<Boolean>(hasDeleted, HttpStatus.EXPECTATION_FAILED);
		}
	}

}
