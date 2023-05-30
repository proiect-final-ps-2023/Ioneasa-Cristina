package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Data

public class Flight implements Comparable<Flight>{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NonNull
    private String number;
    @NonNull
    private String departure;
    @NonNull
    private String arrival;
    @NonNull
    private LocalDateTime date_time;
    @NonNull
    private LocalDateTime date_time_arrival;
    @NonNull
    private Float duration;
    @NonNull
    private int nr_seats;
    @NonNull
    private int price;

    @OneToMany(mappedBy= "flightToTicket",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Ticket> ticket=new ArrayList<>();

    @ManyToOne
    private User user;

    @Override
    public int compareTo(Flight o) {
        Integer price = this.getPrice();
        return price.compareTo(o.getPrice());
    }
}
