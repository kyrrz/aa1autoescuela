package com.svalero.autoescuela.service;

import com.svalero.autoescuela.dto.*;
import com.svalero.autoescuela.exception.AlumnoNotFoundException;
import com.svalero.autoescuela.exception.AutoescuelaNotFoundException;
import com.svalero.autoescuela.exception.MatriculaNotFoundException;
import com.svalero.autoescuela.exception.ProfesorNotFoundException;
import com.svalero.autoescuela.model.Alumno;
import com.svalero.autoescuela.model.Autoescuela;
import com.svalero.autoescuela.model.Matricula;
import com.svalero.autoescuela.repository.AlumnoRepository;
import com.svalero.autoescuela.repository.AutoescuelaRepository;
import com.svalero.autoescuela.repository.MatriculaRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatriculaService {

    @Autowired
    private MatriculaRepository matriculaRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private AlumnoRepository alumnoRepository;
    @Autowired
    private AutoescuelaRepository autoescuelaRepository;

    public MatriculaDetailOutDto add(MatriculaInDto matriculaInDto, AlumnoDetailOutDto alumnoDetailOutDto, AutoescuelaDetailOutDto autoescuelaDetailOutDto)
            throws AlumnoNotFoundException, MatriculaNotFoundException, AutoescuelaNotFoundException {

        Matricula m = modelMapper.map(matriculaInDto, Matricula.class);
//        m.setModalidad(matriculaInDto.getModalidad());
//        m.setTipoMatricula(matriculaInDto.getTipoMatricula());
//        m.setFechaInicio(matriculaInDto.getFechaInicio());
//        m.setFechaFinal(matriculaInDto.getFechaFinal());
//        m.setPrecio(matriculaInDto.getPrecio());
//        m.setHorasPracticas(matriculaInDto.getHorasPracticas());
//        m.setHorasTeoricas(matriculaInDto.getHorasTeoricas());
//        m.setCompletada(matriculaInDto.isCompletada());
//        m.setObservaciones(matriculaInDto.getObservaciones());
        Alumno alumno = alumnoRepository.findById(alumnoDetailOutDto.getId()).orElseThrow(AlumnoNotFoundException::new);
        Autoescuela autoescuela = autoescuelaRepository.findById(autoescuelaDetailOutDto.getId()).orElseThrow(AutoescuelaNotFoundException::new);
        m.setAlumno(alumno);
        m.setAutoescuela(autoescuela);

        Matricula matricula = matriculaRepository.save(m);
        return modelMapper.map(matricula, MatriculaDetailOutDto.class) ;
    }

    public void delete( long id ) throws MatriculaNotFoundException {
        Matricula m = matriculaRepository.findById(id)
                .orElseThrow(MatriculaNotFoundException::new);
        matriculaRepository.delete(m);
    }

    public List<Matricula> findAll(){
    return matriculaRepository.findAll();
    }

    public MatriculaDetailOutDto findById(long id) throws MatriculaNotFoundException {
        return matriculaRepository.findById(id)
                .map(matricula -> modelMapper.map(matricula, MatriculaDetailOutDto.class))
                .orElseThrow(MatriculaNotFoundException::new);
    }

    public List<MatriculaOutDto> findByFiltros(String modalidad, String tipoMatricula, Integer horasPracticas, Integer horasTeoricas)
    {

        if (modalidad != null && tipoMatricula != null && horasPracticas != null && horasTeoricas != null) {
            List<Matricula> matriculas = matriculaRepository.findByModalidadAndTipoMatriculaAndHorasPracticasAndHorasTeoricas(
                    modalidad, tipoMatricula, horasPracticas, horasTeoricas);
            List<MatriculaOutDto> mod = modelMapper.map(matriculas, new TypeToken<List<MatriculaOutDto>>() {}.getType());
            return mod;
        }
        if (tipoMatricula != null && horasPracticas != null && horasTeoricas != null) {
            List<Matricula> matriculas = matriculaRepository.findByTipoMatriculaAndHorasPracticasAndHorasTeoricas(
                    tipoMatricula, horasPracticas, horasTeoricas);
            List<MatriculaOutDto> mod = modelMapper.map(matriculas, new TypeToken<List<MatriculaOutDto>>() {}.getType());
            return mod;
        }
        if (modalidad != null && horasPracticas != null && horasTeoricas != null) {
            List<Matricula> matriculas = matriculaRepository.findByModalidadAndHorasPracticasAndHorasTeoricas(
                    modalidad, horasPracticas, horasTeoricas);
            List<MatriculaOutDto> mod = modelMapper.map(matriculas, new TypeToken<List<MatriculaOutDto>>() {}.getType());
            return mod;
        }
        if (modalidad != null && tipoMatricula != null && horasTeoricas != null) {
            List<Matricula> matriculas = matriculaRepository.findByModalidadAndTipoMatriculaAndHorasTeoricas(
                    modalidad, tipoMatricula, horasTeoricas);
            List<MatriculaOutDto> mod = modelMapper.map(matriculas, new TypeToken<List<MatriculaOutDto>>() {}.getType());
            return mod;
        }
        if (modalidad != null && tipoMatricula != null && horasPracticas != null) {
            List<Matricula> matriculas = matriculaRepository.findByModalidadAndTipoMatriculaAndHorasPracticas(
                    modalidad, tipoMatricula, horasPracticas);
            List<MatriculaOutDto> mod = modelMapper.map(matriculas, new TypeToken<List<MatriculaOutDto>>() {}.getType());
            return mod;
        }

        if (horasPracticas != null && horasTeoricas != null ) {
            List<Matricula> matriculas = matriculaRepository.findByHorasPracticasAndHorasTeoricas(
                     horasPracticas, horasTeoricas);
            List<MatriculaOutDto> mod = modelMapper.map(matriculas, new TypeToken<List<MatriculaOutDto>>() {}.getType());
            return mod;
        }
        if (tipoMatricula != null && horasTeoricas != null ) {
            List<Matricula> matriculas = matriculaRepository.findByTipoMatriculaAndHorasTeoricas(
                    tipoMatricula, horasTeoricas);
            List<MatriculaOutDto> mod = modelMapper.map(matriculas, new TypeToken<List<MatriculaOutDto>>() {}.getType());
            return mod;
        }
        if (tipoMatricula != null && horasPracticas != null ) {
            List<Matricula> matriculas = matriculaRepository.findByTipoMatriculaAndHorasPracticas(
                    tipoMatricula, horasPracticas);
            List<MatriculaOutDto> mod = modelMapper.map(matriculas, new TypeToken<List<MatriculaOutDto>>() {}.getType());
            return mod;
        }
        if (modalidad != null && horasTeoricas != null ) {
            List<Matricula> matriculas = matriculaRepository.findByModalidadAndHorasTeoricas(
                    modalidad, horasTeoricas);
            List<MatriculaOutDto> mod = modelMapper.map(matriculas, new TypeToken<List<MatriculaOutDto>>() {}.getType());
            return mod;
        }
        if (modalidad != null && horasPracticas != null ) {
            List<Matricula> matriculas = matriculaRepository.findByModalidadAndHorasPracticas(
                    modalidad, horasPracticas);
            List<MatriculaOutDto> mod = modelMapper.map(matriculas, new TypeToken<List<MatriculaOutDto>>() {}.getType());
            return mod;
        }
        if (modalidad != null && tipoMatricula != null ) {
            List<Matricula> matriculas = matriculaRepository.findByModalidadAndTipoMatricula(
                    modalidad, tipoMatricula);
            List<MatriculaOutDto> mod = modelMapper.map(matriculas, new TypeToken<List<MatriculaOutDto>>() {}.getType());
            return mod;
        }

        if (horasTeoricas != null ) {
            List<Matricula> matriculas = matriculaRepository.findByHorasTeoricas(
                    horasTeoricas);
            List<MatriculaOutDto> mod = modelMapper.map(matriculas, new TypeToken<List<MatriculaOutDto>>() {}.getType());
            return mod;
        }
        if (horasPracticas != null ) {
            List<Matricula> matriculas = matriculaRepository.findByHorasPracticas(
                    horasPracticas);
            List<MatriculaOutDto> mod = modelMapper.map(matriculas, new TypeToken<List<MatriculaOutDto>>() {}.getType());
            return mod;
        }
        if (tipoMatricula != null ) {
            List<Matricula> matriculas = matriculaRepository.findByTipoMatricula(
                    tipoMatricula);
            List<MatriculaOutDto> mod = modelMapper.map(matriculas, new TypeToken<List<MatriculaOutDto>>() {}.getType());
            return mod;
        }
        if (modalidad != null ) {
            List<Matricula> matriculas = matriculaRepository.findByModalidad(
                    modalidad);
            List<MatriculaOutDto> mod = modelMapper.map(matriculas, new TypeToken<List<MatriculaOutDto>>() {}.getType());
            return mod;
        }



        List<Matricula> matriculas = matriculaRepository.findAll();
        List<MatriculaOutDto> mod = modelMapper.map(matriculas, new TypeToken<List<MatriculaOutDto>>() {}.getType());

        return mod;
    }

    public MatriculaDetailOutDto modify(long id, MatriculaInDto matriculaInDto, AlumnoDetailOutDto alumnoDetailOutDto, AutoescuelaDetailOutDto autoescuelaDetailOutDto)
            throws MatriculaNotFoundException, AlumnoNotFoundException, AutoescuelaNotFoundException {
        Matricula matriculaAnterior = matriculaRepository.findById(id)
                .orElseThrow(MatriculaNotFoundException::new);

        modelMapper.map(matriculaInDto, matriculaAnterior);
//        m.setTipoMatricula(matriculaInDto.getTipoMatricula());
//        m.setFechaInicio(matriculaInDto.getFechaInicio());
//        m.setFechaFinal(matriculaInDto.getFechaFinal());
//        m.setPrecio(matriculaInDto.getPrecio());
//        m.setHorasPracticas(matriculaInDto.getHorasPracticas());
//        m.setHorasTeoricas(matriculaInDto.getHorasTeoricas());
//        m.setCompletada(matriculaInDto.isCompletada());
//        m.setObservaciones(matriculaInDto.getObservaciones());
        Alumno alumno = alumnoRepository.findById(alumnoDetailOutDto.getId())
                .orElseThrow(AlumnoNotFoundException::new);
        Autoescuela autoescuela =  autoescuelaRepository.findById(autoescuelaDetailOutDto.getId())
                .orElseThrow(AutoescuelaNotFoundException::new);
        matriculaAnterior.setAlumno(alumno);
        matriculaAnterior.setAutoescuela(autoescuela);
        matriculaAnterior.setId(id);

        Matricula matriculaSaved = matriculaRepository.save(matriculaAnterior);

        return modelMapper.map(matriculaSaved, MatriculaDetailOutDto.class);
    }
}
