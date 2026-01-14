package com.svalero.autoescuela.service;


import com.svalero.autoescuela.exception.CocheNotFoundException;
import com.svalero.autoescuela.model.Coche;
import com.svalero.autoescuela.repository.CocheRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CocheService {

    @Autowired
    private CocheRepository cocheRepository;

    public Coche add(Coche coche){
        return cocheRepository.save(coche);
    }
    public List<Coche> findByMarca(String marca){
        return cocheRepository.findByMarca(marca);
    }

    public void delete( long id ) throws CocheNotFoundException {
        Coche c = cocheRepository.findById(id)
                .orElseThrow(CocheNotFoundException::new);
        cocheRepository.delete(c);
    }

    public List<Coche> findAll(){
        return cocheRepository.findAll();
    }

    public Coche findById(long id) throws CocheNotFoundException{
        return cocheRepository.findById(id)
                .orElseThrow(CocheNotFoundException::new);
    }

    public Coche modify(long id, Coche coche) throws CocheNotFoundException {
        Coche oldCoche = cocheRepository.findById(id)
                .orElseThrow(CocheNotFoundException::new);

        oldCoche.setMatricula(coche.getMatricula());
        oldCoche.setMarca(coche.getMarca());
        oldCoche.setModelo(coche.getModelo());
        oldCoche.setTipoCambio(coche.getTipoCambio());
        oldCoche.setKilometraje(coche.getKilometraje());
        oldCoche.setFechaCompra(coche.getFechaCompra());
        oldCoche.setPrecioCompra(coche.getPrecioCompra());
        oldCoche.setDisponible(coche.isDisponible());



        return cocheRepository.save(oldCoche);
    }

    }
