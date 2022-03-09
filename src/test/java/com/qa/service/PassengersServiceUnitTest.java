package com.qa.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.entity.Passengers;
import com.qa.repo.PassengersRepo;

@SpringBootTest
public class PassengersServiceUnitTest {
	
	@Autowired
	private PassengersService service;
	
	@MockBean
	private PassengersRepo repo;
	
	/* TEST FOR CREATE RESERVATION
	 * this test checks whether the method behaves in the expected way
	 * no real entries are created as mock objects are used
	 */
	@Test
	void createReservationTest() {
		// Given
		Passengers passengertToSave = new Passengers("BTZ3ZNZ8", "Aaron", "Smith", "GB439275", "aaron@smith.co.uk", false);
		Passengers passengertSaved = new Passengers(1, "BTZ3ZNZ8", "Aaron", "Smith", "GB439275", "aaron@smith.co.uk", false);
		// When
		Mockito.when(this.repo.save(passengertToSave)).thenReturn(passengertSaved);
		// Then
		assertThat(this.service.createReservation(passengertToSave)).isEqualTo(passengertSaved);
		// Verify
		Mockito.verify(this.repo, Mockito.times(1)).save(Mockito.any(Passengers.class));
	}
	
	
	/* TEST FOR GET ALL PASSENGERS
	 * this test checks whether the method behaves in the expected way
	 * no real entries are created as mock objects are used
	 */
	@Test
	void getPassengersTest() {
		// Given
		List<Passengers> passengers = new ArrayList<>();
		passengers.add(new Passengers("BTZ3ZNZ8", "Aaron", "Smith", "GB439275", "aaron@smith.co.uk", false));
		passengers.add(new Passengers("JNBK675U", "Will", "Smith", "GB244928", "will@smith.co.uk", false));
		passengers.add(new Passengers("YZESP14C", "Ruby", "Embley", "GB444588", "ruby@thebest.co.uk", true));
		// When
		Mockito.when(this.repo.findAll()).thenReturn(passengers);
		// Then
		assertThat(this.service.getPassengers()).isEqualTo(passengers);
		// Verify
		Mockito.verify(this.repo, Mockito.times(1)).findAll();
	}
	
	/* TEST FOR GET BY RESERVATION NUMBER
	 * this test checks whether the method behaves in the expected way
	 * no real entries are created as mock objects are used
	 */
	@Test
	void getByReservationTest() {
		// Given
		String reservation = "YZESP14C";
		Passengers foundPassenger = new Passengers(1, "YZESP14C", "Richard", "Mansworth", "GB472749", "richa@qa.co.uk", false);
		// When
		Mockito.when(this.repo.findPassengersByReservation(reservation)).thenReturn(Optional.of(foundPassenger));
		// Then
		assertThat(this.service.getByReservation(reservation).get()).isEqualTo(foundPassenger);
		// Verify
		Mockito.verify(this.repo, Mockito.times(1)).findPassengersByReservation(Mockito.anyString());
	}
	
	
	/* TEST FOR UPDATE PASSENGER
	 * this test checks whether the method behaves in the expected way
	 * no real entries are created as mock objects are used
	 */
	@Test
	void updatePassengerTest() {
		// Given
		int id = 1;
		Passengers reservationSaved = new Passengers(1, "JNBK675U", "Ben", "Dover", "GB244928", "ben@dover.co.uk", false);
		Passengers reservationUpdate = new Passengers("JNBK675U", "Ben", "Dover", "GB244928", "ben@dover.co.uk", true);
		Passengers actualReservationUpdate = new Passengers(1, "JNBK675U", "Ben", "Dover", "GB244928", "ben@dover.co.uk", true);
		// When
		Mockito.when(this.repo.findById(id)).thenReturn(Optional.of(reservationSaved));
		Mockito.when(this.repo.save(actualReservationUpdate)).thenReturn(actualReservationUpdate);
		// Then
		Assertions.assertThat(this.service.updatePassenger(id, reservationUpdate)).isEqualTo(actualReservationUpdate);
		// Verify
		Mockito.verify(this.repo, Mockito.times(1)).findById(Mockito.anyInt());
		Mockito.verify(this.repo, Mockito.times(1)).save(Mockito.any(Passengers.class));
	}
	
	/* TEST FOR DELETE 
	 * this test checks whether the method behaves in the expected way
	 * no real entries are created as mock objects are used
	 */
	@Test
	void deleteTest() {
		// Given
		int id = 1;
		// When
		Mockito.when(this.repo.existsById(id)).thenReturn(false);
		// Then
		assertThat(this.service.deletePassenger(id)).isTrue();
		//Verify
		Mockito.verify(this.repo, Mockito.times(1)).deleteById(Mockito.anyInt());
		Mockito.verify(this.repo, Mockito.times(1)).existsById(Mockito.anyInt());
	}
	
	
	

}
