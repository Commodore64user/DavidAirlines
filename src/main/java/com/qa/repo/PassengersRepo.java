package com.qa.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qa.entity.Passengers;


@Repository
public interface PassengersRepo extends JpaRepository<Passengers, Integer>{
	
	Optional<Passengers> findPassengersByReservation(String reservation);
	
	@Query(value = "SELECT COUNT(*) FROM schedule;", nativeQuery = true)
	int totalFlights();
	
	List<Passengers> findPassengersByBookedFlight(int flight);

}
