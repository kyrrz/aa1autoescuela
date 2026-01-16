package com.svalero.autoescuela.service;

import com.svalero.autoescuela.repository.AutoescuelaRepository;
import com.svalero.autoescuela.repository.ProfesorRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@ExtendWith(MockitoExtension.class)
class ProfesorServiceTests {

    @Mock
    private ProfesorRepository profesorRepository;

    @Mock
    private AutoescuelaRepository autoescuelaRepository;

    @InjectMocks
    private ProfesorService profesorService;


}