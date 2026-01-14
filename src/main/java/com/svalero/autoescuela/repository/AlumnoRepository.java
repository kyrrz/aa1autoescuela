package com.svalero.autoescuela.repository;


import com.svalero.autoescuela.model.Alumno;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlumnoRepository extends CrudRepository<Alumno, Long>{
    List<Alumno> findAll();
    List<Alumno> findByUsaGafas(boolean usaGafas);
    List<Alumno> findByCiudad(String ciudad);

}
