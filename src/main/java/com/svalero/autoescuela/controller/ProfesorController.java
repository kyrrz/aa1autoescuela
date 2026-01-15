package com.svalero.autoescuela.controller;


import com.svalero.autoescuela.dto.AutoescuelaDetailOutDto;
import com.svalero.autoescuela.dto.ProfesorDetailOutDto;
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
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        List<ProfesorOutDto> pod  = profesorService.findByFilters(nombre,especialidad,activo);

        return ResponseEntity.ok(pod);
    }

    @GetMapping("/profesores/{id}")
    public ResponseEntity<ProfesorDetailOutDto> getProfesorById(@PathVariable long id) throws ProfesorNotFoundException{
        return ResponseEntity.ok(profesorService.findById(id));
    }


    @PostMapping("/profesores")
    public ResponseEntity<ProfesorDetailOutDto> addProfesor(@Valid @RequestBody ProfesorInDto profesorInDto) throws AutoescuelaNotFoundException {
        List<AutoescuelaDetailOutDto> autoescuelasDetailDtos = autoescuelaService.findAllById(profesorInDto.getAutoescuelaId());
        ProfesorDetailOutDto pdod = profesorService.add(profesorInDto,  autoescuelasDetailDtos);
        return ResponseEntity.status(HttpStatus.CREATED).body(pdod);
    }

    @PutMapping("/profesores/{id}")
    public ResponseEntity<ProfesorDetailOutDto> modifyProfesor(@Valid  @RequestBody ProfesorInDto profesorInDto, @PathVariable long id) throws ProfesorNotFoundException, AutoescuelaNotFoundException {
        List<AutoescuelaDetailOutDto> autoescuelasDetailDtos = autoescuelaService.findAllById(profesorInDto.getAutoescuelaId());
        ProfesorDetailOutDto pdod = profesorService.modify(id, profesorInDto, autoescuelasDetailDtos);
        return ResponseEntity.ok(pdod);
    }

    @DeleteMapping("/profesores/{id}")
    public ResponseEntity<Void> deleteProfesor(@PathVariable long id) throws ProfesorNotFoundException {
        profesorService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(ProfesorNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(ProfesorNotFoundException pnfe){
        ErrorResponse errorResponse = ErrorResponse.notFound("Profesor no encontrado");
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AutoescuelaNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(AutoescuelaNotFoundException aunfe){
        ErrorResponse errorResponse = ErrorResponse.notFound("Autoescuela no encontrada");
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException manve){
        Map<String,String> errors = new HashMap<>();
        manve.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName,message);
        });
        ErrorResponse errorResponse = ErrorResponse.validationError(errors);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }


}
