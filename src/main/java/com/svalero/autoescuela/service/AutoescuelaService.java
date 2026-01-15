package com.svalero.autoescuela.service;

import com.svalero.autoescuela.dto.AutoescuelaInDto;
import com.svalero.autoescuela.exception.AutoescuelaNotFoundException;
import com.svalero.autoescuela.model.Autoescuela;
import com.svalero.autoescuela.repository.AutoescuelaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AutoescuelaService {

    @Autowired
    private AutoescuelaRepository autoescuelaRepository;
    @Autowired
    private ModelMapper modelMapper;

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

    public List<Autoescuela> findAllById(List<Long> ids) throws AutoescuelaNotFoundException{
        List<Autoescuela> autoescuelas = (List<Autoescuela>) autoescuelaRepository.findAllById(ids);
        return autoescuelas;
    }

    public Autoescuela modify(long id, AutoescuelaInDto autoescuelaInDto) throws AutoescuelaNotFoundException {
        Autoescuela a = autoescuelaRepository.findById(id)
                .orElseThrow(AutoescuelaNotFoundException::new);
        modelMapper.map(autoescuelaInDto, a);
        a.setId(id);
//        oldAuto.setNombre(autoescuelaInDto.getNombre());
//        oldAuto.setDireccion(autoescuelaInDto.getDireccion());
//        oldAuto.setCiudad(autoescuelaInDto.getCiudad());
//        oldAuto.setTelefono(autoescuelaInDto.getTelefono());
//        oldAuto.setEmail(autoescuelaInDto.getEmail());
//        oldAuto.setCapacidad(autoescuelaInDto.getCapacidad());
//        oldAuto.setRating(autoescuelaInDto.getRating());
//        oldAuto.setFechaApertura(autoescuelaInDto.getFechaApertura());
//        oldAuto.setActiva(autoescuelaInDto.isActiva());


        return autoescuelaRepository.save(a);
    }


}
