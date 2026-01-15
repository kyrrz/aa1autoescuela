package com.svalero.autoescuela.controller;


import com.svalero.autoescuela.dto.ProfesorInDto;
import com.svalero.autoescuela.dto.ProfesorOutDto;
import com.svalero.autoescuela.exception.AutoescuelaNotFoundException;
import com.svalero.autoescuela.exception.ErrorResponse;
import com.svalero.autoescuela.exception.ProfesorNotFoundException;
import com.svalero.autoescuela.model.Autoescuela;
import com.svalero.autoescuela.model.Profesor;
import com.svalero.autoescuela.repository.ProfesorRepository;
import com.svalero.autoescuela.service.AutoescuelaService;
import com.svalero.autoescuela.service.ProfesorService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProfesorController {

    @Autowired
    private ProfesorService profesorService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private AutoescuelaService autoescuelaService;

    @GetMapping("/profesores")
    public ResponseEntity<List<ProfesorOutDto>> getAll(
            @RequestParam(required = false) String especialidad,
            @RequestParam(required = false) Boolean activo,
            @RequestParam(required = false) String nombre
            ) {
        List<Profesor> profesores = profesorService.findByFilters(nombre,especialidad,activo);
        List<ProfesorOutDto> pod = modelMapper.map(profesores, new  TypeToken<List<ProfesorOutDto>>() {}.getType());

        return ResponseEntity.ok(pod);
    }

    @GetMapping("/profesores/{id}")
    public ResponseEntity<Profesor> getProfesorById(@PathVariable long id) throws ProfesorNotFoundException{
        return ResponseEntity.ok(profesorService.findById(id));
    }


    @PostMapping("/profesores")
    public ResponseEntity<Profesor> addProfesor(@RequestBody ProfesorInDto profesorInDto) throws AutoescuelaNotFoundException {
        List<Autoescuela> autoescuelas = autoescuelaService.findAllById(profesorInDto.getAutoescuelaId());
        Profesor p = profesorService.add(profesorInDto,  autoescuelas);
        return ResponseEntity.status(HttpStatus.CREATED).body(p);
    }

    @PutMapping("/profesores/{id}")
    public ResponseEntity<Profesor> modifyProfesor(@RequestBody ProfesorInDto profesorInDto, @PathVariable long id) throws ProfesorNotFoundException, AutoescuelaNotFoundException {
        List<Autoescuela> autoescuelas = autoescuelaService.findAllById(profesorInDto.getAutoescuelaId());
        Profesor p = profesorService.modify(id, profesorInDto, autoescuelas);
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
