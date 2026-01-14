package com.svalero.autoescuela.repository;

import com.svalero.autoescuela.model.Coche;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CocheRepository extends CrudRepository<Coche, Long> {
    List<Coche> findByMarca(String marca);
    List<Coche> findAll();
}
