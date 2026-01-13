package com.svalero.autoescuela.controller;

import com.svalero.autoescuela.model.Alumno;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@RestController
public class AlumnoController {

    @GetMapping("/alumno")
    public List<Alumno> getAll(){
        return null;
    }

    @PostMapping("/alumno")
    public void addAlumno(@RequestBody Alumno alumno){
        
    }


}
