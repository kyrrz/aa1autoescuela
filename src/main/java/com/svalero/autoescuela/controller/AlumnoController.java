package com.svalero.autoescuela.controller;

import com.svalero.autoescuela.dto.AlumnoInDto;
import com.svalero.autoescuela.dto.AlumnoOutDto;
import com.svalero.autoescuela.exception.AlumnoNotFoundException;
import com.svalero.autoescuela.exception.AutoescuelaNotFoundException;
import com.svalero.autoescuela.exception.ErrorResponse;
import com.svalero.autoescuela.model.Alumno;
import com.svalero.autoescuela.model.Autoescuela;
import com.svalero.autoescuela.service.AlumnoService;
import com.svalero.autoescuela.service.AutoescuelaService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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

        List<Alumno> alumnos = alumnoService.findByFiltros(nombre,ciudad,usaGafas,notaTeorico);
        List<AlumnoOutDto> aod = modelMapper.map(alumnos, new TypeToken<List<AlumnoOutDto>>() {}.getType());

        return ResponseEntity.ok(aod);
    }

    @GetMapping("/alumnos/{id}")
    public ResponseEntity<Alumno> getAlumnoById(@PathVariable long id) throws AlumnoNotFoundException{
        return ResponseEntity.ok(alumnoService.findById(id));
    }


    @PostMapping("/alumnos")
    public ResponseEntity<Alumno> addAlumno(@RequestBody AlumnoInDto alumnoInDto) throws AutoescuelaNotFoundException {

        Autoescuela autoescuela = autoescuelaService.findById(alumnoInDto.getAutoescuelaId());
        Alumno nuevoAlumno = alumnoService.add(alumnoInDto, autoescuela);

        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoAlumno);
    }

    @PutMapping("/alumnos/{id}")
    public ResponseEntity<Alumno> modifyAlumno(@RequestBody AlumnoInDto alumnoInDto, @PathVariable long id) throws AlumnoNotFoundException, AutoescuelaNotFoundException {

        Autoescuela autoescuela = autoescuelaService.findById(alumnoInDto.getAutoescuelaId());
        Alumno alumnoUpdated = alumnoService.modify(id, alumnoInDto, autoescuela);

        return ResponseEntity.ok(alumnoUpdated);
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

    @ExceptionHandler(AutoescuelaNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(AutoescuelaNotFoundException anfe){
        ErrorResponse errorResponse = new ErrorResponse(404, "not-found", "Autoescuela no encontrada");
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

}
