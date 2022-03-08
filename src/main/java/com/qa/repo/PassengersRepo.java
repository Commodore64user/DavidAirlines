package com.qa.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.entity.Passengers;


@Repository
public interface PassengersRepo extends JpaRepository<Passengers, Integer>{

}
