package com.svalero.autoescuela.service;


import com.svalero.autoescuela.dto.CocheInDto;
import com.svalero.autoescuela.exception.CocheNotFoundException;
import com.svalero.autoescuela.model.Autoescuela;
import com.svalero.autoescuela.model.Coche;
import com.svalero.autoescuela.repository.CocheRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CocheService {

    @Autowired
    private CocheRepository cocheRepository;
    @Autowired
    private ModelMapper modelMapper;

    public Coche add(CocheInDto cocheInDto, Autoescuela  autoescuela){
        Coche coche = new Coche();
        modelMapper.map(cocheInDto, coche);
//        coche.setMatricula(cocheInDto.getMatricula());
//        coche.setMarca(cocheInDto.getMarca());
//        coche.setModelo(cocheInDto.getModelo());
//        coche.setTipoCambio(cocheInDto.getTipoCambio());
//        coche.setKilometraje(cocheInDto.getKilometraje());
//        coche.setFechaCompra(cocheInDto.getFechaCompra());
//        coche.setPrecioCompra(cocheInDto.getPrecioCompra());
//        coche.setDisponible(cocheInDto.getDisponible());
        coche.setAutoescuela(autoescuela);

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

    public Coche modify(long id, CocheInDto cocheInDto, Autoescuela autoescuela) throws CocheNotFoundException {
        Coche coche = cocheRepository.findById(id)
                .orElseThrow(CocheNotFoundException::new);

        modelMapper.map(cocheInDto, coche);
//        coche.setMatricula(cocheInDto.getMatricula());
//        coche.setMarca(cocheInDto.getMarca());
//        coche.setModelo(cocheInDto.getModelo());
//        coche.setTipoCambio(cocheInDto.getTipoCambio());
//        coche.setKilometraje(cocheInDto.getKilometraje());
//        coche.setFechaCompra(cocheInDto.getFechaCompra());
//        coche.setPrecioCompra(cocheInDto.getPrecioCompra());
//        coche.setDisponible(cocheInDto.getDisponible());
        coche.setAutoescuela(autoescuela);
        coche.setId(id);

        return cocheRepository.save(coche);
    }

    }
