package com.example.demo.controllers.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TicketDTO {

    public Long noTicket;
    public String passagerName;
    public String seat;
    public String numberFlight;
    public int price;
}
