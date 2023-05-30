package com.example.demo.controllers;

import com.example.demo.Service.FlightService;
import com.example.demo.controllers.DTO.FlightDTO;
import com.example.demo.entity.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/flight")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @GetMapping("/findAll")
    public ResponseEntity<List<FlightDTO>> findAll() {
        return ResponseEntity.ok(flightService.findAll());
    }

    @GetMapping("/findByArrival")
    public ResponseEntity<List<FlightDTO>> findByArrival(@RequestParam String arrival){
        return ResponseEntity.ok(flightService.findByArrival(arrival));
    }

    @GetMapping("/findById")
    public ResponseEntity<Optional<Flight>> findById(@RequestParam Long id){
        return ResponseEntity.ok(flightService.findById(id));
    }

    @GetMapping("/findByDeparture")
    public ResponseEntity<List<FlightDTO>> findByDeparture(@RequestParam String departure){
        return ResponseEntity.ok(flightService.findByDeparture(departure));
    }

    @GetMapping("/findByArrivalAndDeparture")
    public ResponseEntity<List<FlightDTO>> findByArrialAndDeparture(@RequestParam String arrival, @RequestParam String departure){
        return ResponseEntity.ok(flightService.findByArrivalAndDeparture(arrival, departure));
    }

    @PutMapping("/updateNrSeats")
    public Flight updateNrSeats(@RequestBody FlightDTO flightDTO){
        return flightService.updateFlight(flightDTO, flightDTO.getNr_seats());
    }

    @DeleteMapping("/deleteFlight")
    public void anulateFlight(@RequestParam Long id){
        flightService.anulateFlight(id);
    }

    @PutMapping("/insertFlight")
    public Flight insertFlight(@RequestBody Flight flight){
        return flightService.insertFlight(flight);
    }

    @GetMapping("/sort")
    public List<FlightDTO> sortByPrice(){
        return flightService.sortByPrice();
    }
}
