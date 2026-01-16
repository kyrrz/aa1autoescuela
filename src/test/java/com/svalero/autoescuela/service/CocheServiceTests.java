package com.svalero.autoescuela.service;

import com.svalero.autoescuela.dto.CocheOutDto;
import com.svalero.autoescuela.model.Coche;
import com.svalero.autoescuela.repository.AutoescuelaRepository;
import com.svalero.autoescuela.repository.CocheRepository;
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
public class CocheServiceTests {

    @InjectMocks
    private CocheService cocheService;
    @Mock
    private CocheRepository cocheRepository;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private AutoescuelaRepository autoescuelaRepository;

    @Test
    public void testFindAllCoches(){
        List<Coche> mockCocheList = List.of(
                new Coche(1, "1111FSX", "Mazda", "3", "Manual", 10000, LocalDate.now(), 30000.10f, true, null),
                new Coche(2, "2222GGG", "Mazda", "6", "Manual", 10000, LocalDate.now(), 30000.10f, true, null)
        );
        List<CocheOutDto> mockCocheOutDtoList = List.of(
                new CocheOutDto(1, "Mazda", "3", "Manual" ),
                new CocheOutDto(2, "Mazda", "6", "Manual" )
        );

        when(cocheRepository.findAll()).thenReturn(mockCocheList);
        when(modelMapper.map(mockCocheList, new TypeToken<List<CocheOutDto>>(){}.getType())).thenReturn(mockCocheOutDtoList);

        List<CocheOutDto> actualCocheList =  cocheService.findAllDto();
        assertEquals(2, actualCocheList.size());
        assertEquals("3", actualCocheList.getFirst().getModelo());
        assertEquals("6", actualCocheList.getLast().getModelo());

        verify(cocheRepository, times(1)).findAll();
        verify(cocheRepository, times(0)).findByMarcaAndModeloAndTipoCambio("","", "");


    }



}
