package com.qa.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.entity.Schedule;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class ScheduleControllerIntegrationTest {

	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper mapper;
	
	@Test
	void createFlightTest() throws Exception {
		//Given
		Schedule newFlight = new Schedule("London", "Belfast", LocalTime.of(07, 15));
		String newFlightJSON = this.mapper.writeValueAsString(newFlight);
		
		Schedule savedFlight = new Schedule(1, "London", "Belfast", LocalTime.of(07, 15));
		String savedFlightJSON = this.mapper.writeValueAsString(savedFlight);
		//When
		RequestBuilder request = post("/createFlight").contentType(MediaType.APPLICATION_JSON).content(newFlightJSON);
		
		ResultMatcher responseStatus = status().isCreated();
		ResultMatcher responseContent = content().json(savedFlightJSON);
		
		//Then
		this.mvc.perform(request).andExpect(responseStatus).andExpect(responseContent);
	}
	
	@Test
	void getAllFlights() throws Exception {
		List<Schedule> schedule = new ArrayList<>();
		schedule.add(new Schedule("London", "Belfast", LocalTime.of(10, 50)));
		schedule.add(new Schedule("Aberdeen", "Manchester", LocalTime.of(23, 00)));
		schedule.add(new Schedule("Cardiff", "Dublin", LocalTime.of(12, 30)));
		
		String savedScheduleJSON = this.mapper.writeValueAsString(schedule);
		
		RequestBuilder request = get("/getAllFlights");
		
		ResultMatcher responseStatus = status().isOk();
		ResultMatcher responseContent = content().json(savedScheduleJSON);
		
		this.mvc.perform(request).andExpect(responseStatus).andExpect(responseContent);
	}
	
	
	
	
	
	
	

}
