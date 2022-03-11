package com.qa.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
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
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.entity.Schedule;

@SpringBootTest
@ActiveProfiles("test")
@Sql(scripts = { "classpath:schedule-schema.sql",
		"classpath:schedule-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
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
		
		Schedule savedFlight = new Schedule(4, "London", "Belfast", LocalTime.of(07, 15));
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
		//Given
		List<Schedule> schedule = new ArrayList<>();
		schedule.add(new Schedule(1, "New Sebastian", "Brakusmouth", LocalTime.of(14, 41)));
		schedule.add(new Schedule(2, "Port Lucienne", "Caitlynview", LocalTime.of(20, 46)));
		schedule.add(new Schedule(3, "Rocklin", "Rogahnville", LocalTime.of(10, 25)));
		
		String savedScheduleJSON = this.mapper.writeValueAsString(schedule);
		
		//When
		RequestBuilder request = get("/getAllFlights");
		
		ResultMatcher responseStatus = status().isOk();
		ResultMatcher responseContent = content().json(savedScheduleJSON);
		
		//Then
		this.mvc.perform(request).andExpect(responseStatus).andExpect(responseContent);
	}
	
	@Test
	void getByflightNumTest() throws Exception{
		Schedule savedFlight = new Schedule(2, "Port Lucienne", "Caitlynview", LocalTime.of(20, 46));
		String savedFlightJSON = this.mapper.writeValueAsString(savedFlight);
		
		RequestBuilder request = get("/getByflightNum/2");
		
		ResultMatcher responseStatus = status().isOk();
		ResultMatcher responseContent = content().json(savedFlightJSON);
		
		this.mvc.perform(request).andExpect(responseStatus).andExpect(responseContent);
	}
	
	
	@Test
	void updateScheduleTest() throws Exception {
		Schedule updatedFlight = new Schedule(2, "Port Lucienne", "Rogahnville", LocalTime.of(20, 46));
		String updatedFlightJSON = this.mapper.writeValueAsString(updatedFlight);
		
		RequestBuilder request = put("/updateSchedule/2").contentType(MediaType.APPLICATION_JSON)
				.content(updatedFlightJSON);
		
		ResultMatcher responseStatus = status().isAccepted();
		ResultMatcher responseContent = content().json(updatedFlightJSON);
		
		this.mvc.perform(request).andExpect(responseStatus).andExpect(responseContent);
	}
	
	
	@Test
	void updateDelayedFlightTest() throws Exception {
		Schedule updatedFlight = new Schedule(2, "Port Lucienne", "Caitlynview", LocalTime.of(21, 10));
		String updatedFlightJSON = this.mapper.writeValueAsString(updatedFlight);
		
		RequestBuilder request = patch("/updateDelayedFlight/2").contentType(MediaType.APPLICATION_JSON)
				.content(updatedFlightJSON);
		
		ResultMatcher responseStatus = status().isAccepted();
		ResultMatcher responseContent = content().json(updatedFlightJSON);
		
		this.mvc.perform(request).andExpect(responseStatus).andExpect(responseContent);
	}
	
	
	@Test 
	void deleteByFlightNumTest() throws Exception {
		this.mvc.perform(delete("/delete/1")).andExpect(status().isAccepted());
	}
	
	

}
