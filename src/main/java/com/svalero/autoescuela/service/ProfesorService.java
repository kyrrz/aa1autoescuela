package com.svalero.autoescuela.service;

import com.svalero.autoescuela.model.Profesor;
import com.svalero.autoescuela.repository.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfesorService {

    @Autowired
    private ProfesorRepository profesorRepository;
    
    public void add(Profesor profesor){

    }

    public void delete(Profesor profesor){

    }

    public List<Profesor> findAll(){
        return null;
    }

    public Profesor findById(Long id){
        return null;
    }

    public void modify(Profesor profesor){

    }
}
