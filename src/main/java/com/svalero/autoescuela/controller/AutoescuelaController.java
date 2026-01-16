package com.svalero.autoescuela.controller;



import com.svalero.autoescuela.dto.AlumnoOutDto;
import com.svalero.autoescuela.dto.AutoescuelaDetailOutDto;
import com.svalero.autoescuela.dto.AutoescuelaInDto;
import com.svalero.autoescuela.dto.AutoescuelaOutDto;
import com.svalero.autoescuela.exception.AlumnoNotFoundException;
import com.svalero.autoescuela.exception.AutoescuelaNotFoundException;
import com.svalero.autoescuela.exception.ErrorResponse;
import com.svalero.autoescuela.model.Alumno;
import com.svalero.autoescuela.model.Autoescuela;
import com.svalero.autoescuela.service.AutoescuelaService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
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
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("")
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

    @PatchMapping("/{id}")
    public ResponseEntity<AutoescuelaDetailOutDto> patchAutoescuela(@PathVariable Long id, @RequestBody Map<String, Object> patch) throws AutoescuelaNotFoundException {
        AutoescuelaDetailOutDto autoescuelaPatch = autoescuelaService.patch(id, patch);
        return ResponseEntity.ok(autoescuelaPatch);
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
