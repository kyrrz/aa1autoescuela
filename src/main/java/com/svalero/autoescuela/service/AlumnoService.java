package com.svalero.autoescuela.service;

import com.svalero.autoescuela.model.Alumno;
import com.svalero.autoescuela.repository.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlumnoService {

    @Autowired
    private AlumnoRepository alumnoRepository;

    public void add(Alumno alumno){

    }

    public void delete(Alumno alumno){

    }

    public List<Alumno> findAll(){
        return null;
    }

    public Alumno findById(Long id){
        return null;
    }

    public void modify(Alumno alumno){

    }

}
