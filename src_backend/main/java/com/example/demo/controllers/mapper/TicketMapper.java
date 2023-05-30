package com.example.demo.controllers.mapper;

import com.example.demo.controllers.DTO.TicketDTO;
import com.example.demo.entity.Ticket;

public class TicketMapper {

    public static TicketDTO mapModelToDTO(Ticket ticket){
        TicketDTO ticketDTO = new TicketDTO();

        ticketDTO.setNoTicket(ticket.getNoTicket());
        ticketDTO.setPrice(ticket.getPrice());
        ticketDTO.setPassagerName(ticket.getPassagerName());
        ticketDTO.setSeat(ticket.getSeat());
        ticketDTO.setNumberFlight(ticket.getNumberFlight());

        return ticketDTO;
    }
}
