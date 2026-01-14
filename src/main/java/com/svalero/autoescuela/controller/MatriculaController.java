package com.svalero.autoescuela.controller;

import com.svalero.autoescuela.exception.AlumnoNotFoundException;
import com.svalero.autoescuela.exception.ErrorResponse;
import com.svalero.autoescuela.exception.MatriculaNotFoundException;
import com.svalero.autoescuela.model.Matricula;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @ExceptionHandler(MatriculaNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(MatriculaNotFoundException anfe){
        ErrorResponse errorResponse = new ErrorResponse(404, "not-found", "Matricula no encontrada");
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
