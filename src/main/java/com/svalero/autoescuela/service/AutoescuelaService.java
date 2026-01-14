package com.svalero.autoescuela.service;

import com.svalero.autoescuela.exception.AutoescuelaNotFoundException;
import com.svalero.autoescuela.model.Autoescuela;
import com.svalero.autoescuela.repository.AutoescuelaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutoescuelaService {

    @Autowired
    private AutoescuelaRepository autoescuelaRepository;

    public Autoescuela add(Autoescuela autoescuela){
        return autoescuelaRepository.save(autoescuela);
    }
    public List<Autoescuela> findByCiudad(String ciudad){
        return autoescuelaRepository.findByCiudad(ciudad);
    }

    public void delete( long id ) throws AutoescuelaNotFoundException {
        Autoescuela a = autoescuelaRepository.findById(id)
                .orElseThrow(AutoescuelaNotFoundException::new);
        autoescuelaRepository.delete(a);
    }

    public List<Autoescuela> findAll(){
        return autoescuelaRepository.findAll();
    }

    public Autoescuela findById(long id) throws AutoescuelaNotFoundException{
        return autoescuelaRepository.findById(id)
                .orElseThrow(AutoescuelaNotFoundException::new);
    }

    public Autoescuela modify(long id, Autoescuela autoescuela) throws AutoescuelaNotFoundException {
        Autoescuela oldAuto = autoescuelaRepository.findById(id)
                .orElseThrow(AutoescuelaNotFoundException::new);

        oldAuto.setNombre(autoescuela.getNombre());
        oldAuto.setDireccion(autoescuela.getDireccion());
        oldAuto.setCiudad(autoescuela.getCiudad());
        oldAuto.setTelefono(autoescuela.getTelefono());
        oldAuto.setEmail(autoescuela.getEmail());
        oldAuto.setCapacidad(autoescuela.getCapacidad());
        oldAuto.setRating(autoescuela.getRating());
        oldAuto.setFechaApertura(autoescuela.getFechaApertura());
        oldAuto.setActiva(autoescuela.isActiva());

        return autoescuelaRepository.save(oldAuto);
    }


}
