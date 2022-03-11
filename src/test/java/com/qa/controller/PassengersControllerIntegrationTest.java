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
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.entity.Passengers;

@SpringBootTest
@ActiveProfiles("test")
@Sql(scripts = { "classpath:passengers-schema.sql", "classpath:passengers-data.sql", "classpath:schedule-schema.sql",
		"classpath:schedule-data2.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@AutoConfigureMockMvc
public class PassengersControllerIntegrationTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper mapper;

	@Test
	void createReservationTest() throws Exception {
		// Given
		Passengers newReservation = new Passengers("JNBK675U", 1, "Ben", "Dover", "GB244928", "ben@dover.co.uk", false);
		String newReservationJSON = this.mapper.writeValueAsString(newReservation);

		// When
		RequestBuilder request = post("/createReservation").contentType(MediaType.APPLICATION_JSON)
				.content(newReservationJSON);

		ResultActions var = this.mvc.perform(request);

		ResultMatcher responseStatus = status().isCreated();
		ResultMatcher responseContent = content().json(var.andReturn().getResponse().getContentAsString());

		// Then

		System.out.println(var.andReturn().getResponse().getContentAsString());
		var.andExpect(responseStatus).andExpect(responseContent);
	}

	@Test
	void getPassengersTest() throws Exception {
		// Given
		List<Passengers> passengers = new ArrayList<>();
		passengers
				.add(new Passengers(1, "LT4LG99Y", 3, "Chaz", "Wuckert", "GB986794", "Chaz_Kulas@hotmail.com", false));
		passengers
				.add(new Passengers(2, "H0JBUHPN", 2, "Gerardo", "Aufderhar", "GB623977", "Gerardo10@gmail.com", true));
		passengers
				.add(new Passengers(3, "N5NSQ4XG", 3, "Marie", "Swift", "GB623977", "Marie.Swift23@yahoo.com", false));

		String savedPassengersJSON = this.mapper.writeValueAsString(passengers);

		// When
		RequestBuilder request = get("/getPassengers");

		ResultMatcher responseStatus = status().isOk();
		ResultMatcher responseContent = content().json(savedPassengersJSON);

		// Then
		this.mvc.perform(request).andExpect(responseStatus).andExpect(responseContent);
	}

	@Test
	void getByIdTest() throws Exception {
		// Given
		Passengers savedReservation = new Passengers(2, "H0JBUHPN", 2, "Gerardo", "Aufderhar", "GB623977",
				"Gerardo10@gmail.com", true);
		String savedReservationJSON = this.mapper.writeValueAsString(savedReservation);
		// When
		RequestBuilder request = get("/getById/2");

		ResultMatcher responseStatus = status().isOk();
		ResultMatcher responseContent = content().json(savedReservationJSON);
		// Then
		this.mvc.perform(request).andExpect(responseStatus).andExpect(responseContent);
	}

	@Test
	void getByReservationTest() throws Exception {
		// Given
		Passengers savedReservation = new Passengers(2, "H0JBUHPN", 2, "Gerardo", "Aufderhar", "GB623977",
				"Gerardo10@gmail.com", true);
		String savedReservationJSON = this.mapper.writeValueAsString(savedReservation);
		// When
		RequestBuilder request = get("/getByReservation/H0JBUHPN");

		ResultMatcher responseStatus = status().isOk();
		ResultMatcher responseContent = content().json(savedReservationJSON);
		// Then
		this.mvc.perform(request).andExpect(responseStatus).andExpect(responseContent);
	}

	@Test
	void getPassengersByFlightTest() throws Exception {
		// Given
		List<Passengers> passengers = new ArrayList<>();
		passengers
				.add(new Passengers(1, "LT4LG99Y", 3, "Chaz", "Wuckert", "GB986794", "Chaz_Kulas@hotmail.com", false));
		passengers
				.add(new Passengers(3, "N5NSQ4XG", 3, "Marie", "Swift", "GB623977", "Marie.Swift23@yahoo.com", false));

		String savedPassengersJSON = this.mapper.writeValueAsString(passengers);

		// When
		RequestBuilder request = get("/getPassengersByFlight/3");

		ResultMatcher responseStatus = status().isOk();
		ResultMatcher responseContent = content().json(savedPassengersJSON);

		// Then
		this.mvc.perform(request).andExpect(responseStatus).andExpect(responseContent);
	}

	@Test
	void updatePassenger() throws Exception {
		Passengers updatedReservation = new Passengers(3, "N5NSQ4XG", 3, "Marie", "Swift", "GB623977",
				"Marie.Swift23@yahoo.com", true);
		String updatedReservationJSON = this.mapper.writeValueAsString(updatedReservation);
		// When
		RequestBuilder request = put("/updatePassenger/3").contentType(MediaType.APPLICATION_JSON)
				.content(updatedReservationJSON);

		ResultMatcher responseStatus = status().isAccepted();
		ResultMatcher responseContent = content().json(updatedReservationJSON);
		// Then
		this.mvc.perform(request).andExpect(responseStatus).andExpect(responseContent);
	}

	@Test
	void deletePassengerTest() throws Exception {
		ResultActions cond = this.mvc.perform(delete("/deletePassenger/2"));
		cond.andExpect(status().isAccepted());
	}

}
