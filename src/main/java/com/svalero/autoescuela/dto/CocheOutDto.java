package com.svalero.autoescuela.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CocheOutDto {
    private long id;
    private String marca;
    private String modelo;
    private String tipoCambio;
    private long autoescuelaId;

}
