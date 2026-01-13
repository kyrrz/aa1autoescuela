package com.svalero.autoescuela.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity(name = "profesores")
@Table(name = "profesores")
public class Profesor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String nombre;
    @Column
    private String apellidos;
    @Column
    private String dni;
    @Column
    private String telefono;
    @Column
    private float salario;
    @Column(name = "fecha_contratacion")
    private LocalDate fechaContratacion;
    @Column
    private String especialidad;
    @Column
    private boolean activo;
    @Column(name = "autoescuela_id")
    private int autoescuelaId;
}
