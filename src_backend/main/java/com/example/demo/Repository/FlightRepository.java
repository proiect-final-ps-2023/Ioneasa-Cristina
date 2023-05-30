package com.example.demo.Repository;

import com.example.demo.entity.Flight;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FlightRepository extends CrudRepository<Flight, Long> {
    Flight findByNumber(String number);
    Optional<Flight> findById(Long id);
    List<Flight> findByArrival(String arrival);
    List<Flight> findByDeparture(String departure);
    List<Flight> findByArrivalAndDeparture(String arrival, String departure);
    void deleteById(Long id);

}
