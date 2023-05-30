package com.example.demo.controllers;

import com.example.demo.Repository.FlightRepository;
import com.example.demo.Service.FlightService;
import com.example.demo.Service.TicketService;
import com.example.demo.Service.UserService;
import com.example.demo.controllers.DTO.FlightDTO;
import com.example.demo.controllers.DTO.TicketDTO;
import com.example.demo.controllers.DTO.UserDTO;
import com.example.demo.controllers.mapper.FlightMapper;
import com.example.demo.controllers.mapper.UserMapper;
import com.example.demo.entity.Flight;
import com.example.demo.entity.Ticket;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private FlightService flightService;

    @Autowired
    private UserService userService;

    @Autowired
    private FlightRepository flightRepository;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @DeleteMapping("/deleteTicket")
    public TicketDTO deleteTicket(@RequestParam Long noTicket) {
        System.out.println(noTicket);
        return ticketService.deleteTicket(noTicket);
    }

    @PutMapping("/updatePrice")
    public Ticket updatePrice(@RequestBody Ticket ticket) {
        return ticketService.modifyPrice(ticket, ticket.getPrice());
    }

    @PutMapping("/insertTicket")
    @Transactional
    public Ticket insertTicket(@RequestParam String number, @RequestParam String email, @RequestParam String seat){

            FlightDTO flight = flightService.findByNumber(number);
            UserDTO user = userService.findByEmail(email);

            Flight flight1 = FlightMapper.mapDTOToModel(flight);
            flight1.setNr_seats(flight1.getNr_seats() - 1);
            flightRepository.save(flight1);

            //System.out.println(user.getName());
            Ticket ticket = new Ticket();
            ticket.setNumberFlight(flight.getNumber());
            ticket.setSeat(seat);
            ticket.setPrice(flight.getPrice());
            ticket.setPassagerName(user.getName());
            ticket.setFlightToTicket(FlightMapper.mapDTOToModel(flight));
            ticket.setUserToTicket(UserMapper.mapDTOToModel(user));

            return ticketService.insertTicket(ticket);

    }

    @GetMapping("/findByPassagerName")
    public List<TicketDTO> findByPassagerName(@RequestParam String passagerName){
        return ticketService.findByPassagerName(passagerName);
    }

    @GetMapping("/findByFlight")
    public List<TicketDTO>findByFlight(@RequestParam String number_flight){
        return ticketService.findByFlight(number_flight);
    }

    @GetMapping("/ticketDetails")
    public ResponseEntity<Map<String, String>> ticketDetails(@RequestParam Long id){
        return ResponseEntity.ok().body(ticketService.ticketDetails(id));
}

}












