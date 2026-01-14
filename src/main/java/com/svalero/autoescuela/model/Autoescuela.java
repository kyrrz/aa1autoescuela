package com.svalero.autoescuela.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity(name = "autoescuelas")
@Table(name = "autoescuelas")
public class Autoescuela {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String nombre;
    @Column
    private String direccion;
    @Column
    private String ciudad;
    @Column
    private String telefono;
    @Column
    private String email;
    @Column
    private int capacidad;
    @Column
    private float rating;
    @Column(name = "fecha_apertura")
    private LocalDate fechaApertura;
    @Column
    private boolean activa;

    @OneToMany(mappedBy = "autoescuela")
    private List<Matricula> matriculas;

    @OneToMany(mappedBy = "autoescuela")
    private List<Alumno> alumnos;

    @OneToMany(mappedBy = "autoescuela")
    private List<Coche> coches;

    @ManyToMany
    @JoinTable(name = "profesores_autoescuela",
            joinColumns = @JoinColumn(name = "autoescuela_id"),
            inverseJoinColumns = @JoinColumn(name = "profesor_id"))
    private List<Profesor> profesores;
}
