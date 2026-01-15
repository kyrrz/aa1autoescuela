package com.svalero.autoescuela.repository;

import com.svalero.autoescuela.model.Coche;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CocheRepository extends CrudRepository<Coche, Long> {

    List<Coche> findAll();

    List<Coche> findByMarca(String marca);
    List<Coche> findByModelo(String modelo);
    List<Coche> findByTipoCambio(String tipoCambio);

    List<Coche> findByMarcaAndModelo(String marca, String modelo);
    List<Coche> findByMarcaAndTipoCambio(String marca, String tipoCambio);
    List<Coche> findByModeloAndTipoCambio(String modelo, String tipoCambio);

    List<Coche> findByMarcaAndModeloAndTipoCambio(String marca, String modelo, String tipoCambio);
}
