package com.svalero.autoescuela.controller;

import com.svalero.autoescuela.exception.AlumnoNotFoundException;
import com.svalero.autoescuela.exception.CocheNotFoundException;
import com.svalero.autoescuela.exception.ErrorResponse;
import com.svalero.autoescuela.model.Alumno;
import com.svalero.autoescuela.model.Coche;
import com.svalero.autoescuela.service.CocheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CocheController {

    @Autowired
    private CocheService cocheService;

    @GetMapping("/coches")
    public ResponseEntity<List<Coche>> getAll(@RequestParam(value = "marca", defaultValue = "") String m) {

        List<Coche> coches;

        if (!m.isEmpty()) {
            coches = cocheService.findByMarca(m);
        }else {
            coches = cocheService.findAll();
        }
        return ResponseEntity.ok(coches);
    }

    @GetMapping("/coches/{id}")
    public ResponseEntity<Coche> getCocheById(@PathVariable long id) throws CocheNotFoundException {
        return ResponseEntity.ok(cocheService.findById(id));
    }



    @PostMapping("/coches")
    public ResponseEntity<Coche> addCoche(@RequestBody Coche coche){
        Coche c = cocheService.add(coche);
        return ResponseEntity.status(HttpStatus.CREATED).body(c);
    }

    @PutMapping("/coches/{id}")
    public ResponseEntity<Coche> modifyCoche(@RequestBody Coche coche, @PathVariable long id) throws CocheNotFoundException {
        Coche c = cocheService.modify(id, coche);
        return ResponseEntity.ok(c);
    }

    @DeleteMapping("/coches/{id}")
    public ResponseEntity<Void> deleteCoche(@PathVariable long id) throws CocheNotFoundException {
        cocheService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(CocheNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(CocheNotFoundException cnfe){
        ErrorResponse errorResponse = new ErrorResponse(404, "not-found", "Coche no encontrado");
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
