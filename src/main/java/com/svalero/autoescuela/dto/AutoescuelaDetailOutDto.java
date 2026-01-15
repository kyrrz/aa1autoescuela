package com.svalero.autoescuela.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class AutoescuelaDetailOutDto {

    private long id;
    private String nombre;
    private String direccion;
    private String ciudad;
    private String telefono;
    private String email;
    private float rating;
    private boolean activa;
    private List<CocheOutDto> coches;
    private List<ProfesorOutDto> profesores;


}
