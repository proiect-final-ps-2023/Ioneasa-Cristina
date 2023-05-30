package com.example.demo.service;

import com.example.demo.Repository.TicketRepository;
import com.example.demo.Service.TicketServiceImplementare;
import com.example.demo.entity.Ticket;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TicketServiceTest {

    @Mock
    TicketRepository ticketRepository;

    @InjectMocks
    TicketServiceImplementare ticketService;

    @Test
    @DisplayName("should return null when no ticket is found with the given number")
    void findByNoTicketWhenNoTicketFound() {
        Long number = 1L;
        when(ticketRepository.findByNoTicket(number)).thenReturn(null);
        Ticket ticket = ticketService.findByNoTicket(number);
        assertNull(ticket);
    }

    @Test
    @DisplayName("should return the ticket with the given number")
    void findByNoTicketWithGivenNumber() {
        Ticket ticket = new Ticket();
        ticket.setNoTicket(1L);
        when(ticketRepository.findByNoTicket(1L)).thenReturn(ticket);
        Ticket result = ticketService.findByNoTicket(1L);
        assertEquals(1L, result.getNoTicket());
    }

    @Test
    void deleteTicketTest(){
        Ticket ticket = new Ticket();
        ticket.setNoTicket(1234L);
      //  ticket.setNumberFlight("RO4563");
        ticket.setSeat("33A");
        ticket.setPassagerName("Ioneasa");

        ticketService = new TicketServiceImplementare(ticketRepository);
        ticketService.deleteTicket(ticket.getNoTicket());
        assertEquals(ticketService.findByNoTicket(ticket.getNoTicket()), null);
    }

    @Test
    void modifyPriceTest(){
        Ticket ticket = new Ticket();
        ticket.setNoTicket(1234L);
       // ticket.setNumberFlight("RO4563");
        ticket.setSeat("33A");
        ticket.setPassagerName("Ioneasa");

        ticketService = new TicketServiceImplementare(ticketRepository);
        Ticket ticket2 = ticketService.modifyPrice(ticket, 60);
        assertEquals(ticket2.getPrice(), 60);
    }
}