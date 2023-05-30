package com.example.demo.service;

import com.example.demo.Repository.FlightRepository;
import com.example.demo.Service.FlightService;
import com.example.demo.Service.FlightServiceImplementare;
import com.example.demo.entity.Flight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class FlightServiceTest {

    private FlightServiceImplementare flightService;
    private Flight flight;

    @Mock
    private FlightRepository flightRepository;

    @BeforeEach
    void init() {
        initMocks(this);
        flight = new Flight();
        flight.setNumber("RO3564");
        flight.setArrival("London");
        flight.setDeparture("Bucharest");
        flight.setDate_time(LocalDateTime.of(2023, 12, 29, 19, 30));
        flight.setNr_seats(250);
        when(flightRepository.findByNumber("RO3564")).thenReturn(flight);
    }
/*
    @Test
    @DisplayName("should return null when no flight is found with the given number")
    void findByNumberReturnsNullWhenNoFlightFound() {
        FlightService flightService = mock(FlightService.class);
        when(flightService.findByNumber("")).thenReturn(null);
        Flight flight = flightService.findByNumber("");
        assertNull(flight);
    }

    @Test
    @DisplayName("should return the flight with the given number")
    void findByNumberReturnsFlightWithGivenNumber() {
        Flight flight = new Flight();
        flight.setNumber("TEST");
        FlightService flightService = mock(FlightService.class);
        when(flightService.findByNumber("TEST")).thenReturn(flight);
        Flight result = flightService.findByNumber("TEST");
        assertEquals(flight, result);
    }
    /*
    @Test
    void findByArrivalWhenExists() {
        flightService = new FlightServiceImplementare(flightRepository);
        List<Flight> flight1 = flightService.findByArrival("London");
        assertNotNull(flight1);
    }
    */
    @Test
    void findByArrivalWhenArrivalNotExist(){
        when(flightRepository.findByArrival("non_existing_arrival")).thenReturn(null);

        Exception exception = assertThrows(NullPointerException.class, () ->
                flightService.findByArrival("non_existing_arrival"));
    }

    @Test
    void anulateFlightTest(){
        flight.setId(1L);
        flight.setNumber("RO3564");
        flight.setArrival("London");
        flight.setDeparture("Bucharest");
        flight.setDate_time(LocalDateTime.of(2023, 12, 29, 19, 30));
        flight.setNr_seats(250);

        flightService = new FlightServiceImplementare(flightRepository);
        flightService.anulateFlight(flight.getId());
        assertEquals(flightService.findById(1L), Optional.empty());
    }

/*
    @Test
    void updateFlight(){
        flight = new Flight();

        flightService = new FlightServiceImplementare(flightRepository);
        Flight flight2 = flightService.updateFlight(flight, 140);
        assertEquals(flight2.getNr_seats(), 140);
    }
*/
}
