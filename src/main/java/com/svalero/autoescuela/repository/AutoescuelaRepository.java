package com.svalero.autoescuela.repository;

import com.svalero.autoescuela.model.Autoescuela;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutoescuelaRepository  extends CrudRepository<Autoescuela, Long> {
    List<Autoescuela> findByCiudad(String ciudad);
    List<Autoescuela> findAll();
}
