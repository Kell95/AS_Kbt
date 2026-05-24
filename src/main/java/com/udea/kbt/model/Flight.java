package com.udea.kbt.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "flight")
public class Flight implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idflight")
    private Long idFlight;

    @Column(name = "nombreavion", nullable = false)
    private String nombreAvion;

    @Column(name = "numerovuelo", nullable = false)
    private String numeroVuelo;

    @Column(name = "origen", nullable = false)
    private String origen;

    @Column(name = "destino", nullable = false)
    private String destino;

    @Column(name = "capacidad", nullable = false)
    private int capacidad;

    @Min(1)
    @Max(5)
    @Column(name = "rating", nullable = false)
    private int rating;

    @Column(name = "planvuelo", nullable = false)
    private long planvuelo;

    @Column(name = "cumplido")
    private Boolean cumplido;
}