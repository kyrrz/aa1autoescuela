package com.svalero.autoescuela.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Coche {
    private long id;
    private String matricula;
    private String marca;
    private String modelo;
    private String tipo_cambio;
    private int kilometraje;
    private LocalDate fecha_compra;
    private float precio_compra;
    private boolean disponible;
    private int autoescuela_id;
}
