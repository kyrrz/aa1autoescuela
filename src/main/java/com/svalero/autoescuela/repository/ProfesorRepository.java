package com.svalero.autoescuela.repository;

import com.svalero.autoescuela.model.Profesor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfesorRepository extends CrudRepository<Profesor, Long> {
}
