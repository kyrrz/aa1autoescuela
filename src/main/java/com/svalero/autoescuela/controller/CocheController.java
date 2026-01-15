package com.svalero.autoescuela.controller;

import com.svalero.autoescuela.dto.CocheInDto;
import com.svalero.autoescuela.dto.CocheOutDto;
import com.svalero.autoescuela.exception.AlumnoNotFoundException;
import com.svalero.autoescuela.exception.AutoescuelaNotFoundException;
import com.svalero.autoescuela.exception.CocheNotFoundException;
import com.svalero.autoescuela.exception.ErrorResponse;
import com.svalero.autoescuela.model.Alumno;
import com.svalero.autoescuela.model.Autoescuela;
import com.svalero.autoescuela.model.Coche;
import com.svalero.autoescuela.service.AutoescuelaService;
import com.svalero.autoescuela.service.CocheService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CocheController {

    @Autowired
    private CocheService cocheService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private AutoescuelaService autoescuelaService;

    @GetMapping("/coches")
    public ResponseEntity<List<CocheOutDto>> getAll(@RequestParam(value = "marca", defaultValue = "") String m) {

        List<Coche> coches;

        if (!m.isEmpty()) {
            coches = cocheService.findByMarca(m);
        }else {
            coches = cocheService.findAll();
        }
        List<CocheOutDto> cod = modelMapper.map(coches, new  TypeToken<List<CocheOutDto>>() {}.getType());
        return ResponseEntity.ok(cod);
    }

    @GetMapping("/coches/{id}")
    public ResponseEntity<Coche> getCocheById(@PathVariable long id) throws CocheNotFoundException {
        return ResponseEntity.ok(cocheService.findById(id));
    }


    @PostMapping("/coches")
    public ResponseEntity<Coche> addCoche(@RequestBody CocheInDto cocheInDto) throws AutoescuelaNotFoundException {

        Autoescuela autoescuela = autoescuelaService.findById(cocheInDto.getAutoescuelaId());
        Coche nuevoCoche = cocheService.add(cocheInDto, autoescuela);

        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCoche);
    }

    @PutMapping("/coches/{id}")
    public ResponseEntity<Coche> modifyCoche(@RequestBody CocheInDto cocheInDto, @PathVariable long id) throws CocheNotFoundException, AutoescuelaNotFoundException {

        Autoescuela autoescuela = autoescuelaService.findById(cocheInDto.getAutoescuelaId());
        Coche cocheUpdated =  cocheService.modify(id, cocheInDto, autoescuela);

        return ResponseEntity.ok(cocheUpdated);
    }

    @DeleteMapping("/coches/{id}")
    public ResponseEntity<Void> deleteCoche(@PathVariable long id) throws CocheNotFoundException {
        cocheService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(CocheNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(CocheNotFoundException cnfe){
        ErrorResponse errorResponse = new ErrorResponse(404, "not-found", "Coche no encontrado");
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AutoescuelaNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(AutoescuelaNotFoundException cnfe){
        ErrorResponse errorResponse = new ErrorResponse(404, "not-found", "Autoescuela no encontrada");
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
