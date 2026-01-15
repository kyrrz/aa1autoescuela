package com.svalero.autoescuela.repository;


import com.svalero.autoescuela.model.Alumno;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlumnoRepository extends CrudRepository<Alumno, Long>{
    List<Alumno> findAll();

    List<Alumno> findByNombre(String nombre);
    List<Alumno> findByCiudad(String ciudad);
    List<Alumno> findByUsaGafas(Boolean usaGafas);
    List<Alumno> findByNotaTeorico(Float notaTeorico);

    List<Alumno> findByNombreAndCiudad(String nombre, String ciudad);
    List<Alumno> findByNombreAndUsaGafas(String nombre, Boolean usaGafas);
    List<Alumno> findByNombreAndNotaTeorico(String nombre, Float notaTeorico);

    List<Alumno> findByCiudadAndUsaGafas(String ciudad, Boolean usaGafas);
    List<Alumno> findByCiudadAndNotaTeorico(String ciudad, Float notaTeorico);
    List<Alumno> findByUsaGafasAndNotaTeorico(Boolean usaGafas, Float notaTeorico);

    List<Alumno> findByNombreAndCiudadAndUsaGafas(String nombre, String ciudad, Boolean usaGafas);
    List<Alumno> findByNombreAndCiudadAndNotaTeorico(String nombre, String ciudad, Float notaTeorico);
    List<Alumno> findByNombreAndUsaGafasAndNotaTeorico(String nombre, Boolean usaGafas, Float notaTeorico);
    List<Alumno> findByCiudadAndUsaGafasAndNotaTeorico(String ciudad, Boolean usaGafas, Float notaTeorico);

    List<Alumno> findByNombreAndCiudadAndUsaGafasAndNotaTeorico(String nombre, String ciudad, Boolean usaGafas, Float notaTeorico);




}
