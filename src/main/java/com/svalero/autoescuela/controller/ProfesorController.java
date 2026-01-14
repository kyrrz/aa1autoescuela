package com.svalero.autoescuela.controller;


import com.svalero.autoescuela.exception.ErrorResponse;
import com.svalero.autoescuela.exception.ProfesorNotFoundException;
import com.svalero.autoescuela.model.Profesor;
import com.svalero.autoescuela.repository.ProfesorRepository;
import com.svalero.autoescuela.service.ProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProfesorController {

    @Autowired
    private ProfesorService profesorService;

    @GetMapping("/profesores")
    public ResponseEntity<List<Profesor>> getAll(@RequestParam(value = "especialidad", defaultValue = "") String e) {

        List<Profesor> profesores;

        if (!e.isEmpty()) {
            profesores = profesorService.findByEspecialidad(e);
        }else {
            profesores = profesorService.findAll();
        }
        return ResponseEntity.ok(profesores);
    }

    @GetMapping("/profesores/{id}")
    public ResponseEntity<Profesor> getProfesorById(@PathVariable long id) throws ProfesorNotFoundException{
        return ResponseEntity.ok(profesorService.findById(id));
    }


    @PostMapping("/profesores")
    public ResponseEntity<Profesor> addProfesor(@RequestBody Profesor profesor) {
        Profesor p = profesorService.add(profesor);
        return ResponseEntity.status(HttpStatus.CREATED).body(p);
    }

    @PutMapping("/profesores/{id}")
    public ResponseEntity<Profesor> modifyProfesor(@RequestBody Profesor profesor, @PathVariable long id) throws ProfesorNotFoundException {
        Profesor p = profesorService.modify(id, profesor);
        return ResponseEntity.ok(p);
    }

    @DeleteMapping("/profesores/{id}")
    public ResponseEntity<Void> deleteProfesor(@PathVariable long id) throws ProfesorNotFoundException {
        profesorService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(ProfesorNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(ProfesorNotFoundException pnfe){
        ErrorResponse errorResponse = new ErrorResponse(404, "not-found", "Profesor no encontrado");
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

}
