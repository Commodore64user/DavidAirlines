package com.qa.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.entity.Passengers;


@Repository
public interface PassengersRepo extends JpaRepository<Passengers, Integer>{
	
	Optional<Passengers> findPassengersByReservation(String reservation);

}
