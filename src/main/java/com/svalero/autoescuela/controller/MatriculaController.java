package com.svalero.autoescuela.controller;

import com.svalero.autoescuela.dto.MatriculaInDto;
import com.svalero.autoescuela.dto.MatriculaOutDto;
import com.svalero.autoescuela.exception.*;
import com.svalero.autoescuela.model.*;
import com.svalero.autoescuela.service.AlumnoService;
import com.svalero.autoescuela.service.AutoescuelaService;
import com.svalero.autoescuela.service.MatriculaService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MatriculaController {
    @Autowired
    private MatriculaService matriculaService;
    @Autowired
    private AlumnoService alumnoService;
    @Autowired
    private AutoescuelaService autoescuelaService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/matriculas")
    public ResponseEntity<List<MatriculaOutDto>> getAll(@RequestParam(value = "modalidad", defaultValue = "") String m) {
        List<Matricula> matriculas;
        if (!m.isEmpty()) {
            matriculas = matriculaService.findByModalidad(m);
        }else {
            matriculas = matriculaService.findAll();
        }

        List<MatriculaOutDto> mod = modelMapper.map(matriculas, new TypeToken<List<MatriculaOutDto>>() {}.getType());
        return new ResponseEntity<>(mod, HttpStatus.OK);
    }
    @GetMapping("/matriculas/{id}")
    public ResponseEntity<Matricula> getMatriculaById(@PathVariable long id) throws MatriculaNotFoundException {
        return ResponseEntity.ok(matriculaService.findById(id));
    }

    @PostMapping("/matriculas")
    public ResponseEntity<Matricula> addMatricula(@RequestBody MatriculaInDto matriculaInDto) throws AlumnoNotFoundException, AutoescuelaNotFoundException {
        Alumno alumno = alumnoService.findById(matriculaInDto.getAlumnoId());
        Autoescuela autoescuela = autoescuelaService.findById(matriculaInDto.getAutoescuelaId());
        Matricula m = matriculaService.add(matriculaInDto, alumno, autoescuela);
        return ResponseEntity.status(HttpStatus.CREATED).body(m);
    }


    @PutMapping("/matriculas/{id}")
    public ResponseEntity<Matricula> modifyMatricula(@RequestBody MatriculaInDto matriculaInDto, @PathVariable long id) throws MatriculaNotFoundException,AlumnoNotFoundException, AutoescuelaNotFoundException  {
        Alumno alumno = alumnoService.findById(matriculaInDto.getAlumnoId());
        Autoescuela autoescuela = autoescuelaService.findById(matriculaInDto.getAutoescuelaId());
        Matricula m = matriculaService.modify(id, matriculaInDto, alumno, autoescuela);
        return ResponseEntity.ok(m);
    }

    @DeleteMapping("/matriculas/{id}")
    public ResponseEntity<Void> deleteMatricula(@PathVariable long id) throws MatriculaNotFoundException {
        matriculaService.delete(id);
        return ResponseEntity.noContent().build();
    }
    @ExceptionHandler(MatriculaNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(MatriculaNotFoundException anfe){
        ErrorResponse errorResponse = new ErrorResponse(404, "not-found", "Matricula no encontrada");
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(AlumnoNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(AlumnoNotFoundException anfe){
        ErrorResponse errorResponse = new ErrorResponse(404, "not-found", "Alumno no encontrado");
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(AutoescuelaNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(AutoescuelaNotFoundException anfe){
        ErrorResponse errorResponse = new ErrorResponse(404, "not-found", "Autoescuela no encontrada");
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
