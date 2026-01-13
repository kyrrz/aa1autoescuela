package com.svalero.autoescuela.repository;

import com.svalero.autoescuela.model.Matricula;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatriculaRepository extends CrudRepository<Matricula, Long> {
}
