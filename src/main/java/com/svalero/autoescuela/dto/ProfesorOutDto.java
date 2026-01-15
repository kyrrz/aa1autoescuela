package com.svalero.autoescuela.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfesorOutDto {
    private long id;
    private String nombre;
    private String apellidos;
    private String dni;
    private String telefono;
    private String especialidad;
    private boolean activo;

}
