package com.svalero.autoescuela.service;


import com.svalero.autoescuela.dto.AutoescuelaDetailOutDto;
import com.svalero.autoescuela.dto.CocheDetailOutDto;
import com.svalero.autoescuela.dto.CocheInDto;
import com.svalero.autoescuela.dto.CocheOutDto;
import com.svalero.autoescuela.exception.AutoescuelaNotFoundException;
import com.svalero.autoescuela.exception.CocheNotFoundException;
import com.svalero.autoescuela.model.Autoescuela;
import com.svalero.autoescuela.model.Coche;
import com.svalero.autoescuela.repository.AutoescuelaRepository;
import com.svalero.autoescuela.repository.CocheRepository;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CocheService {

    @Autowired
    private CocheRepository cocheRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private AutoescuelaRepository autoescuelaRepository;

    public CocheDetailOutDto add(CocheInDto cocheInDto, AutoescuelaDetailOutDto autoescuelaDetailOutDto) throws AutoescuelaNotFoundException {
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
        Autoescuela autoescuela = autoescuelaRepository.findById(autoescuelaDetailOutDto.getId())
                .orElseThrow(AutoescuelaNotFoundException::new);
        coche.setAutoescuela(autoescuela);

        Coche savedCoche = cocheRepository.save(coche);
        return modelMapper.map(savedCoche, CocheDetailOutDto.class);
    }


    public List<CocheOutDto> findByFiltros(String marca, String modelo, String tipoCambio){

        if (marca != null && modelo != null && tipoCambio != null) {
            List<Coche> coches = cocheRepository.findByMarcaAndModeloAndTipoCambio(marca, modelo, tipoCambio);
            List<CocheOutDto> cocheOutDtos = modelMapper.map(coches, new TypeToken<List<CocheOutDto>>() {}.getType());
            return cocheOutDtos;
        }

        if (modelo != null && tipoCambio != null) {
            List<Coche> coches = cocheRepository.findByModeloAndTipoCambio(modelo, tipoCambio);
            List<CocheOutDto> cocheOutDtos = modelMapper.map(coches, new TypeToken<List<CocheOutDto>>() {}.getType());
            return cocheOutDtos;
        }
        if (marca != null && tipoCambio != null) {
            List<Coche> coches = cocheRepository.findByMarcaAndTipoCambio(marca, tipoCambio);
            List<CocheOutDto> cocheOutDtos = modelMapper.map(coches, new TypeToken<List<CocheOutDto>>() {}.getType());
            return cocheOutDtos;
        }
        if (modelo != null && marca != null) {
            List<Coche> coches = cocheRepository.findByMarcaAndModelo(marca, modelo);
            List<CocheOutDto> cocheOutDtos = modelMapper.map(coches, new TypeToken<List<CocheOutDto>>() {}.getType());
            return cocheOutDtos;
        }

        if (tipoCambio != null) {
            List<Coche> coches = cocheRepository.findByTipoCambio(tipoCambio);
            List<CocheOutDto> cocheOutDtos = modelMapper.map(coches, new TypeToken<List<CocheOutDto>>() {}.getType());
            return cocheOutDtos;
        }
        if (modelo != null) {
            List<Coche> coches = cocheRepository.findByModelo(modelo);
            List<CocheOutDto> cocheOutDtos = modelMapper.map(coches, new TypeToken<List<CocheOutDto>>() {}.getType());
            return cocheOutDtos;
        }
        if (marca != null) {
            List<Coche> coches = cocheRepository.findByMarca(marca);
            List<CocheOutDto> cocheOutDtos = modelMapper.map(coches, new TypeToken<List<CocheOutDto>>() {}.getType());
            return cocheOutDtos;
        }

        List<Coche>  coches = cocheRepository.findAll();
        List<CocheOutDto> cocheOutDtos = modelMapper.map(coches, new TypeToken<List<CocheOutDto>>() {}.getType());
        return cocheOutDtos;
    }

    public void delete( long id ) throws CocheNotFoundException {
        Coche c = cocheRepository.findById(id)
                .orElseThrow(CocheNotFoundException::new);
        cocheRepository.delete(c);
    }

    public List<Coche> findAll(){
        return cocheRepository.findAll();
    }

    public CocheDetailOutDto findById(long id) throws CocheNotFoundException{
        return cocheRepository.findById(id).map(coche -> modelMapper.map(coche, CocheDetailOutDto.class))
                .orElseThrow(CocheNotFoundException::new);
    }

    public CocheDetailOutDto modify(long id, CocheInDto cocheInDto, AutoescuelaDetailOutDto autoescuelaDetailOutDto) throws AutoescuelaNotFoundException, CocheNotFoundException {
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
        Autoescuela autoescuela = autoescuelaRepository.findById(autoescuelaDetailOutDto.getId()).orElseThrow(AutoescuelaNotFoundException::new);
        coche.setAutoescuela(autoescuela);
        coche.setId(id);

        Coche savedCoche = cocheRepository.save(coche);

        return modelMapper.map(savedCoche, CocheDetailOutDto.class);
    }

    }
