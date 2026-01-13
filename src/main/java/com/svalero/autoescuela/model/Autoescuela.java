package com.svalero.autoescuela.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Autoescuela {
    private long id;
    private String nombre;
    private String direccion;
    private String telefono;
    private String email;
    private int capacidad;
    private float rating;
    private LocalDate fecha_apertura;
    private boolean activa;
}
