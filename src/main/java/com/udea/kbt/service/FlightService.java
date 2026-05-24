package com.udea.kbt.service;

import com.udea.kbt.dao.IFlightDAO;
import com.udea.kbt.exception.FlightNotFoundException;
import com.udea.kbt.model.Flight;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FlightService {

    @Autowired
    private IFlightDAO dao;

    // Guardar
    public Flight save(Flight flight) {
        return dao.save(flight);
    }

    // Eliminar
    public String delete(long id) {
        dao.deleteById(id);
        return "Flight removed";
    }

    // Listar todos

    public List<Flight> list() {

        List<Flight> flights = new ArrayList<>();

        dao.findAll().forEach(flights::add);

        return flights;
    }

    // Buscar por ID
    public Optional<Flight> listId(long id) {
        return dao.findById(id);
    }

    // Actualizar
    public Flight update(Flight flight) {

        Flight existingFlight = dao.findById(flight.getIdFlight())
                .orElseThrow(() -> new RuntimeException("Flight not found"));

        existingFlight.setNombreAvion(flight.getNombreAvion());
        existingFlight.setNumeroVuelo(flight.getNumeroVuelo());
        existingFlight.setOrigen(flight.getOrigen());
        existingFlight.setDestino(flight.getDestino());
        existingFlight.setCapacidad(flight.getCapacidad());
        existingFlight.setRating(flight.getRating());
        existingFlight.setPlanvuelo(flight.getPlanvuelo());
        existingFlight.setCumplido(flight.getCumplido());

        return dao.save(existingFlight);
    }

    // Mejores vuelos
    public List<Flight> viewBestFlight() {

        List<Flight> flights = dao.viewBestFlight();

        if (!flights.isEmpty()) {
            return flights;
        } else {
            throw new FlightNotFoundException(
                    "No Flight found with rating >= 4"
            );
        }
    }

}