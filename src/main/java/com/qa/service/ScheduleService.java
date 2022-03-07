package com.qa.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.entity.Schedule;
import com.qa.repo.ScheduleRepo;

@Service
public class ScheduleService {

	private ScheduleRepo repo;

	@Autowired
	public ScheduleService(ScheduleRepo repo) {
		this.repo = repo;
	}

	/* create
	 * this method creates a new flight entry in the database
	 */
	public Schedule createFlight(Schedule flight) {
		return this.repo.save(flight);
	}

	
	/* get all
	 * this method returns all flight in the database
	 */
	public List<Schedule> getAllFlights() {
		return this.repo.findAll();
	}

	
	/* get by flightNum
	 * this method return a specific flight when searched by flight
	 * number if said flight does not exist, an exception is thrown
	 */
	public Schedule getByflightNum(Integer id) {
		Optional<Schedule> opFlightNum = this.repo.findById(id);
		if (opFlightNum.isPresent()) {
			return opFlightNum.get();
		} else {
			throw new EntityNotFoundException("Can't find this flight");
		}
	}

	
	/* update ALL fields
	 * this method updates ALL fields in a flight entry, if no data is given
	 * the method will populate empty fields with null or default values depending on data type
	 */	
	public Schedule updateSchedule(Integer id, Schedule schedule) {
		Schedule foundFlight = this.getByflightNum(id);
		foundFlight.setOrigin(schedule.getOrigin());
		foundFlight.setDestination(schedule.getDestination());
		foundFlight.setDeparture(schedule.getDeparture());
		return this.repo.save(foundFlight);
	}

	
	/* update departure time (for delayed flights)
	 * this method will update only departure time, all remaining fields in an entry will remain as
	 * they were before.
	 */
	public Schedule updateDelayedFlight(Integer id, Schedule schedule) {
		Schedule foundFlight = this.getByflightNum(id);
		foundFlight.setDeparture(schedule.getDeparture());
		return this.repo.save(foundFlight);
	}

	
	/* delete
	 * this method deletes a flight entry from the database, cannot be undone.
	 */	
	public boolean deleteFlight(Integer id) {
		this.repo.deleteById(id);
		return !this.repo.existsById(id);
	}

}
