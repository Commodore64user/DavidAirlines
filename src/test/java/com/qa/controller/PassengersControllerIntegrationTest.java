package com.qa.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import com.qa.entity.Passengers;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class PassengersControllerIntegrationTest {
	
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper mapper;
	
	@Test
	void createReservationTest() throws Exception {
		//Given
		Passengers newReservation = new Passengers("JNBK675U", "Ben", "Dover", "GB244928", "ben@dover.co.uk", false);
		String newReservationJSON = this.mapper.writeValueAsString(newReservation);
		
		Passengers savedReservation = new Passengers(1, "JNBK675U", "Ben", "Dover", "GB244928", "ben@dover.co.uk", false);
		String savedReservationJSON = this.mapper.writeValueAsString(savedReservation);
		//When
		RequestBuilder request = post("/createFlight").contentType(MediaType.APPLICATION_JSON).content(newReservationJSON);
		
		ResultMatcher responseStatus = status().isCreated();
		ResultMatcher responseContent = content().json(savedReservationJSON);
		
		//Then
		this.mvc.perform(request).andExpect(responseStatus).andExpect(responseContent);
	}

	
	@Test
	void getPassengersTest() throws Exception {
		List<Passengers> passengers = new ArrayList<>();
		passengers.add(new Passengers("BTZ3ZNZ8", "Aaron", "Smith", "GB439275", "aaron@smith.co.uk", false));
		passengers.add(new Passengers("JNBK675U", "Will", "Smith", "GB244928", "will@smith.co.uk", false));
		passengers.add(new Passengers("YZESP14C", "Ruby", "Embley", "GB444588", "ruby@thebest.co.uk", true));
		
		String savedPassengersJSON = this.mapper.writeValueAsString(passengers);
		
		RequestBuilder request = get("/getPassengers");
		
		ResultMatcher responseStatus = status().isOk();
		ResultMatcher responseContent = content().json(savedPassengersJSON);
		
		this.mvc.perform(request).andExpect(responseStatus).andExpect(responseContent);
		
	}
	
	
	
	
	
	
	
	
}
