package com.svalero.autoescuela.controller;

import com.svalero.autoescuela.model.Coche;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CocheController {

    @GetMapping("/coche")
    public List<Coche> getAll(){
        return null;
    }

    @PostMapping("/coche")
    public void addCoche(@RequestBody Coche coche){

    }
}
