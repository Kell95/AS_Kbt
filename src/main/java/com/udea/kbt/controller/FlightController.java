package com.udea.kbt.controller;

import com.udea.kbt.exception.InvalidRating;
import com.udea.kbt.exception.ModelNotFoundException;
import com.udea.kbt.model.Flight;
import com.udea.kbt.service.FlightService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/flight")
@CrossOrigin("*")

@Tag(
        name = "Flight Controller",
        description = "API para gestión de vuelos"
)
public class FlightController {

    @Autowired
    private FlightService flightService;

    @Operation(summary = "Guardar un nuevo vuelo")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Vuelo creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    @PostMapping("/save")
    public ResponseEntity<Long> save(
            @Valid @RequestBody Flight flight) {

        if (flight.getRating() > 5) {
            throw new InvalidRating(
                    "Rating debe ser menor o igual a 5"
            );
        }

        Flight savedFlight = flightService.save(flight);

        return new ResponseEntity<>(
                savedFlight.getIdFlight(),
                HttpStatus.CREATED
        );
    }

    @Operation(summary = "Ver lista de vuelos disponibles")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente")
    })
    @GetMapping("/listAll")
    public ResponseEntity<List<Flight>> listAllFlights() {

        List<Flight> flights = (List<Flight>) flightService.list();

        return new ResponseEntity<>(
                flights,
                HttpStatus.OK
        );
    }

    @Operation(summary = "Buscar vuelo por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Vuelo encontrado"),
            @ApiResponse(responseCode = "404", description = "Vuelo no encontrado")
    })
    @GetMapping("/list/{id}")
    public ResponseEntity<Flight> listFlightById(
            @PathVariable("id") long id) {

        Optional<Flight> flight =
                flightService.listId(id);

        if (flight.isPresent()) {
            return new ResponseEntity<>(
                    flight.get(),
                    HttpStatus.OK
            );
        }

        throw new ModelNotFoundException(
                "ID de Flight inválido"
        );
    }

    @Operation(summary = "Ver mejores vuelos")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista obtenida"),
            @ApiResponse(responseCode = "404", description = "No existen vuelos")
    })
    @GetMapping("/topFlights")
    public ResponseEntity<List<Flight>> viewBestFlights() {

        List<Flight> list =
                flightService.viewBestFlight();

        return new ResponseEntity<>(
                list,
                HttpStatus.OK
        );
    }

    @Operation(summary = "Actualizar un vuelo")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Vuelo actualizado"),
            @ApiResponse(responseCode = "404", description = "Vuelo no encontrado")
    })
    @PutMapping("/update")
    public ResponseEntity<Flight> updateFlight(
            @Valid @RequestBody Flight flight) {

        Flight updatedFlight =
                flightService.update(flight);

        return new ResponseEntity<>(
                updatedFlight,
                HttpStatus.OK
        );
    }

    @Operation(summary = "Eliminar un vuelo por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Vuelo eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Vuelo no encontrado")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteFlight(
            @PathVariable long id) {

        String message =
                flightService.delete(id);

        return new ResponseEntity<>(
                message,
                HttpStatus.OK
        );
    }
}