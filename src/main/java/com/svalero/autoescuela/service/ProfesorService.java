package com.svalero.autoescuela.service;

import com.svalero.autoescuela.dto.ProfesorInDto;
import com.svalero.autoescuela.exception.ProfesorNotFoundException;
import com.svalero.autoescuela.model.Autoescuela;
import com.svalero.autoescuela.model.Profesor;
import com.svalero.autoescuela.repository.ProfesorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ProfesorService {

    @Autowired
    private ProfesorRepository profesorRepository;
    @Autowired
    private ModelMapper modelMapper;


    public Profesor add(ProfesorInDto profesorInDto, List<Autoescuela> autoescuela) {

        Profesor p = new Profesor();
        modelMapper.map(profesorInDto, p);
//        p.setNombre(profesorInDto.getNombre());
//        p.setApellidos(profesorInDto.getApellidos());
//        p.setDni(profesorInDto.getDni());
//        p.setTelefono(profesorInDto.getTelefono());
//        p.setSalario(profesorInDto.getSalario());
//        p.setFechaContratacion(profesorInDto.getFechaContratacion());
//        p.setEspecialidad(profesorInDto.getEspecialidad());
//        p.setActivo(profesorInDto.isActivo());
        p.setAutoescuelas(autoescuela);

        return profesorRepository.save(p);
    }

    public void delete( long id ) throws ProfesorNotFoundException {
        Profesor p = profesorRepository.findById(id)
                .orElseThrow(ProfesorNotFoundException::new);
        profesorRepository.delete(p);
    }

    public List<Profesor> findAll(){
        return profesorRepository.findAll();
    }

    public List<Profesor> findByEspecialidad(String especialidad){
        return profesorRepository.findByEspecialidad(especialidad);
    }

    public List<Profesor> findByFilters(String nombre, String especialidad, Boolean activo){

        if (nombre != null && especialidad != null && activo != null) {
            return profesorRepository.findByNombreAndEspecialidadAndActivo(
                    nombre, especialidad, activo);
        }

        if (nombre != null && especialidad != null) {
            return profesorRepository.findByNombreAndEspecialidad(
                    nombre, especialidad);
        }

        if (nombre != null && activo != null) {
            return profesorRepository.findByNombreAndActivo(
                    nombre, activo);
        }

        if (especialidad != null && activo != null) {
            return profesorRepository.findByActivoAndEspecialidad(
                    activo, especialidad);
        }

        if (nombre != null) {
            return profesorRepository.findByNombre(nombre);
        }

        if (especialidad != null) {
            return profesorRepository.findByEspecialidad(especialidad);
        }

        if (activo != null) {
            return profesorRepository.findByActivo(activo);
        }
        return profesorRepository.findAll();

    }

    public Profesor findById(long id) throws ProfesorNotFoundException{
        return profesorRepository.findById(id)
                .orElseThrow(ProfesorNotFoundException::new);
    }


    public Profesor modify(long id, ProfesorInDto profesorInDto, List<Autoescuela> autoescuela) throws ProfesorNotFoundException {
        Profesor p = profesorRepository.findById(id)
                .orElseThrow(ProfesorNotFoundException::new);
        modelMapper.map(profesorInDto, p);
//        p.setNombre(profesorInDto.getNombre());
//        p.setApellidos(profesorInDto.getApellidos());
//        p.setDni(profesorInDto.getDni());
//        p.setTelefono(profesorInDto.getTelefono());
//        p.setSalario(profesorInDto.getSalario());
//        p.setFechaContratacion(profesorInDto.getFechaContratacion());
//        p.setEspecialidad(profesorInDto.getEspecialidad());
//        p.setActivo(profesorInDto.isActivo());
        p.setId(id);
        p.setAutoescuelas(autoescuela);

        return profesorRepository.save(p);
    }}
