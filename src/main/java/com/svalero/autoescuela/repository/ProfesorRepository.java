package com.svalero.autoescuela.repository;

import com.svalero.autoescuela.model.Profesor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfesorRepository extends CrudRepository<Profesor, Long> {
    List<Profesor> findAll();
    List<Profesor> findByEspecialidad(String especialidad);
}
