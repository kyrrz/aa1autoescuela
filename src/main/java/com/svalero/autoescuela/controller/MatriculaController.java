package com.svalero.autoescuela.controller;

import com.svalero.autoescuela.model.Matricula;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MatriculaController {
    @GetMapping("/matricula")
    public List<Matricula> getAll(){
        return null;
    }

    @PostMapping("/matricula")
    public void addMatricula(@RequestBody Matricula matricula){

    }
}
