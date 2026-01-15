package com.svalero.autoescuela.service;

import com.svalero.autoescuela.dto.MatriculaInDto;
import com.svalero.autoescuela.exception.MatriculaNotFoundException;
import com.svalero.autoescuela.model.Alumno;
import com.svalero.autoescuela.model.Autoescuela;
import com.svalero.autoescuela.model.Matricula;
import com.svalero.autoescuela.repository.MatriculaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatriculaService {

    @Autowired
    private MatriculaRepository matriculaRepository;
    @Autowired
    private ModelMapper modelMapper;

    public Matricula add(MatriculaInDto matriculaInDto, Alumno alumno, Autoescuela autoescuela) {
        Matricula m = new Matricula();
        modelMapper.map(matriculaInDto, m);
//        m.setModalidad(matriculaInDto.getModalidad());
//        m.setTipoMatricula(matriculaInDto.getTipoMatricula());
//        m.setFechaInicio(matriculaInDto.getFechaInicio());
//        m.setFechaFinal(matriculaInDto.getFechaFinal());
//        m.setPrecio(matriculaInDto.getPrecio());
//        m.setHorasPracticas(matriculaInDto.getHorasPracticas());
//        m.setHorasTeoricas(matriculaInDto.getHorasTeoricas());
//        m.setCompletada(matriculaInDto.isCompletada());
//        m.setObservaciones(matriculaInDto.getObservaciones());
        m.setAlumno(alumno);
        m.setAutoescuela(autoescuela);

        return matriculaRepository.save(m);
    }

    public void delete( long id ) throws MatriculaNotFoundException {
        Matricula m = matriculaRepository.findById(id)
                .orElseThrow(MatriculaNotFoundException::new);
        matriculaRepository.delete(m);
    }

    public List<Matricula> findAll(){
    return matriculaRepository.findAll();
    }


    public List<Matricula> findByModalidad(String modalidad){
        return matriculaRepository.findByModalidad(modalidad);
    }

    public Matricula findById(long id) throws MatriculaNotFoundException {
        return matriculaRepository.findById(id)
                .orElseThrow(MatriculaNotFoundException::new);
    }

    public Matricula modify(long id, MatriculaInDto matriculaInDto, Alumno alumno, Autoescuela autoescuela) throws MatriculaNotFoundException {
        Matricula m = matriculaRepository.findById(id)
                .orElseThrow(MatriculaNotFoundException::new);

        modelMapper.map(matriculaInDto, m);
//        m.setTipoMatricula(matriculaInDto.getTipoMatricula());
//        m.setFechaInicio(matriculaInDto.getFechaInicio());
//        m.setFechaFinal(matriculaInDto.getFechaFinal());
//        m.setPrecio(matriculaInDto.getPrecio());
//        m.setHorasPracticas(matriculaInDto.getHorasPracticas());
//        m.setHorasTeoricas(matriculaInDto.getHorasTeoricas());
//        m.setCompletada(matriculaInDto.isCompletada());
//        m.setObservaciones(matriculaInDto.getObservaciones());
        m.setId(id);
        m.setAlumno(alumno);
        m.setAutoescuela(autoescuela);

        return matriculaRepository.save(m);
    }
}
