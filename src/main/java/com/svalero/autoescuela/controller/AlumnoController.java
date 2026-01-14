package com.svalero.autoescuela.controller;

import com.svalero.autoescuela.exception.AlumnoNotFoundException;
import com.svalero.autoescuela.exception.ErrorResponse;
import com.svalero.autoescuela.model.Alumno;
import com.svalero.autoescuela.service.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class AlumnoController {

    @Autowired
    private AlumnoService alumnoService;


    @GetMapping("/alumnos")
    public ResponseEntity<List<Alumno>> getAll(@RequestParam(value = "ciudad", defaultValue = "") String c) {

        List<Alumno> alumnos;

        if (!c.isEmpty()) {
            alumnos = alumnoService.findByCiudad(c);
        }else {
        alumnos = alumnoService.findAll();
        }
        return ResponseEntity.ok(alumnos);
    }

    @GetMapping("/alumnos/{id}")
    public ResponseEntity<Alumno> getAlumnoById(@PathVariable long id) throws AlumnoNotFoundException{
        return ResponseEntity.ok(alumnoService.findById(id));
    }



    @PostMapping("/alumnos")
    public ResponseEntity<Alumno> addAlumno(@RequestBody Alumno alumno){
        Alumno a = alumnoService.add(alumno);
        return ResponseEntity.status(HttpStatus.CREATED).body(a);
    }

    @PutMapping("/alumnos/{id}")
    public ResponseEntity<Alumno> modifyAlumno(@RequestBody Alumno alumno, @PathVariable long id) throws AlumnoNotFoundException {
        Alumno a = alumnoService.modify(id, alumno);
        return ResponseEntity.ok(a);
    }

    @DeleteMapping("/alumnos/{id}")
    public ResponseEntity<Void> deleteAlumno(@PathVariable long id) throws AlumnoNotFoundException {
        alumnoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(AlumnoNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(AlumnoNotFoundException anfe){
        ErrorResponse errorResponse = new ErrorResponse(404, "not-found", "Alumno no encontrado");
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

}
