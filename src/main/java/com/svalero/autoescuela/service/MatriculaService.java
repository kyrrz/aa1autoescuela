package com.svalero.autoescuela.service;

import com.svalero.autoescuela.model.Matricula;
import com.svalero.autoescuela.repository.MatriculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatriculaService {

    @Autowired
    private MatriculaRepository matriculaRepository;
    
    public void add(Matricula matricula){

    }

    public void delete(Matricula matricula){

    }

    public List<Matricula> findAll(){
        return null;
    }

    public Matricula findById(Long id){
        return null;
    }

    public void modify(Matricula matricula){

    }
}
