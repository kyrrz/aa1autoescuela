package com.svalero.autoescuela.controller;

import com.svalero.autoescuela.dto.AlumnoDetailOutDto;
import com.svalero.autoescuela.dto.AlumnoInDto;
import com.svalero.autoescuela.dto.AlumnoOutDto;
import com.svalero.autoescuela.dto.AutoescuelaDetailOutDto;
import com.svalero.autoescuela.exception.AlumnoNotFoundException;
import com.svalero.autoescuela.exception.AutoescuelaNotFoundException;
import com.svalero.autoescuela.exception.ErrorResponse;
import com.svalero.autoescuela.model.Alumno;
import com.svalero.autoescuela.model.Autoescuela;
import com.svalero.autoescuela.service.AlumnoService;
import com.svalero.autoescuela.service.AutoescuelaService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class AlumnoController {

    @Autowired
    private AlumnoService alumnoService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private AutoescuelaService autoescuelaService;

    @GetMapping("/alumnos")
    public ResponseEntity<List<AlumnoOutDto>> getAll(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String ciudad,
            @RequestParam(required = false) Boolean usaGafas,
            @RequestParam(required = false) Float notaTeorico
    ) {
        List<AlumnoOutDto> alumnoOutDtos = alumnoService.findByFiltros(nombre, ciudad, usaGafas, notaTeorico);
        return ResponseEntity.ok(alumnoOutDtos);
    }

    @GetMapping("/alumnos/{id}")
    public ResponseEntity<AlumnoDetailOutDto> getAlumnoById(@PathVariable long id) throws AlumnoNotFoundException{
        return ResponseEntity.ok(alumnoService.findById(id));
    }


    @PostMapping("/alumnos")
    public ResponseEntity<AlumnoDetailOutDto> addAlumno(@Valid @RequestBody AlumnoInDto alumnoInDto) throws AutoescuelaNotFoundException {

        AutoescuelaDetailOutDto autoescuelaDetailOutDto = autoescuelaService.findById(alumnoInDto.getAutoescuelaId());
        AlumnoDetailOutDto nuevoAlumno = alumnoService.add(alumnoInDto, autoescuelaDetailOutDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoAlumno);
    }

    @PutMapping("/alumnos/{id}")
    public ResponseEntity<AlumnoDetailOutDto> modifyAlumno(@Valid @RequestBody AlumnoInDto alumnoInDto, @PathVariable long id) throws AlumnoNotFoundException, AutoescuelaNotFoundException {
        System.out.println("==============================================");
        System.out.println("autoescuelaId = " + alumnoInDto.getAutoescuelaId());
        System.out.println("autoescuelaDetailOutDto = " + autoescuelaService.findById(alumnoInDto.getAutoescuelaId()));
        System.out.println("==========================================");
        AutoescuelaDetailOutDto autoescuelaDetailOutDto = autoescuelaService.findById(alumnoInDto.getAutoescuelaId());
        System.out.println(autoescuelaDetailOutDto);
        AlumnoDetailOutDto alumnoUpdated = alumnoService.modify(id, alumnoInDto, autoescuelaDetailOutDto);

        return ResponseEntity.ok(alumnoUpdated);
    }

    @DeleteMapping("/alumnos/{id}")
    public ResponseEntity<Void> deleteAlumno(@PathVariable long id) throws AlumnoNotFoundException {
        alumnoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(AlumnoNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(AlumnoNotFoundException anfe){
        ErrorResponse errorResponse = ErrorResponse.notFound("Alumno no encontrado");
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AutoescuelaNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(AutoescuelaNotFoundException anfe){
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
