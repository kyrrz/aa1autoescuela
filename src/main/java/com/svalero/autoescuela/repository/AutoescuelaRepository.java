package com.svalero.autoescuela.repository;

import com.svalero.autoescuela.model.Autoescuela;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutoescuelaRepository  extends CrudRepository<Autoescuela, Long> {
}
