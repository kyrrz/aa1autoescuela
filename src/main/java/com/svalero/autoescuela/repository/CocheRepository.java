package com.svalero.autoescuela.repository;

import com.svalero.autoescuela.model.Coche;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CocheRepository extends CrudRepository<Coche, Long> {
}
