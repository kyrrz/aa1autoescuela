package com.svalero.autoescuela.service;

import com.svalero.autoescuela.dto.*;
import com.svalero.autoescuela.exception.AutoescuelaNotFoundException;
import com.svalero.autoescuela.exception.MatriculaNotFoundException;
import com.svalero.autoescuela.exception.ProfesorNotFoundException;
import com.svalero.autoescuela.model.Autoescuela;
import com.svalero.autoescuela.model.Profesor;
import com.svalero.autoescuela.repository.AutoescuelaRepository;
import com.svalero.autoescuela.repository.ProfesorRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfesorService {

    @Autowired
    private ProfesorRepository profesorRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private AutoescuelaRepository autoescuelaRepository;


    public ProfesorDetailOutDto add(ProfesorInDto profesorInDto, List<AutoescuelaDetailOutDto> autoescuelaDetailOutDtos) throws AutoescuelaNotFoundException {

        Profesor p = modelMapper.map(profesorInDto, Profesor.class);
//        p.setNombre(profesorInDto.getNombre());
//        p.setApellidos(profesorInDto.getApellidos());
//        p.setDni(profesorInDto.getDni());
//        p.setTelefono(profesorInDto.getTelefono());
//        p.setSalario(profesorInDto.getSalario());
//        p.setFechaContratacion(profesorInDto.getFechaContratacion());
//        p.setEspecialidad(profesorInDto.getEspecialidad());
//        p.setActivo(profesorInDto.isActivo());
        List<Long> autoescuelasIds = autoescuelaDetailOutDtos.stream()
                .map(AutoescuelaDetailOutDto::getId)
                .toList();

        List<Autoescuela> autoescuelas = (List<Autoescuela>) autoescuelaRepository.findAllById(autoescuelasIds);
        p.setAutoescuelas(autoescuelas);

        Profesor profesor = profesorRepository.save(p);
        return modelMapper.map(profesor, ProfesorDetailOutDto.class);
    }

    public void delete( long id ) throws ProfesorNotFoundException {
        Profesor p = profesorRepository.findById(id)
                .orElseThrow(ProfesorNotFoundException::new);
        profesorRepository.delete(p);
    }

    public List<Profesor> findAll(){
        return profesorRepository.findAll();
    }

    public List<ProfesorOutDto> findByFilters(String nombre, String especialidad, Boolean activo){

        if (nombre != null && especialidad != null && activo != null) {
            List<Profesor> profesors = profesorRepository.findByNombreAndEspecialidadAndActivo(nombre, especialidad, activo);
            List<ProfesorOutDto> profesorsDto = modelMapper.map(profesors, new TypeToken<List<ProfesorOutDto>>() {}.getType());

            return profesorsDto;
        }
        if ( especialidad != null && activo != null) {
            List<Profesor> profesors = profesorRepository.findByActivoAndEspecialidad(activo, especialidad);
            List<ProfesorOutDto> profesorsDto = modelMapper.map(profesors, new TypeToken<List<ProfesorOutDto>>() {}.getType());

            return profesorsDto;
        }
        if ( nombre != null && activo != null) {
            List<Profesor> profesors = profesorRepository.findByNombreAndActivo(nombre, activo);
            List<ProfesorOutDto> profesorsDto = modelMapper.map(profesors, new TypeToken<List<ProfesorOutDto>>() {}.getType());

            return profesorsDto;
        }
        if ( nombre != null && especialidad != null) {
            List<Profesor> profesors = profesorRepository.findByNombreAndEspecialidad(nombre, especialidad);
            List<ProfesorOutDto> profesorsDto = modelMapper.map(profesors, new TypeToken<List<ProfesorOutDto>>() {}.getType());

            return profesorsDto;
        }

        if ( activo != null ) {
            List<Profesor> profesors = profesorRepository.findByActivo(activo);
            List<ProfesorOutDto> profesorsDto = modelMapper.map(profesors, new TypeToken<List<ProfesorOutDto>>() {}.getType());

            return profesorsDto;
        }
        if ( nombre != null ) {
            List<Profesor> profesors = profesorRepository.findByNombre(nombre);
            List<ProfesorOutDto> profesorsDto = modelMapper.map(profesors, new TypeToken<List<ProfesorOutDto>>() {}.getType());

            return profesorsDto;
        }
        if ( especialidad != null ) {
            List<Profesor> profesors = profesorRepository.findByEspecialidad(especialidad);
            List<ProfesorOutDto> profesorsDto = modelMapper.map(profesors, new TypeToken<List<ProfesorOutDto>>() {}.getType());

            return profesorsDto;
        }

        List<Profesor> profesors = profesorRepository.findAll();
        List<ProfesorOutDto> profesorsDto = modelMapper.map(profesors, new TypeToken<List<ProfesorOutDto>>() {}.getType());

        return profesorsDto;
    }

    public ProfesorDetailOutDto findById(long id) throws ProfesorNotFoundException{
        return profesorRepository.findById(id)
                .map(profesor -> modelMapper.map(profesor, ProfesorDetailOutDto.class))
                .orElseThrow(ProfesorNotFoundException::new);
    }

    public ProfesorDetailOutDto modify(long id, ProfesorInDto profesorInDto, List<AutoescuelaDetailOutDto> autoescuelaDetailOutDtos) throws ProfesorNotFoundException {
        Profesor p = profesorRepository.findById(id)
                .orElseThrow(ProfesorNotFoundException::new);
        modelMapper.map(profesorInDto, p);
//        p.setNombre(profesorInDto.getNombre());
//        p.setApellidos(profesorInDto.getApellidos());
//        p.setDni(profesorInDto.getDni());
//        p.setTelefono(profesorInDto.getTelefono());
//        p.setSalario(profesorInDto.getSalario());
//        p.setFechaContratacion(profesorInDto.getFechaContratacion());
//        p.setEspecialidad(profesorInDto.getEspecialidad());
//        p.setActivo(profesorInDto.isActivo());
        p.setId(id);
        List<Long> autoescuelasIds = autoescuelaDetailOutDtos.stream()
                .map(AutoescuelaDetailOutDto::getId)
                .toList();

        List<Autoescuela> autoescuelas = (List<Autoescuela>) autoescuelaRepository.findAllById(autoescuelasIds);
        p.setAutoescuelas(autoescuelas);

        Profesor profesor = profesorRepository.save(p);
        return modelMapper.map(profesor, ProfesorDetailOutDto.class);
    }}
