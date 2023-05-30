package com.example.demo.Service;

import com.example.demo.controllers.DTO.TicketDTO;
import com.example.demo.entity.Ticket;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public interface TicketService {

    Ticket findByNoTicket(Long number);
    List<TicketDTO> findByPassagerName(String passagerName);
    TicketDTO deleteTicket(Long noTicket);
    Ticket modifyPrice(Ticket ticket, int price);
    Ticket insertTicket(Ticket ticket);
    List<TicketDTO> findByFlight(String number_flight);
    Map<String, String> ticketDetails(Long id);
}
