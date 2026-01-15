package com.svalero.autoescuela.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlumnoInDto {

    private String nombre;
    private String apellidos;
    private String dni;
    private LocalDate fechaNacimiento;
    private String telefono;
    private String email;
    private String direccion;
    private String ciudad;
    private boolean usaGafas;
    private float notaTeorico;
    private long autoescuelaId;
}
