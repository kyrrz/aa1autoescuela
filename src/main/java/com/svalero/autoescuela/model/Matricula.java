package com.svalero.autoescuela.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Matricula {
    private long id;
    private int alumno_id;
    private String tipo_matricula;
    private LocalDate fecha_inicio;
    private LocalDate fecha_final;
    private float precio;
    private int horas_practicas;
    private int horas_teoricas;
    private boolean completada;
    private String observaciones;
}
