package com.svalero.autoescuela.controller;



import com.svalero.autoescuela.dto.AlumnoOutDto;
import com.svalero.autoescuela.dto.AutoescuelaInDto;
import com.svalero.autoescuela.dto.AutoescuelaOutDto;
import com.svalero.autoescuela.exception.AlumnoNotFoundException;
import com.svalero.autoescuela.exception.AutoescuelaNotFoundException;
import com.svalero.autoescuela.exception.ErrorResponse;
import com.svalero.autoescuela.model.Alumno;
import com.svalero.autoescuela.model.Autoescuela;
import com.svalero.autoescuela.service.AutoescuelaService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AutoescuelaController {

    @Autowired
    private AutoescuelaService autoescuelaService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/autoescuelas")
    public ResponseEntity<List<AutoescuelaOutDto>> getAll(@RequestParam(value = "ciudad", defaultValue = "") String c) {

        List<Autoescuela> autoescuelas;

        if (!c.isEmpty()) {
            autoescuelas = autoescuelaService.findByCiudad(c);
        }else {
            autoescuelas = autoescuelaService.findAll();
        }
        List<AutoescuelaOutDto> out = modelMapper.map(autoescuelas, new TypeToken<List<AutoescuelaOutDto>>() {}.getType()) ;
        return ResponseEntity.ok(out);
    }

    @GetMapping("/autoescuelas/{id}")
    public ResponseEntity<Autoescuela> getAutoescuelaById(@PathVariable long id) throws AutoescuelaNotFoundException {
        return ResponseEntity.ok(autoescuelaService.findById(id));
    }

    @PostMapping("/autoescuelas")
    public ResponseEntity<Autoescuela> addAutoescuela(@RequestBody Autoescuela autoescuela){
        Autoescuela a = autoescuelaService.add(autoescuela);
        return ResponseEntity.status(HttpStatus.CREATED).body(a);
    }

    @PutMapping("/autoescuelas/{id}")
    public ResponseEntity<Autoescuela> modifyAutoescuela(@RequestBody AutoescuelaInDto autoescuelaInDto, @PathVariable long id) throws AutoescuelaNotFoundException {
        Autoescuela a = autoescuelaService.modify(id, autoescuelaInDto);
        return ResponseEntity.ok(a);
    }

    @DeleteMapping("/autoescuelas/{id}")
    public ResponseEntity<Void> deleteAutoescuela(@PathVariable long id) throws AutoescuelaNotFoundException {
        autoescuelaService.delete(id);
        return ResponseEntity.noContent().build();
    }


    @ExceptionHandler(AutoescuelaNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(AutoescuelaNotFoundException anfe){
        ErrorResponse errorResponse = new ErrorResponse(404, "not-found", "Autoescuela no encontrada");
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
