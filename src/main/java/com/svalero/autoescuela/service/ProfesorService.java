package com.svalero.autoescuela.service;

import com.svalero.autoescuela.exception.ProfesorNotFoundException;
import com.svalero.autoescuela.model.Profesor;
import com.svalero.autoescuela.repository.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfesorService {

    @Autowired
    private ProfesorRepository profesorRepository;


    public Profesor add(Profesor profesor) {
        return profesorRepository.save(profesor);
    }

    public void delete( long id ) throws ProfesorNotFoundException {
        Profesor p = profesorRepository.findById(id)
                .orElseThrow(ProfesorNotFoundException::new);
        profesorRepository.delete(p);
    }

    public List<Profesor> findAll(){
        return profesorRepository.findAll();
    }


    public List<Profesor> findByEspecialidad(String especialidad){
        return profesorRepository.findByEspecialidad(especialidad);
    }

    public Profesor findById(long id) throws ProfesorNotFoundException{
        return profesorRepository.findById(id)
                .orElseThrow(ProfesorNotFoundException::new);
    }

    public Profesor modify(long id, Profesor profesor) throws ProfesorNotFoundException {
        Profesor oldProfesor = profesorRepository.findById(id)
                .orElseThrow(ProfesorNotFoundException::new);

        oldProfesor.setNombre(profesor.getNombre());
        oldProfesor.setApellidos(profesor.getApellidos());
        oldProfesor.setDni(profesor.getDni());
        oldProfesor.setTelefono(profesor.getTelefono());
        oldProfesor.setSalario(profesor.getSalario());
        oldProfesor.setFechaContratacion(profesor.getFechaContratacion());
        oldProfesor.setEspecialidad(profesor.getEspecialidad());
        oldProfesor.setActivo(profesor.isActivo());


        return profesorRepository.save(oldProfesor);
    }}
