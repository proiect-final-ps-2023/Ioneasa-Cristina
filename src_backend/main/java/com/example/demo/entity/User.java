package com.example.demo.entity;

import com.example.demo.controllers.DTO.FlightDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Getter
@Setter

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false, length = 30)
    private String password;
    private User_type type;
    private boolean logged;

    @OneToMany
    private List<Flight> favorites = new ArrayList<>();

    @OneToMany(mappedBy= "userToTicket",cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private List<Ticket> ticket=new ArrayList<>();

}
