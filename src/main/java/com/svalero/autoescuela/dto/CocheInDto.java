package com.svalero.autoescuela.dto;
import com.svalero.autoescuela.model.Autoescuela;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CocheInDto {

    private String matricula;
    private String marca;
    private String modelo;
    private String tipoCambio;
    private Integer kilometraje;
    private LocalDate fechaCompra;
    private Float precioCompra;
    private Boolean disponible;
    private Long autoescuelaId;

}
