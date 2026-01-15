package com.svalero.autoescuela.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class MatriculaInDto {
    private String modalidad;
    private String tipoMatricula;
    private LocalDate fechaInicio;
    private LocalDate fechaFinal;
    private float precio;
    private int horasPracticas;
    private int horasTeoricas;
    private boolean completada;
    private String observaciones;
    private long autoescuelaId;
    private long alumnoId;
}
