package com.svalero.autoescuela.service;

import com.svalero.autoescuela.dto.*;
import com.svalero.autoescuela.exception.AutoescuelaNotFoundException;
import com.svalero.autoescuela.model.*;
import com.svalero.autoescuela.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AutoescuelaServiceTests {

    @Mock
    private AutoescuelaRepository autoescuelaRepository;

    @Mock
    private ProfesorRepository profesorRepository;

    @Mock
    private CocheRepository cocheRepository;

    @Mock
    private MatriculaRepository matriculaRepository;

    @Mock
    private AlumnoRepository alumnoRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private AutoescuelaService autoescuelaService;

    private Autoescuela autoescuela;
    private AutoescuelaInDto autoescuelaInDto;
    private AutoescuelaOutDto autoescuelaOutDto;
    private AutoescuelaDetailOutDto autoescuelaDetailOutDto;

    @BeforeEach
    void setUp() {
        autoescuela = new Autoescuela();
        autoescuela.setId(1L);
        autoescuela.setNombre("Autoescuela Test");
        autoescuela.setDireccion("Calle Mayor 1");
        autoescuela.setCiudad("Madrid");
        autoescuela.setTelefono("912345678");
        autoescuela.setEmail("test@autoescuela.com");
        autoescuela.setCapacidad(50);
        autoescuela.setRating(4.5f);
        autoescuela.setFechaApertura(LocalDate.of(2020, 1, 1));
        autoescuela.setActiva(true);
        autoescuela.setCoches(new ArrayList<>());
        autoescuela.setProfesores(new ArrayList<>());

        autoescuelaInDto = new AutoescuelaInDto();
        autoescuelaInDto.setNombre("Autoescuela Test");
        autoescuelaInDto.setCiudad("Madrid");
        autoescuelaInDto.setRating(4.5f);
        autoescuelaInDto.setActiva(true);

        autoescuelaOutDto = new AutoescuelaOutDto();
        autoescuelaOutDto.setId(1L);
        autoescuelaOutDto.setNombre("Autoescuela Test");
        autoescuelaOutDto.setCiudad("Madrid");

        autoescuelaDetailOutDto = new AutoescuelaDetailOutDto();
        autoescuelaDetailOutDto.setId(1L);
        autoescuelaDetailOutDto.setNombre("Autoescuela Test");
        autoescuelaDetailOutDto.setCiudad("Madrid");
        autoescuelaDetailOutDto.setCoches(new ArrayList<>());
        autoescuelaDetailOutDto.setProfesores(new ArrayList<>());
    }

    // ========== TEST ADD ==========
    @Test
    void testAdd_Success() {
        when(modelMapper.map(autoescuelaInDto, Autoescuela.class)).thenReturn(autoescuela);
        when(autoescuelaRepository.save(any(Autoescuela.class))).thenReturn(autoescuela);
        when(modelMapper.map(autoescuela, AutoescuelaDetailOutDto.class)).thenReturn(autoescuelaDetailOutDto);

        AutoescuelaDetailOutDto result = autoescuelaService.add(autoescuelaInDto);

        assertNotNull(result);
        assertEquals(autoescuelaDetailOutDto.getId(), result.getId());
        assertEquals(autoescuelaDetailOutDto.getNombre(), result.getNombre());
        verify(autoescuelaRepository, times(1)).save(any(Autoescuela.class));
    }

    // ========== TEST DELETE ==========
    @Test
    void testDelete_Success() throws AutoescuelaNotFoundException {
        when(autoescuelaRepository.findById(1L)).thenReturn(Optional.of(autoescuela));
        doNothing().when(autoescuelaRepository).delete(autoescuela);

        autoescuelaService.delete(1L);

        verify(autoescuelaRepository, times(1)).findById(1L);
        verify(autoescuelaRepository, times(1)).delete(autoescuela);
    }

    @Test
    void testDelete_NotFound() {
        when(autoescuelaRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(AutoescuelaNotFoundException.class, () -> {
            autoescuelaService.delete(999L);
        });
        verify(autoescuelaRepository, times(1)).findById(999L);
        verify(autoescuelaRepository, never()).delete(any(Autoescuela.class));
    }

    // ========== TEST FIND ALL ==========
    @Test
    void testFindAll_Success() {
        // Given
        List<Autoescuela> autoescuelas = Arrays.asList(autoescuela);
        List<AutoescuelaOutDto> autoescuelasDto = Arrays.asList(autoescuelaOutDto);
        Type listType = new TypeToken<List<AutoescuelaOutDto>>() {}.getType();

        when(autoescuelaRepository.findAll()).thenReturn(autoescuelas);
        // ✅ Corregido: usar el mismo tipo que el código
        when(modelMapper.map(eq(autoescuelas), eq(listType))).thenReturn(autoescuelasDto);

        // When
        List<AutoescuelaOutDto> result = autoescuelaService.findAll();

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(autoescuelaRepository, times(1)).findAll();
    }

    @Test
    void testFindAll_EmptyList() {
        List<AutoescuelaOutDto> emptyList = Collections.emptyList();
        Type listType = new TypeToken<List<AutoescuelaOutDto>>() {}.getType();

        when(autoescuelaRepository.findAll()).thenReturn(Collections.emptyList());
        when(modelMapper.map(eq(Collections.emptyList()), eq(listType))).thenReturn(emptyList);

        List<AutoescuelaOutDto> result = autoescuelaService.findAll();

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(autoescuelaRepository, times(1)).findAll();
    }

    // ========== TEST FIND BY ID ==========
    @Test
    void testFindById_Success() throws AutoescuelaNotFoundException {
        when(autoescuelaRepository.findById(1L)).thenReturn(Optional.of(autoescuela));

        AutoescuelaDetailOutDto result = autoescuelaService.findById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Autoescuela Test", result.getNombre());
        assertEquals("Madrid", result.getCiudad());
        verify(autoescuelaRepository, times(1)).findById(1L);
    }

    @Test
    void testFindById_NotFound() {
        when(autoescuelaRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(AutoescuelaNotFoundException.class, () -> {
            autoescuelaService.findById(999L);
        });
        verify(autoescuelaRepository, times(1)).findById(999L);
    }

    // ========== TEST FIND ALL BY ID ==========
    @Test
    void testFindAllById_Success() {
        List<Long> ids = Arrays.asList(1L, 2L);
        List<Autoescuela> autoescuelas = Arrays.asList(autoescuela);

        when(autoescuelaRepository.findAllById(ids)).thenReturn(autoescuelas);

        List<AutoescuelaDetailOutDto> result = autoescuelaService.findAllById(ids);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(autoescuelaRepository, times(1)).findAllById(ids);
    }

    @Test
    void testFindAllById_EmptyList() {
        List<Long> ids = Collections.emptyList();
        when(autoescuelaRepository.findAllById(ids)).thenReturn(Collections.emptyList());

        List<AutoescuelaDetailOutDto> result = autoescuelaService.findAllById(ids);

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    // ========== TEST MODIFY ==========
    @Test
    void testModify_Success() throws AutoescuelaNotFoundException {
        when(autoescuelaRepository.findById(1L)).thenReturn(Optional.of(autoescuela));
        // ✅ Corregido: usar doAnswer para que modifique el objeto
        doAnswer(invocation -> {
            AutoescuelaInDto source = invocation.getArgument(0);
            Autoescuela target = invocation.getArgument(1);
            target.setNombre(source.getNombre());
            target.setCiudad(source.getCiudad());
            return null;
        }).when(modelMapper).map(eq(autoescuelaInDto), eq(autoescuela));

        when(autoescuelaRepository.save(any(Autoescuela.class))).thenReturn(autoescuela);
        when(modelMapper.map(autoescuela, AutoescuelaDetailOutDto.class)).thenReturn(autoescuelaDetailOutDto);

        AutoescuelaDetailOutDto result = autoescuelaService.modify(1L, autoescuelaInDto);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(autoescuelaRepository, times(1)).findById(1L);
        verify(autoescuelaRepository, times(1)).save(any(Autoescuela.class));
    }

    @Test
    void testModify_NotFound() {
        when(autoescuelaRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(AutoescuelaNotFoundException.class, () -> {
            autoescuelaService.modify(999L, autoescuelaInDto);
        });
        verify(autoescuelaRepository, times(1)).findById(999L);
        verify(autoescuelaRepository, never()).save(any(Autoescuela.class));
    }

    // ========== TEST PATCH ==========
    @Test
    void testPatch_Success() throws AutoescuelaNotFoundException {
        Map<String, Object> patch = new HashMap<>();
        patch.put("nombre", "Autoescuela Actualizada");
        patch.put("rating", 4.8);
        patch.put("activa", false);

        when(autoescuelaRepository.findById(1L)).thenReturn(Optional.of(autoescuela));
        when(autoescuelaRepository.save(any(Autoescuela.class))).thenReturn(autoescuela);
        when(modelMapper.map(autoescuela, AutoescuelaDetailOutDto.class)).thenReturn(autoescuelaDetailOutDto);

        AutoescuelaDetailOutDto result = autoescuelaService.patch(1L, patch);

        assertNotNull(result);
        verify(autoescuelaRepository, times(1)).findById(1L);
        verify(autoescuelaRepository, times(1)).save(any(Autoescuela.class));
    }

    @Test
    void testPatch_AllFields() throws AutoescuelaNotFoundException {
        Map<String, Object> patch = new HashMap<>();
        patch.put("nombre", "Nueva Autoescuela");
        patch.put("direccion", "Nueva Direccion");
        patch.put("ciudad", "Barcelona");
        patch.put("telefono", "933333333");
        patch.put("email", "nuevo@email.com");
        patch.put("capacidad", 100);
        patch.put("rating", 5.0);
        patch.put("fechaApertura", "2024-01-01");
        patch.put("activa", false);

        when(autoescuelaRepository.findById(1L)).thenReturn(Optional.of(autoescuela));
        when(autoescuelaRepository.save(any(Autoescuela.class))).thenReturn(autoescuela);
        when(modelMapper.map(autoescuela, AutoescuelaDetailOutDto.class)).thenReturn(autoescuelaDetailOutDto);

        AutoescuelaDetailOutDto result = autoescuelaService.patch(1L, patch);

        assertNotNull(result);
        verify(autoescuelaRepository, times(1)).save(any(Autoescuela.class));
    }

    @Test
    void testPatch_NotFound() {
        Map<String, Object> patch = new HashMap<>();
        patch.put("nombre", "Test");

        when(autoescuelaRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(AutoescuelaNotFoundException.class, () -> {
            autoescuelaService.patch(999L, patch);
        });
        verify(autoescuelaRepository, never()).save(any(Autoescuela.class));
    }

    // ========== TEST FIND BY FILTROS ==========
    @Test
    void testFindByFiltros_AllFilters() {
        String ciudad = "Madrid";
        String rating = "4.5";
        Boolean activa = true;

        List<Autoescuela> autoescuelas = Arrays.asList(autoescuela);
        List<AutoescuelaOutDto> autoescuelasDto = Arrays.asList(autoescuelaOutDto);
        Type listType = new TypeToken<List<AutoescuelaOutDto>>() {}.getType();

        when(autoescuelaRepository.findByCiudadAndRatingAndActiva(ciudad, 4.5f, activa))
                .thenReturn(autoescuelas);
        // ✅ Corregido: usar eq() con los argumentos correctos
        when(modelMapper.map(eq(autoescuelas), eq(listType))).thenReturn(autoescuelasDto);

        List<AutoescuelaOutDto> result = autoescuelaService.findByFiltros(ciudad, rating, activa);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(autoescuelaRepository, times(1)).findByCiudadAndRatingAndActiva(ciudad, 4.5f, activa);
    }

    @Test
    void testFindByFiltros_OnlyCiudad() {
        String ciudad = "Madrid";
        List<Autoescuela> autoescuelas = Arrays.asList(autoescuela);
        List<AutoescuelaOutDto> autoescuelasDto = Arrays.asList(autoescuelaOutDto);
        Type listType = new TypeToken<List<AutoescuelaOutDto>>() {}.getType();

        when(autoescuelaRepository.findByCiudad(ciudad)).thenReturn(autoescuelas);
        when(modelMapper.map(eq(autoescuelas), eq(listType))).thenReturn(autoescuelasDto);

        List<AutoescuelaOutDto> result = autoescuelaService.findByFiltros(ciudad, null, null);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(autoescuelaRepository, times(1)).findByCiudad(ciudad);
    }

    @Test
    void testFindByFiltros_OnlyRating() {
        String rating = "4.5";
        List<Autoescuela> autoescuelas = Arrays.asList(autoescuela);
        List<AutoescuelaOutDto> autoescuelasDto = Arrays.asList(autoescuelaOutDto);
        Type listType = new TypeToken<List<AutoescuelaOutDto>>() {}.getType();

        when(autoescuelaRepository.findByRating(4.5f)).thenReturn(autoescuelas);
        when(modelMapper.map(eq(autoescuelas), eq(listType))).thenReturn(autoescuelasDto);

        List<AutoescuelaOutDto> result = autoescuelaService.findByFiltros(null, rating, null);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(autoescuelaRepository, times(1)).findByRating(4.5f);
    }

    @Test
    void testFindByFiltros_OnlyActiva() {
        Boolean activa = true;
        List<Autoescuela> autoescuelas = Arrays.asList(autoescuela);
        List<AutoescuelaOutDto> autoescuelasDto = Arrays.asList(autoescuelaOutDto);
        Type listType = new TypeToken<List<AutoescuelaOutDto>>() {}.getType();

        when(autoescuelaRepository.findByActiva(activa)).thenReturn(autoescuelas);
        when(modelMapper.map(eq(autoescuelas), eq(listType))).thenReturn(autoescuelasDto);

        List<AutoescuelaOutDto> result = autoescuelaService.findByFiltros(null, null, activa);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(autoescuelaRepository, times(1)).findByActiva(activa);
    }

    @Test
    void testFindByFiltros_NoFilters() {
        List<Autoescuela> autoescuelas = Arrays.asList(autoescuela);
        List<AutoescuelaOutDto> autoescuelasDto = Arrays.asList(autoescuelaOutDto);
        Type listType = new TypeToken<List<AutoescuelaOutDto>>() {}.getType();

        when(autoescuelaRepository.findAll()).thenReturn(autoescuelas);
        when(modelMapper.map(eq(autoescuelas), eq(listType))).thenReturn(autoescuelasDto);

        List<AutoescuelaOutDto> result = autoescuelaService.findByFiltros(null, null, null);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(autoescuelaRepository, times(1)).findAll();
    }

    // ========== TEST GET COCHES ==========
    @Test
    void testGetCoches_Success() {
        Coche coche = new Coche();
        coche.setId(1L);
        coche.setModelo("Deportivo");

        CocheOutDto cocheDto = new CocheOutDto();
        cocheDto.setId(1L);
        cocheDto.setModelo("Deportivo");

        List<Coche> coches = Arrays.asList(coche);

        when(cocheRepository.findCochesByAutoescuelaId(1L)).thenReturn(coches);
        when(modelMapper.map(coche, CocheOutDto.class)).thenReturn(cocheDto);

        List<CocheOutDto> result = autoescuelaService.getCoches(1L);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Deportivo", result.get(0).getModelo());
        verify(cocheRepository, times(1)).findCochesByAutoescuelaId(1L);
    }

    @Test
    void testGetCoches_EmptyList() {
        when(cocheRepository.findCochesByAutoescuelaId(1L)).thenReturn(Collections.emptyList());

        List<CocheOutDto> result = autoescuelaService.getCoches(1L);

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    // ========== TEST GET MATRICULAS ==========
    @Test
    void testGetMatriculas_Success() {
        Matricula matricula = new Matricula();
        matricula.setId(1L);

        MatriculaOutDto matriculaDto = new MatriculaOutDto();
        matriculaDto.setId(1L);

        List<Matricula> matriculas = Arrays.asList(matricula);

        when(matriculaRepository.findMatriculaByAutoescuelaId(1L)).thenReturn(matriculas);
        when(modelMapper.map(matricula, MatriculaOutDto.class)).thenReturn(matriculaDto);

        List<MatriculaOutDto> result = autoescuelaService.getMatriculas(1L);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(matriculaRepository, times(1)).findMatriculaByAutoescuelaId(1L);
    }

    // ========== TEST GET PROFESORES ==========
    @Test
    void testGetProfesores_Success() {
        Profesor profesor = new Profesor();
        profesor.setId(1L);
        profesor.setNombre("Carlos");

        ProfesorOutDto profesorDto = new ProfesorOutDto();
        profesorDto.setId(1L);
        profesorDto.setNombre("Carlos");

        List<Profesor> profesores = Arrays.asList(profesor);

        when(profesorRepository.findProfesoresByAutoescuelaId(1L)).thenReturn(profesores);
        when(modelMapper.map(profesor, ProfesorOutDto.class)).thenReturn(profesorDto);

        List<ProfesorOutDto> result = autoescuelaService.getProfesores(1L);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Carlos", result.get(0).getNombre());
        verify(profesorRepository, times(1)).findProfesoresByAutoescuelaId(1L);
    }

    // ========== TEST GET MATRICULAS COMPLETAS ==========
    @Test
    void testGetMatriculasCompletas_Success() {
        Matricula matricula = new Matricula();
        matricula.setId(1L);

        MatriculaOutDto matriculaDto = new MatriculaOutDto();
        matriculaDto.setId(1L);

        List<Matricula> matriculas = Arrays.asList(matricula);

        when(matriculaRepository.findMatriculaCompletasByAutoescuelaId(1L)).thenReturn(matriculas);
        when(modelMapper.map(matricula, MatriculaOutDto.class)).thenReturn(matriculaDto);

        List<MatriculaOutDto> result = autoescuelaService.getMatriculasCompletas(1L);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(matriculaRepository, times(1)).findMatriculaCompletasByAutoescuelaId(1L);
    }

    // ========== TEST GET ALUMNOS SUSPENSOS ==========
    @Test
    void testGetAlumnosSuspensos_Success() {
        Alumno alumno = new Alumno();
        alumno.setId(1L);
        alumno.setNombre("Juan");

        AlumnoOutDto alumnoDto = new AlumnoOutDto();
        alumnoDto.setId(1L);
        alumnoDto.setNombre("Juan");

        List<Alumno> alumnos = Arrays.asList(alumno);

        when(alumnoRepository.findAlumnosSuspensosByAutoescuela(1L)).thenReturn(alumnos);
        when(modelMapper.map(alumno, AlumnoOutDto.class)).thenReturn(alumnoDto);

        List<AlumnoOutDto> result = autoescuelaService.getAlumnosSuspensos(1L);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Juan", result.get(0).getNombre());
        verify(alumnoRepository, times(1)).findAlumnosSuspensosByAutoescuela(1L);
    }
}