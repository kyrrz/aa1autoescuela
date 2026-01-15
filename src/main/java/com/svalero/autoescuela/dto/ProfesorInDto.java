package com.svalero.autoescuela.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfesorInDto {
    private String nombre;
    private String apellidos;
    private String dni;
    private String telefono;
    private float salario;
    private LocalDate fechaContratacion;
    private String especialidad;
    private boolean activo;
    private List<Long> autoescuelaId;
}
