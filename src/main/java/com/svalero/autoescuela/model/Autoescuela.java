package com.svalero.autoescuela.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity(name = "autoescuela")
@Table(name = "autoescuela")
public class Autoescuela {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String nombre;
    @Column
    private String direccion;
    @Column
    private String telefono;
    @Column
    private String email;
    @Column
    private int capacidad;
    @Column
    private float rating;
    @Column(name = "fecha_apertura")
    private LocalDate fechaApertura;
    @Column
    private boolean activa;
}
