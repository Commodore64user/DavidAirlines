package com.qa.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.entity.Schedule;

@Repository
public interface ScheduleRepo extends JpaRepository<Schedule, Integer>{

}
