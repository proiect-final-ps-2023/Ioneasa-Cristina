package com.example.demo.entity;

import com.example.demo.controllers.DTO.UserDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data

public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long noTicket;
    @NonNull
    public String passagerName;
    public String seat;
    @NonNull
    public String numberFlight;
    @NonNull
    public int price;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name="fk_ticket_to_user")
    private User userToTicket;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="fk_ticket_to_flight")
    private Flight flightToTicket;
}
