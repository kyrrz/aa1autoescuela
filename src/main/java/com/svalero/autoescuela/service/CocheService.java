package com.svalero.autoescuela.service;

import com.svalero.autoescuela.model.Coche;
import com.svalero.autoescuela.repository.CocheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CocheService {

    @Autowired
    private CocheRepository cocheRepository;
    
    public void add(Coche coche){

    }

    public void delete(Coche coche){

    }

    public List<Coche> findAll(){
        return null;
    }

    public Coche findById(Long id){
        return null;
    }

    public void modify(Coche coche){

    }
}
