package com.svalero.autoescuela.repository;

import com.svalero.autoescuela.model.Autoescuela;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutoescuelaRepository  extends CrudRepository<Autoescuela, Long> {

    List<Autoescuela> findAll();

    List<Autoescuela> findByCiudad(String ciudad);
    List<Autoescuela> findByRating(Float rating);
    List<Autoescuela> findByActiva(Boolean activa);

    List<Autoescuela> findByCiudadAndRating(String ciudad, Float rating);
    List<Autoescuela> findByCiudadAndActiva(String ciudad, Boolean activa);
    List<Autoescuela> findByRatingAndActiva(Float rating, Boolean activa);

    List<Autoescuela> findByCiudadAndRatingAndActiva(String ciudad, Float rating, Boolean activa);
}
