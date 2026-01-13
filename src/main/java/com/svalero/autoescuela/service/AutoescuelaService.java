package com.svalero.autoescuela.service;

import com.svalero.autoescuela.model.Autoescuela;
import com.svalero.autoescuela.repository.AutoescuelaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutoescuelaService {

    @Autowired
    private AutoescuelaRepository autoescuelaRepository;
    
    public void add(Autoescuela autoescuela){

    }

    public void delete(Autoescuela autoescuela){

    }

    public List<Autoescuela> findAll(){
        return null;
    }

    public Autoescuela findById(Long id){
        return null;
    }

    public void modify(Autoescuela autoescuela){

    }
}
