package com.svalero.autoescuela.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class AutoescuelaInDto {

    private String nombre;
    private String direccion;
    private String ciudad;
    private String telefono;
    private String email;
    private int capacidad;
    private float rating;
    private LocalDate fechaApertura;
    private boolean activa;



}
