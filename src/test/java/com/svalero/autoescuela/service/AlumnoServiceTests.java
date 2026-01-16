package com.svalero.autoescuela.service;

import com.svalero.autoescuela.dto.AlumnoOutDto;
import com.svalero.autoescuela.dto.CocheOutDto;
import com.svalero.autoescuela.model.Alumno;
import com.svalero.autoescuela.model.Coche;
import com.svalero.autoescuela.repository.AlumnoRepository;
import com.svalero.autoescuela.repository.AutoescuelaRepository;
import com.svalero.autoescuela.repository.CocheRepository;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class AlumnoServiceTests {

    @InjectMocks
    private AlumnoService alumnoService;

    @Mock
    private AlumnoRepository alumnoRepository;
    @Mock
    private AutoescuelaRepository autoescuelaRepository;
    @Mock
    private ModelMapper modelMapper;

    @Test
    public void testFindAllAlumnos(){
        List<Alumno> mockAlumnoList = List.of(
                new Alumno(1, "Alvaro", "Reyes de Diego", "39964150V", LocalDate.of(1999,6,16), "633839069", "1999alvaroreyes@gmail.com","Calle 123", "Reus", false, 9.9f, null, null),
                new Alumno(2, "Santi", "Faci", "11164150D", LocalDate.of(1992,1,16), "223839069", "1992santiface@gmail.com","Calle 456", "Zaragoza", false, 9.9f, null, null)
        );
        List<AlumnoOutDto> mockAlumnoOutDtoList = List.of(
                new AlumnoOutDto(1, "Alvaro", "Reyes de Diego", "39964150V" ,"Reus", 9.9f ),
                new AlumnoOutDto(2, "Santi", "Faci", "11164150D", "Zaragoza", 9.9f )
        );

        when(alumnoRepository.findAll()).thenReturn(mockAlumnoList);
        when(modelMapper.map(mockAlumnoList, new TypeToken<List<AlumnoOutDto>>(){}.getType())).thenReturn(mockAlumnoOutDtoList);

        List<AlumnoOutDto> actualAlumnoList =  alumnoService.findAll();
        assertEquals(2, actualAlumnoList.size());
        assertEquals("Alvaro", actualAlumnoList.getFirst().getNombre());
        assertEquals("Santi", actualAlumnoList.getLast().getNombre());

        verify(alumnoRepository, times(1)).findAll();
        verify(alumnoRepository, times(0)).findByNombreAndCiudadAndUsaGafasAndNotaTeorico("","", null, null);
    }



}
