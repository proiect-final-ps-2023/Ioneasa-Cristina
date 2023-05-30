package com.example.demo.Service;

import com.example.demo.Repository.TicketRepository;
import com.example.demo.controllers.DTO.TicketDTO;
import com.example.demo.controllers.mapper.TicketMapper;
import com.example.demo.entity.Flight;
import com.example.demo.entity.Ticket;
import com.example.demo.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@Service
public class TicketServiceImplementare implements TicketService{

    @Autowired
    TicketRepository ticketRep;
    public TicketServiceImplementare(TicketRepository ticketRepository){
        this.ticketRep = ticketRepository;
    }

    @Override
    public Ticket findByNoTicket(Long number) {
        return ticketRep.findByNoTicket(number);
    }

    @Override
    public List<TicketDTO> findByPassagerName(String passagerName) {
        List<Ticket> tickets = (List<Ticket>) ticketRep.findByPassagerName(passagerName);
        return tickets.stream().map(f -> TicketMapper.mapModelToDTO(f)).toList();
    }

    @Override
    public TicketDTO deleteTicket(Long noTicket) {
        Ticket t = ticketRep.findByNoTicket(noTicket);

        if(t!=null) {
            System.out.println(t.noTicket);
            User user = t.getUserToTicket();
            if(user!=null) user.getTicket().remove(t);

            Flight flight = t.getFlightToTicket();
            if(flight!=null) flight.getTicket().remove(t);
            ticketRep.delete(t);
        }
        return null;
        //return !ticketRep.existsByNoTicket(noTicket);
    }

    @Override
    public Ticket modifyPrice(Ticket ticket, int price) {
        ticket.setPrice(price);
        ticketRep.save(ticket);
        return ticket;
    }

    @Override
    public Ticket insertTicket(Ticket ticket) {
        return ticketRep.save(ticket);
    }

    @Override
    public List<TicketDTO> findByFlight(String number_flight) {
        List<Ticket> tickets = (List<Ticket>) ticketRep.findByNumberFlight(number_flight);
        return tickets.stream().map(f -> TicketMapper.mapModelToDTO(f)).toList();
    }

    @Override
    public Map<String, String> ticketDetails(Long id) {
        Ticket ticket = ticketRep.findByNoTicket(id);
        Map<String, String> pdf = new HashMap<>();
        pdf.put("text", "\n\nDear " + ticket.getPassagerName() + ", here is your ticket details:"
            + "\n\nNumber ticket: " + ticket.getNoTicket()
            + "\nNumber flight: " + ticket.getNumberFlight()
            + "\nSeat:" + ticket.getSeat()
            + "\n\n \t\t\t\t\t\t\t\t\t\t\t Price: " + ticket.getPrice() + "€");
    return pdf;
    }

}
