package com.svalero.autoescuela.repository;

import com.svalero.autoescuela.model.Matricula;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatriculaRepository extends CrudRepository<Matricula, Long> {
    List<Matricula> findAll();
    List<Matricula> findByModalidad(String modalidad);
}
