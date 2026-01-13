package com.svalero.autoescuela.repository;


import com.svalero.autoescuela.model.Alumno;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlumnoRepository extends CrudRepository<Alumno, Long>{
}
