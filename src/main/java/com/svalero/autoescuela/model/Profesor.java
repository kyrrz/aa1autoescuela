package com.svalero.autoescuela.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Profesor {
    private long id;
    private String nombre;
    private String apellidos;
    private String dni;
    private String telefono;
    private float salario;
    private LocalDate fecha_contratacion;
    private String especialidad;
    private boolean activo;
    private int autoescuela_id;
}
