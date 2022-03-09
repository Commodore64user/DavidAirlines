package com.qa.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.entity.Passengers;
import com.qa.repo.PassengersRepo;
import com.qa.utils.RandomString;

@Service
public class PassengersService {
	
	private PassengersRepo repo;

	@Autowired
	public PassengersService(PassengersRepo repo) {
		this.repo = repo;
	}
	
	/* create
	 * this method creates a new [reservation] entry in the database
	 * and  a random string for variable reservation 
	 */
	public Passengers createReservation(Passengers passenger) {
		RandomString random = new RandomString(8);
		passenger.setReservation(random.nextString());
		return this.repo.save(passenger);
	}
	
	
	/* get all
	 * this method returns all [passenger] entries in the database
	 */
	public List<Passengers> getPassengers() {
		return this.repo.findAll();
	}
	
	
	/* get by id
	 * this method returns a specific passenger when searched using flight
	 * number, if said flight does not exist, an exception is thrown
	 */
	public Passengers getById(Integer id) {
		Optional<Passengers> optionalPassenger = this.repo.findById(id);
		if (optionalPassenger.isPresent()) {
			return optionalPassenger.get();
		} else {
			throw new EntityNotFoundException("Can't find passenger");
		}
	}
	
	
	/* get by reservation
	 * this method returns a specific passenger when searched using flight
	 * number, if said flight does not exist, an exception is thrown
	 */
	public Optional<Passengers> getByReservation(String reservation) {
		Optional<Passengers> optionalPassenger = this.repo.findPassengersByReservation(reservation);
		if (optionalPassenger.isPresent()) {
			return optionalPassenger;
		} else {
			throw new EntityNotFoundException("Can't find passenger with this reservation");
		}
	}
	
	
	
	/* update ALL fields
	 * this method updates ALL fields in a passenger entry, if no data is given
	 * the method will populate empty fields with null or default values depending on data type
	 */	
	public Passengers updatePassenger(Integer id, Passengers passenger) {
		Passengers foundPassenger = this.getById(id);
		foundPassenger.setFirstName(passenger.getFirstName());
		foundPassenger.setLastName(passenger.getLastName());
		foundPassenger.setPassport(passenger.getPassport());
		foundPassenger.setEmail(passenger.getEmail());
		foundPassenger.setPremium(passenger.isPremium());
		return this.repo.save(foundPassenger);
	}

	
	/* delete
	 * this method deletes a [passenger] entry from the database, cannot be undone.
	 */	
	public boolean deletePassenger(Integer id) {
		this.repo.deleteById(id);
		return !this.repo.existsById(id);
	}
	

}
