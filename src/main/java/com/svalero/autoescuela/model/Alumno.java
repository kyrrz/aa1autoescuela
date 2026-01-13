package com.svalero.autoescuela.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Alumno {
    private long id;
    private String nombre;
    private String apellidos;
    private String dni;
    private LocalDate fecha_nacimiento;
    private String telefono;
    private String email;
    private String direccion;
    private boolean usa_gafas;
    private float nota_teorico;
    private int autoescuela_id;
}
