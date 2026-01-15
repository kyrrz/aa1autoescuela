package com.svalero.autoescuela.service;

import com.svalero.autoescuela.dto.AlumnoInDto;
import com.svalero.autoescuela.dto.AutoescuelaOutDto;
import com.svalero.autoescuela.exception.AlumnoNotFoundException;
import com.svalero.autoescuela.model.Alumno;
import com.svalero.autoescuela.model.Autoescuela;
import com.svalero.autoescuela.model.Matricula;
import com.svalero.autoescuela.repository.AlumnoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlumnoService {

    @Autowired
    private AlumnoRepository alumnoRepository;
    @Autowired
    private ModelMapper modelMapper;


    public Alumno add(AlumnoInDto alumnoInDto, Autoescuela autoescuela){
        Alumno a = new Alumno();
        modelMapper.map(alumnoInDto, a);
//        a.setNombre(alumnoInDto.getNombre());
//        a.setApellidos(alumnoInDto.getApellidos());
//        a.setDni(alumnoInDto.getDni());
//        a.setFechaNacimiento(alumnoInDto.getFechaNacimiento());
//        a.setTelefono(alumnoInDto.getTelefono());
//        a.setEmail(alumnoInDto.getEmail());
//        a.setDireccion(alumnoInDto.getDireccion());
//        a.setUsaGafas(a.isUsaGafas());
//        a.setNotaTeorico(alumnoInDto.getNotaTeorico());
        a.setAutoescuela(autoescuela);

        return alumnoRepository.save(a);
    }

    public void delete( long id ) throws AlumnoNotFoundException {
        Alumno a = alumnoRepository.findById(id)
                .orElseThrow(AlumnoNotFoundException::new);
        alumnoRepository.delete(a);
    }

    public List<Alumno> findAll(){
        return alumnoRepository.findAll();
    }

    public List<Alumno> findByGafas(Boolean g){
        return alumnoRepository.findByUsaGafas(g);
    }

    public List<Alumno> findByCiudad(String ciudad){
        return alumnoRepository.findByCiudad(ciudad);
    }

    public List<Alumno> findByFiltros(String nombre, String ciudad, Boolean usaGafas, Float notaTeorico){
        // 4 filtros
        if (nombre != null && ciudad != null && usaGafas != null && notaTeorico != null) {
            return alumnoRepository.findByNombreAndCiudadAndUsaGafasAndNotaTeorico(
                    nombre, ciudad, usaGafas, notaTeorico);
        }

        // 3 filtros
        if (nombre != null && ciudad != null && usaGafas != null) {
            return alumnoRepository.findByNombreAndCiudadAndUsaGafas(nombre, ciudad, usaGafas);
        }

        if (nombre != null && ciudad != null && notaTeorico != null) {
            return alumnoRepository.findByNombreAndCiudadAndNotaTeorico(nombre, ciudad, notaTeorico);
        }

        if (nombre != null && usaGafas != null && notaTeorico != null) {
            return alumnoRepository.findByNombreAndUsaGafasAndNotaTeorico(nombre, usaGafas, notaTeorico);
        }

        if (ciudad != null && usaGafas != null && notaTeorico != null) {
            return alumnoRepository.findByCiudadAndUsaGafasAndNotaTeorico(ciudad, usaGafas, notaTeorico);
        }

        // 2 filtros
        if (nombre != null && ciudad != null) {
            return alumnoRepository.findByNombreAndCiudad(nombre, ciudad);
        }

        if (nombre != null && usaGafas != null) {
            return alumnoRepository.findByNombreAndUsaGafas(nombre, usaGafas);
        }

        if (nombre != null && notaTeorico != null) {
            return alumnoRepository.findByNombreAndNotaTeorico(nombre, notaTeorico);
        }

        if (ciudad != null && usaGafas != null) {
            return alumnoRepository.findByCiudadAndUsaGafas(ciudad, usaGafas);
        }

        if (ciudad != null && notaTeorico != null) {
            return alumnoRepository.findByCiudadAndNotaTeorico(ciudad, notaTeorico);
        }

        if (usaGafas != null && notaTeorico != null) {
            return alumnoRepository.findByUsaGafasAndNotaTeorico(usaGafas, notaTeorico);
        }

        // 1 filtro
        if (nombre != null) {
            return alumnoRepository.findByNombre(nombre);
        }

        if (ciudad != null) {
            return alumnoRepository.findByCiudad(ciudad);
        }

        if (usaGafas != null) {
            return alumnoRepository.findByUsaGafas(usaGafas);
        }

        if (notaTeorico != null) {
            return alumnoRepository.findByNotaTeorico(notaTeorico);
        }

        // ningún filtro
        return alumnoRepository.findAll();
    }

    public Alumno findById(long id) throws AlumnoNotFoundException{
        return alumnoRepository.findById(id)
                .orElseThrow(AlumnoNotFoundException::new);
    }

    public Alumno modify(long id, AlumnoInDto alumnoInDto, Autoescuela autoescuela) throws AlumnoNotFoundException {
        Alumno a = alumnoRepository.findById(id)
                .orElseThrow(AlumnoNotFoundException::new);

        modelMapper.map(alumnoInDto, a);
//        oldAlumno.setNombre(alumnoInDto.getNombre());
//        oldAlumno.setApellidos(alumnoInDto.getApellidos());
//        oldAlumno.setDni(alumnoInDto.getDni());
//        oldAlumno.setTelefono(alumnoInDto.getTelefono());
//        oldAlumno.setEmail(alumnoInDto.getEmail());
//        oldAlumno.setDireccion(alumnoInDto.getDireccion());
//        oldAlumno.setCiudad(alumnoInDto.getCiudad());
//        oldAlumno.setFechaNacimiento(alumnoInDto.getFechaNacimiento());
//        oldAlumno.setNotaTeorico(alumnoInDto.getNotaTeorico());
//        oldAlumno.setUsaGafas(alumnoInDto.isUsaGafas());
        a.setId(id);
        a.setAutoescuela(autoescuela);

        return alumnoRepository.save(a);
    }

}
