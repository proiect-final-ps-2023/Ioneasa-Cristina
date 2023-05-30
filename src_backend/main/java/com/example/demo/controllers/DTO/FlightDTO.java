package com.example.demo.controllers.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class FlightDTO {
    private Long id;
    private String number;
    private String departure;
    private String arrival;
    private LocalDateTime date_time;
    private LocalDateTime date_time_arrival;
    private float duration;
    private int nr_seats;
    private int price;
}
