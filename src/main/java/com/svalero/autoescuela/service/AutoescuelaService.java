package com.svalero.autoescuela.service;

import com.svalero.autoescuela.dto.*;
import com.svalero.autoescuela.exception.AutoescuelaNotFoundException;
import com.svalero.autoescuela.model.*;
import com.svalero.autoescuela.repository.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AutoescuelaService {

    @Autowired
    private AutoescuelaRepository autoescuelaRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ProfesorRepository profesorRepository;
    @Autowired
    private CocheRepository cocheRepository;
    @Autowired
    private MatriculaRepository matriculaRepository;
    @Autowired
    private AlumnoRepository alumnoRepository;

    public AutoescuelaDetailOutDto add(AutoescuelaInDto autoescuelaInDto){

        Autoescuela autoescuela = modelMapper.map(autoescuelaInDto, Autoescuela.class);
        Autoescuela a = autoescuelaRepository.save(autoescuela);

        return modelMapper.map(a, AutoescuelaDetailOutDto.class);
    }

    public void delete( long id ) throws AutoescuelaNotFoundException {
        Autoescuela a = autoescuelaRepository.findById(id)
                .orElseThrow(AutoescuelaNotFoundException::new);
        autoescuelaRepository.delete(a);
    }

    public List<AutoescuelaOutDto> findAll(){
        List<Autoescuela> autoescuelas = autoescuelaRepository.findAll();
        List<AutoescuelaOutDto> aod =  modelMapper.map(autoescuelas, new TypeToken<List<AutoescuelaOutDto>>() {}.getType());

        return aod;
    }

    public AutoescuelaDetailOutDto findById(long id) throws AutoescuelaNotFoundException{

        Autoescuela a = autoescuelaRepository.findById(id)
                .orElseThrow(AutoescuelaNotFoundException::new);

        AutoescuelaDetailOutDto dto = new AutoescuelaDetailOutDto();
        dto.setId(a.getId());
        dto.setNombre(a.getNombre());
        dto.setDireccion(a.getDireccion());
        dto.setCiudad(a.getCiudad());
        dto.setTelefono(a.getTelefono());
        dto.setEmail(a.getEmail());
        dto.setRating(a.getRating());
        dto.setActiva(a.isActiva());

        dto.setCoches(
                a.getCoches().stream()
                        .map(c -> modelMapper.map(c, CocheOutDto.class))
                        .toList()
        );

        dto.setProfesores(
                a.getProfesores().stream()
                        .map(p -> modelMapper.map(p, ProfesorOutDto.class))
                        .toList()
        );

        return dto;
    }

    public List<AutoescuelaDetailOutDto> findAllById(List<Long> ids){
        List<Autoescuela> autoescuelas = (List<Autoescuela>) autoescuelaRepository.findAllById(ids);
        List<AutoescuelaDetailOutDto> autoescuelaDetailOutDtos = modelMapper.map(autoescuelas, new TypeToken<List<AutoescuelaDetailOutDto>>() {}.getType());
        return autoescuelaDetailOutDtos;
    }

    public List<AutoescuelaOutDto> findByFiltros(String ciudad, String ratingG, Boolean activa){


        if (ciudad != null && ratingG != null && activa != null) {
            Float rating = Float.parseFloat(ratingG);
            List<Autoescuela> autoescuelas = autoescuelaRepository.findByCiudadAndRatingAndActiva(ciudad, rating, activa);
            List<AutoescuelaOutDto> aod = modelMapper.map(autoescuelas, new TypeToken<List<AutoescuelaOutDto>>() {}.getType());

            return aod;
        }
        if (ratingG != null && activa != null) {
            Float rating = Float.parseFloat(ratingG);
            List<Autoescuela> autoescuelas = autoescuelaRepository.findByRatingAndActiva( rating, activa);
            List<AutoescuelaOutDto> aod = modelMapper.map(autoescuelas, new TypeToken<List<AutoescuelaOutDto>>() {}.getType());

            return aod;
        }
        if (ciudad != null && activa != null) {
            List<Autoescuela> autoescuelas = autoescuelaRepository.findByCiudadAndActiva( ciudad, activa);
            List<AutoescuelaOutDto> aod = modelMapper.map(autoescuelas, new TypeToken<List<AutoescuelaOutDto>>() {}.getType());

            return aod;
        }
        if (ciudad != null && ratingG != null) {
            Float rating = Float.parseFloat(ratingG);
            List<Autoescuela> autoescuelas = autoescuelaRepository.findByCiudadAndRating( ciudad, rating);
            List<AutoescuelaOutDto> aod = modelMapper.map(autoescuelas, new TypeToken<List<AutoescuelaOutDto>>() {}.getType());

            return aod;
        }

        if (activa != null) {
            List<Autoescuela> autoescuelas = autoescuelaRepository.findByActiva( activa);
            List<AutoescuelaOutDto> aod = modelMapper.map(autoescuelas, new TypeToken<List<AutoescuelaOutDto>>() {}.getType());

            return aod;
        }
        if (ratingG != null) {
            Float rating = Float.parseFloat(ratingG);
            List<Autoescuela> autoescuelas = autoescuelaRepository.findByRating( rating);
            List<AutoescuelaOutDto> aod = modelMapper.map(autoescuelas, new TypeToken<List<AutoescuelaOutDto>>() {}.getType());

            return aod;
        }
        if (ciudad != null) {
            List<Autoescuela> autoescuelas = autoescuelaRepository.findByCiudad( ciudad);
            List<AutoescuelaOutDto> aod = modelMapper.map(autoescuelas, new TypeToken<List<AutoescuelaOutDto>>() {}.getType());

            return aod;
        }

        List<Autoescuela> autoescuelas = autoescuelaRepository.findAll();
        List<AutoescuelaOutDto> aod = modelMapper.map(autoescuelas, new TypeToken<List<AutoescuelaOutDto>>() {}.getType());
        return aod;
    }

    public AutoescuelaDetailOutDto modify(long id, AutoescuelaInDto autoescuelaInDto) throws AutoescuelaNotFoundException {
        Autoescuela a = autoescuelaRepository.findById(id)
                .orElseThrow(AutoescuelaNotFoundException::new);
        modelMapper.map(autoescuelaInDto, a);
        a.setId(id);
//        a.setNombre(autoescuelaInDto.getNombre());
//        a.setDireccion(autoescuelaInDto.getDireccion());
//        a.setCiudad(autoescuelaInDto.getCiudad());
//        a.setTelefono(autoescuelaInDto.getTelefono());
//        a.setEmail(autoescuelaInDto.getEmail());
//        a.setCapacidad(autoescuelaInDto.getCapacidad());
//        a.setRating(autoescuelaInDto.getRating());
//        a.setFechaApertura(autoescuelaInDto.getFechaApertura());
//        a.setActiva(autoescuelaInDto.isActiva());
        Autoescuela au = autoescuelaRepository.save(a);
        return modelMapper.map(au, AutoescuelaDetailOutDto.class);
    }

//    public List<ProfesorOutDto> getProfesores(Long autoescuelaId) throws AutoescuelaNotFoundException {
//        Autoescuela a = autoescuelaRepository.findById(autoescuelaId)
//                .orElseThrow(AutoescuelaNotFoundException::new);
//
//        return a.getProfesores().stream().map(p -> modelMapper.map(p, ProfesorOutDto.class)).toList();
//    }
    public List<CocheOutDto> getCoches(Long autoescuelaId){
        List<Coche> coche = cocheRepository.findCochesByAutoescuelaId( autoescuelaId);
        return coche.stream().map(c -> modelMapper.map(c, CocheOutDto.class)).toList();
    }

    public List<MatriculaOutDto> getMatriculas(Long autoescuelaId){
        List<Matricula> matricula = matriculaRepository.findMatriculaByAutoescuelaId( autoescuelaId);

        return matricula.stream().map(m -> modelMapper.map(m, MatriculaOutDto.class)).toList();
    }

    public List<ProfesorOutDto> getProfesores(Long autoescuelaId){
        List<Profesor> profesor = profesorRepository.findProfesoresByAutoescuelaId(autoescuelaId);
             return profesor.stream().map(p -> modelMapper.map(p, ProfesorOutDto.class)).toList();
    }

    public List<MatriculaOutDto> getMatriculasCompletas(Long autoescuelaId){
        List<Matricula> matriculas = matriculaRepository.findMatriculaCompletasByAutoescuelaId(autoescuelaId);

        return matriculas.stream().map(m -> modelMapper.map(m, MatriculaOutDto.class)).toList();
    }

    public List<AlumnoOutDto> getAlumnosSuspensos(Long autoescuelaId){
        List<Alumno> alumnos = alumnoRepository.findAlumnosSuspensosByAutoescuela(autoescuelaId);

        return alumnos.stream().map(a -> modelMapper.map(a, AlumnoOutDto.class)).toList();
    }

}
