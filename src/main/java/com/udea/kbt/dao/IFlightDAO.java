package com.udea.kbt.dao;

import com.udea.kbt.model.Flight;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IFlightDAO extends CrudRepository<Flight, Long> {

    @Query("FROM Flight f WHERE f.rating >= 4 AND f.cumplido = true")
    List<Flight> viewBestFlight();
}