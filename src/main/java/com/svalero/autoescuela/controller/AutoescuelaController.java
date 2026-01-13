package com.svalero.autoescuela.controller;


import com.svalero.autoescuela.model.Autoescuela;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AutoescuelaController {
    @GetMapping("/autoescuela")
    public List<Autoescuela> getAll(){
        return null;
    }

    @PostMapping("/autoescuela")
    public void addAutoescuela(@RequestBody Autoescuela autoescuela){

    }
}
