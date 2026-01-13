package com.svalero.autoescuela.controller;

import com.svalero.autoescuela.model.Profesor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProfesorController {
    @GetMapping("/profesor")
    public List<Profesor> getAll(){
        return null;
    }

    @PostMapping("/profesor")
    public void addProfesor(@RequestBody Profesor profesor){

    }
}
