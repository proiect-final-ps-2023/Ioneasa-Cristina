package com.example.demo.Repository;

import com.example.demo.entity.Ticket;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    Ticket findByNoTicket(Long number);
    List<Ticket> findByPassagerName(String passagerName);
    void deleteByNoTicket(Long noTicket);
    boolean existsByNoTicket(long noTichet);
    List<Ticket>findByNumberFlight(String numberFlight);
}
