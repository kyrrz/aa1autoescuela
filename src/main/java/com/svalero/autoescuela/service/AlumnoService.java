package com.svalero.autoescuela.service;

import com.svalero.autoescuela.exception.AlumnoNotFoundException;
import com.svalero.autoescuela.model.Alumno;
import com.svalero.autoescuela.repository.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlumnoService {

    @Autowired
    private AlumnoRepository alumnoRepository;

    public Alumno add(Alumno alumno){
        return alumnoRepository.save(alumno);
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

    public Alumno findById(long id) throws AlumnoNotFoundException{
        return alumnoRepository.findById(id)
                .orElseThrow(AlumnoNotFoundException::new);
    }

    public Alumno modify(long id, Alumno alumno) throws AlumnoNotFoundException {
        Alumno oldAlumno = alumnoRepository.findById(id)
                .orElseThrow(AlumnoNotFoundException::new);
        oldAlumno.setNombre(alumno.getNombre());
        oldAlumno.setApellidos(alumno.getApellidos());
        oldAlumno.setDni(alumno.getDni());
        oldAlumno.setFechaNacimiento(alumno.getFechaNacimiento());
        oldAlumno.setTelefono(alumno.getTelefono());
        oldAlumno.setEmail(alumno.getEmail());
        oldAlumno.setDireccion(alumno.getDireccion());
        oldAlumno.setUsaGafas(alumno.isUsaGafas());
        oldAlumno.setNotaTeorico(alumno.getNotaTeorico());

        return alumnoRepository.save(oldAlumno);
    }

}
