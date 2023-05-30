package com.example.demo.Service;

import com.example.demo.controllers.DTO.FlightDTO;
import com.example.demo.entity.Flight;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface FlightService {
    FlightDTO findByNumber(String number);
    Optional<Flight> findById(Long id);
    List<FlightDTO> findByArrival(String arrival);
    List<FlightDTO> findByDeparture(String departure);
    List<FlightDTO> findByArrivalAndDeparture(String arrival, String departure);
    List<FlightDTO> findAll();
    void anulateFlight(Long id);
    Flight updateFlight(FlightDTO flight, int nr_seats);
    Flight insertFlight(Flight flight);
    List<FlightDTO>sortByPrice();
}
