package com.svalero.autoescuela.service;

import com.svalero.autoescuela.dto.AlumnoDetailOutDto;
import com.svalero.autoescuela.dto.AlumnoInDto;
import com.svalero.autoescuela.dto.AlumnoOutDto;
import com.svalero.autoescuela.dto.AutoescuelaDetailOutDto;
import com.svalero.autoescuela.exception.AlumnoNotFoundException;
import com.svalero.autoescuela.exception.AutoescuelaNotFoundException;
import com.svalero.autoescuela.model.Alumno;
import com.svalero.autoescuela.model.Autoescuela;
import com.svalero.autoescuela.repository.AlumnoRepository;
import com.svalero.autoescuela.repository.AutoescuelaRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlumnoService {

    @Autowired
    private AlumnoRepository alumnoRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private AutoescuelaRepository autoescuelaRepository;


    public AlumnoDetailOutDto add(AlumnoInDto alumnoInDto, AutoescuelaDetailOutDto autoescuelaDetailOutDto) throws AutoescuelaNotFoundException {
        Alumno alumno = modelMapper.map(alumnoInDto, Alumno.class);
//        a.setNombre(alumnoInDto.getNombre());
//        a.setApellidos(alumnoInDto.getApellidos());
//        a.setDni(alumnoInDto.getDni());
//        a.setFechaNacimiento(alumnoInDto.getFechaNacimiento());
//        a.setTelefono(alumnoInDto.getTelefono());
//        a.setEmail(alumnoInDto.getEmail());
//        a.setDireccion(alumnoInDto.getDireccion());
//        a.setUsaGafas(a.isUsaGafas());
//        a.setNotaTeorico(alumnoInDto.getNotaTeorico());
        Autoescuela autoescuela = autoescuelaRepository.findById(autoescuelaDetailOutDto.getId())
                .orElseThrow(AutoescuelaNotFoundException::new);

        alumno.setAutoescuela(autoescuela);

        Alumno a = alumnoRepository.save(alumno);
        return modelMapper.map(a, AlumnoDetailOutDto.class);
    }

    public void delete( long id ) throws AlumnoNotFoundException {
        Alumno a = alumnoRepository.findById(id)
                .orElseThrow(AlumnoNotFoundException::new);
        alumnoRepository.delete(a);
    }

    public List<AlumnoOutDto> findAll(){

        List<Alumno> alumnoList = alumnoRepository.findAll();
        List<AlumnoOutDto> all =  modelMapper.map(alumnoList, new TypeToken<List<AlumnoOutDto>>() {}.getType());

        return all;
    }


    public List<AlumnoOutDto> findByFiltros(String nombre, String ciudad, Boolean usaGafas, Float notaTeorico){
        if (nombre != null && ciudad != null && usaGafas != null && notaTeorico != null) {
            List<Alumno> alumnoList = alumnoRepository.findByNombreAndCiudadAndUsaGafasAndNotaTeorico(
                    nombre, ciudad, usaGafas, notaTeorico);
            List<AlumnoOutDto> aod = modelMapper.map(alumnoList, new TypeToken<List<AlumnoOutDto>>() {}.getType());
            return aod;
        }
        if( nombre != null && ciudad != null && usaGafas != null ){
            List<Alumno> alumnoList = alumnoRepository.findByNombreAndCiudadAndUsaGafas(nombre, ciudad, usaGafas);
            List<AlumnoOutDto> aod = modelMapper.map(alumnoList, new TypeToken<List<AlumnoOutDto>>() {}.getType());
            return aod;
        }
        if( nombre != null && ciudad != null && notaTeorico != null ){
            List<Alumno> alumnoList = alumnoRepository.findByNombreAndCiudadAndNotaTeorico(nombre, ciudad, notaTeorico);
            List<AlumnoOutDto> aod = modelMapper.map(alumnoList, new TypeToken<List<AlumnoOutDto>>() {}.getType());
            return aod;
        }
        if( nombre != null && usaGafas != null && notaTeorico != null ){
            List<Alumno> alumnoList = alumnoRepository.findByNombreAndUsaGafasAndNotaTeorico(nombre,  usaGafas, notaTeorico);
            List<AlumnoOutDto> aod = modelMapper.map(alumnoList, new TypeToken<List<AlumnoOutDto>>() {}.getType());
            return aod;
        }
        if ( ciudad != null && usaGafas != null && notaTeorico != null ){
            List<Alumno> alumnoList = alumnoRepository.findByCiudadAndUsaGafasAndNotaTeorico(ciudad, usaGafas, notaTeorico);
            List<AlumnoOutDto> aod = modelMapper.map(alumnoList, new TypeToken<List<AlumnoOutDto>>() {}.getType());
            return aod;
        }

        if(ciudad != null && usaGafas != null){
            List<Alumno> alumnoList = alumnoRepository.findByCiudadAndUsaGafas(ciudad, usaGafas);
            List<AlumnoOutDto> aod = modelMapper.map(alumnoList, new TypeToken<List<AlumnoOutDto>>() {}.getType());
            return aod;
        }
        if(ciudad != null && notaTeorico != null){
            List<Alumno> alumnoList = alumnoRepository.findByCiudadAndNotaTeorico(ciudad, notaTeorico);
            List<AlumnoOutDto> aod = modelMapper.map(alumnoList, new TypeToken<List<AlumnoOutDto>>() {}.getType());
            return aod;
        }
        if(usaGafas != null && notaTeorico != null){
            List<Alumno> alumnoList = alumnoRepository.findByUsaGafasAndNotaTeorico(usaGafas, notaTeorico);
            List<AlumnoOutDto> aod = modelMapper.map(alumnoList, new TypeToken<List<AlumnoOutDto>>() {}.getType());
            return aod;
        }
        if(nombre != null && notaTeorico != null){
            List<Alumno> alumnoList = alumnoRepository.findByNombreAndNotaTeorico(nombre, notaTeorico);
            List<AlumnoOutDto> aod = modelMapper.map(alumnoList, new TypeToken<List<AlumnoOutDto>>() {}.getType());
            return aod;
        }
        if(nombre != null && usaGafas != null){
            List<Alumno> alumnoList = alumnoRepository.findByNombreAndUsaGafas(nombre, usaGafas);
            List<AlumnoOutDto> aod = modelMapper.map(alumnoList, new TypeToken<List<AlumnoOutDto>>() {}.getType());
            return aod;
        }
        if(nombre != null && ciudad != null){
            List<Alumno> alumnoList = alumnoRepository.findByNombreAndCiudad(nombre, ciudad);
            List<AlumnoOutDto> aod = modelMapper.map(alumnoList, new TypeToken<List<AlumnoOutDto>>() {}.getType());
            return aod;
        }

        if(nombre != null){
            List<Alumno> alumnoList = alumnoRepository.findByNombre(nombre);
            List<AlumnoOutDto> aod = modelMapper.map(alumnoList, new TypeToken<List<AlumnoOutDto>>() {}.getType());
            return aod;
        }
        if(ciudad != null){
            List<Alumno> alumnoList = alumnoRepository.findByCiudad(ciudad);
            List<AlumnoOutDto> aod = modelMapper.map(alumnoList, new TypeToken<List<AlumnoOutDto>>() {}.getType());
            return aod;
        }
        if(usaGafas != null){
            List<Alumno> alumnoList = alumnoRepository.findByUsaGafas(usaGafas);
            List<AlumnoOutDto> aod = modelMapper.map(alumnoList, new TypeToken<List<AlumnoOutDto>>() {}.getType());
            return aod;
        }
        if(notaTeorico != null){
            List<Alumno> alumnoList = alumnoRepository.findByNotaTeorico(notaTeorico);
            List<AlumnoOutDto> aod = modelMapper.map(alumnoList, new TypeToken<List<AlumnoOutDto>>() {}.getType());
            return aod;
        }


        List<Alumno> alumnoList = alumnoRepository.findAll();
        List<AlumnoOutDto> all =  modelMapper.map(alumnoList, new TypeToken<List<AlumnoOutDto>>() {}.getType());

        return all;
    }

    public AlumnoDetailOutDto findById(long id) throws AlumnoNotFoundException{
        return alumnoRepository.findById(id).map(alumno -> modelMapper.map(alumno, AlumnoDetailOutDto.class))
                .orElseThrow(AlumnoNotFoundException::new);
    }

    public AlumnoDetailOutDto modify(long id, AlumnoInDto alumnoInDto, AutoescuelaDetailOutDto autoescuelaDetailOutDto) throws AutoescuelaNotFoundException, AlumnoNotFoundException {
        Alumno a = alumnoRepository.findById(id)
                .orElseThrow(AlumnoNotFoundException::new);
        modelMapper.map(alumnoInDto, a);
//        a.setNombre(alumnoInDto.getNombre());
//        a.setApellidos(alumnoInDto.getApellidos());
//        a.setDni(alumnoInDto.getDni());
//        a.setTelefono(alumnoInDto.getTelefono());
//        a.setEmail(alumnoInDto.getEmail());
//        a.setDireccion(alumnoInDto.getDireccion());
//        a.setCiudad(alumnoInDto.getCiudad());
//        a.setFechaNacimiento(alumnoInDto.getFechaNacimiento());
//        a.setNotaTeorico(alumnoInDto.getNotaTeorico());
//        a.setUsaGafas(alumnoInDto.isUsaGafas());
        System.out.println(alumnoInDto);
        System.out.println(a);
        System.out.println(autoescuelaDetailOutDto);
        System.out.println(autoescuelaRepository.findById(autoescuelaDetailOutDto.getId()));
        Autoescuela autoescuela = autoescuelaRepository.findById(autoescuelaDetailOutDto.getId())
                .orElseThrow(AutoescuelaNotFoundException::new);
        a.setId(id);
        a.setAutoescuela(autoescuela);

        Alumno alumno = alumnoRepository.save(a);
        return modelMapper.map(alumno, AlumnoDetailOutDto.class);
    }

}
