package com.qa.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.entity.Schedule;
import com.qa.repo.ScheduleRepo;

@SpringBootTest
public class ScheduleServiceUnitTest {

	@Autowired
	private ScheduleService service;

	@MockBean
	private ScheduleRepo repo;

	@Test
	void createFlightTest() {
		// Given
		Schedule flightToSave = new Schedule("London", "Glasgow", LocalTime.of(9, 10));
		Schedule flightSaved = new Schedule(1, "London", "Glasgow", LocalTime.of(9, 10));
		// When
		Mockito.when(this.repo.save(flightToSave)).thenReturn(flightSaved);
		// Then
		assertThat(this.service.createFlight(flightToSave)).isEqualTo(flightSaved);
		// Verify
		Mockito.verify(this.repo, Mockito.times(1)).save(Mockito.any(Schedule.class));
	}

	@Test
	void getAllTest() {
		// Given
		List<Schedule> schedule = new ArrayList<>();
		schedule.add(new Schedule(1, "London", "Glasgow", LocalTime.of(9, 10)));
		schedule.add(new Schedule(2, "Aberdeen", "Belfast", LocalTime.of(11, 55)));
		schedule.add(new Schedule(3, "Cardiff", "Manchester", LocalTime.of(15, 00)));
		// When
		Mockito.when(this.repo.findAll()).thenReturn(schedule);
		// Then
		assertThat(this.service.getAllFlights()).isEqualTo(schedule);
		// Verify
		Mockito.verify(this.repo, Mockito.times(1)).findAll();
	}

	@Test
	void getByflightNumTest() {
		// Given
		int flightNum = 1;
		Schedule foundFlight = new Schedule(1, "London", "Glasgow", LocalTime.of(9, 10));
		// When
		Mockito.when(this.repo.findById(flightNum)).thenReturn(Optional.of(foundFlight));
		// Then
		assertThat(this.service.getByflightNum(flightNum)).isEqualTo(foundFlight);
		// Verify
		Mockito.verify(this.repo, Mockito.times(1)).findById(Mockito.anyInt());
	}

	@Test
	void updateScheduleTest() {
		// Given
		int flightNum = 1;
		Schedule flightSaved = new Schedule(1, "London", "Glasgow", LocalTime.of(9, 10));
		Schedule flightUpdate = new Schedule("London", "Belfast", LocalTime.of(10, 15));
		Schedule actualFlightUpdate = new Schedule(1, "London", "Belfast", LocalTime.of(10, 15));
		// When
		Mockito.when(this.repo.findById(flightNum)).thenReturn(Optional.of(flightSaved));
		Mockito.when(this.repo.save(actualFlightUpdate)).thenReturn(actualFlightUpdate);
		// Then
		assertThat(this.service.updateSchedule(flightNum, flightUpdate)).isEqualTo(actualFlightUpdate);
		// Verify
		Mockito.verify(this.repo, Mockito.times(1)).findById(Mockito.anyInt());
		Mockito.verify(this.repo, Mockito.times(1)).save(Mockito.any(Schedule.class));
	}

	@Test
	void updateDelayedFlightTest() {
		// Given
		int flightNum = 1;
		Schedule flightSaved = new Schedule(1, "London", "Glasgow", LocalTime.of(9, 10));
		Schedule flightUpdate = new Schedule("London", "Glasgow", LocalTime.of(10, 15));
		Schedule actualFlightUpdate = new Schedule(1, "London", "Glasgow", LocalTime.of(10, 15));
		// When
		Mockito.when(this.repo.findById(flightNum)).thenReturn(Optional.of(flightSaved));
		Mockito.when(this.repo.save(actualFlightUpdate)).thenReturn(actualFlightUpdate);
		// Then
		assertThat(this.service.updateSchedule(flightNum, flightUpdate)).isEqualTo(actualFlightUpdate);
		// Verify
		Mockito.verify(this.repo, Mockito.times(1)).findById(Mockito.anyInt());
		Mockito.verify(this.repo, Mockito.times(1)).save(Mockito.any(Schedule.class));
	}
	
	@Test
	void deleteTest() {
		// Given
		int flightNum = 1;
		// When
		Mockito.when(this.repo.existsById(flightNum)).thenReturn(false);
		// Then
		assertThat(this.service.deleteFlight(flightNum)).isTrue();
		//Verify
		Mockito.verify(this.repo, Mockito.times(1)).deleteById(Mockito.anyInt());
		Mockito.verify(this.repo, Mockito.times(1)).existsById(Mockito.anyInt());
	}

}
