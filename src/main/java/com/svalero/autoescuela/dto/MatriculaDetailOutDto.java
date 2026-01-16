package com.svalero.autoescuela.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatriculaDetailOutDto
{

    private long id;
    private String modalidad;
    private String tipoMatricula;
    private int horasPracticas;
    private int horasTeoricas;
    private AutoescuelaOutDto autoescuela;
    private AlumnoOutDto alumno;
    private String observaciones;

}
