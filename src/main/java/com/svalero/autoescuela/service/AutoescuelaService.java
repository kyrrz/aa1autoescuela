package com.svalero.autoescuela.service;

import com.svalero.autoescuela.dto.*;
import com.svalero.autoescuela.exception.AutoescuelaNotFoundException;
import com.svalero.autoescuela.model.Alumno;
import com.svalero.autoescuela.model.Autoescuela;
import com.svalero.autoescuela.repository.AutoescuelaRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AutoescuelaService {

    @Autowired
    private AutoescuelaRepository autoescuelaRepository;
    @Autowired
    private ModelMapper modelMapper;

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

    public AutoescuelaDetailOutDto toDetailDto(Autoescuela a) {

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
                a.getCoches().stream().map(coche -> modelMapper.map(coche, CocheOutDto.class)).toList()
        );

        dto.setProfesores(
                a.getProfesores().stream().map(p -> modelMapper.map(p, ProfesorOutDto.class)).toList()
        );

        return dto;
    }

    public List<AutoescuelaDetailOutDto> findAllById(List<Long> ids) {

        Iterable<Autoescuela> iterable = autoescuelaRepository.findAllById(ids);

        List<Autoescuela> autoescuelas = new ArrayList<>();
        iterable.forEach(autoescuelas::add);

        return autoescuelas.stream()
                .map(this::toDetailDto)
                .toList();
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

    public AutoescuelaDetailOutDto patch(Long id, Map<String, Object> patch)
            throws AutoescuelaNotFoundException {

        Autoescuela autoescuela = autoescuelaRepository.findById(id)
                .orElseThrow(AutoescuelaNotFoundException::new);

        patch.forEach((key, value) -> {
            switch (key) {
                case "nombre":
                    autoescuela.setNombre((String) value);
                    break;
                case "direccion":
                    autoescuela.setDireccion((String) value);
                    break;
                case "ciudad":
                    autoescuela.setCiudad((String) value);
                    break;
                case "telefono":
                    autoescuela.setTelefono((String) value);
                    break;
                case "email":
                    autoescuela.setEmail((String) value);
                    break;
                case "capacidad":
                    autoescuela.setCapacidad(((Number) value).intValue());
                    break;
                case "rating":
                    autoescuela.setRating(((Number) value).floatValue());
                    break;
                case "fechaApertura":
                    autoescuela.setFechaApertura(LocalDate.parse((String) value));
                    break;
                case "activa":
                    autoescuela.setActiva((Boolean) value);
                    break;
            }
        });

        Autoescuela autoescuelaActualizada = autoescuelaRepository.save(autoescuela);
        return modelMapper.map(autoescuelaActualizada, AutoescuelaDetailOutDto.class);
    }
}
