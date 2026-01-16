package com.svalero.autoescuela.controller;



import com.svalero.autoescuela.dto.*;
import com.svalero.autoescuela.exception.*;
import com.svalero.autoescuela.model.Alumno;
import com.svalero.autoescuela.model.Autoescuela;
import com.svalero.autoescuela.repository.AlumnoRepository;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/autoescuelas")
public class AutoescuelaController {

    @Autowired
    private AutoescuelaService autoescuelaService;


    @GetMapping
    public ResponseEntity<List<AutoescuelaOutDto>> getAll(
            @RequestParam(required = false) String ciudad,
            @RequestParam(required = false) String ratingG,
            @RequestParam(required = false) Boolean activa
    ) {

        List<AutoescuelaOutDto> autoescuelaOutDtos = autoescuelaService.findByFiltros(ciudad, ratingG, activa);
        return ResponseEntity.ok(autoescuelaOutDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AutoescuelaDetailOutDto> getAutoescuelaById(@PathVariable long id) throws AutoescuelaNotFoundException {
        return ResponseEntity.ok(autoescuelaService.findById(id));
    }

    @GetMapping("/{id}/profesores")
    public ResponseEntity<List<ProfesorOutDto>> getProfesoresByAutoescuelaId(@PathVariable long id) {
        List<ProfesorOutDto> profesores = autoescuelaService.getProfesores(id);

        return ResponseEntity.ok(profesores);
    }

    @GetMapping("/{id}/coches")
    public ResponseEntity<List<CocheOutDto>> getCochesByAutoescuelaId(@PathVariable long id) {
        List<CocheOutDto> coches = autoescuelaService.getCoches(id);

        return ResponseEntity.ok(coches);
    }

    @GetMapping("/{id}/matriculas")
    public ResponseEntity<List<MatriculaOutDto>> getMatriculasByAutoescuelaId(@PathVariable long id) throws AutoescuelaNotFoundException {
        List<MatriculaOutDto> matricula = autoescuelaService.getMatriculas(id);

        return ResponseEntity.ok(matricula);
    }

    @GetMapping("/{id}/matriculas/completas")
    public ResponseEntity<List<MatriculaOutDto>> getMatriculasCompletas(@PathVariable Long id) throws AutoescuelaNotFoundException {

        return ResponseEntity.ok(
                autoescuelaService.getMatriculasCompletas(id)
        );
    }
    @GetMapping("/{id}/alumnos/suspensos")
    public ResponseEntity<List<AlumnoOutDto>> getAlumnosSuspensosByAutoescuelaId(@PathVariable long id)  {
        return ResponseEntity.ok(autoescuelaService.getAlumnosSuspensos(id));
    }




    @PostMapping("")
    public ResponseEntity<AutoescuelaDetailOutDto> addAutoescuela(@Valid  @RequestBody AutoescuelaInDto autoescuelaInDto){
        AutoescuelaDetailOutDto a = autoescuelaService.add(autoescuelaInDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(a);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AutoescuelaDetailOutDto> modifyAutoescuela(@Valid @RequestBody AutoescuelaInDto autoescuelaInDto, @PathVariable long id) throws AutoescuelaNotFoundException {
        AutoescuelaDetailOutDto a = autoescuelaService.modify(id, autoescuelaInDto);
        return ResponseEntity.ok(a);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAutoescuela(@PathVariable long id) throws AutoescuelaNotFoundException {
        autoescuelaService.delete(id);
        return ResponseEntity.noContent().build();
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
