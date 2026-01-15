package com.svalero.autoescuela.repository;

import com.svalero.autoescuela.model.Matricula;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatriculaRepository extends CrudRepository<Matricula, Long> {
    List<Matricula> findAll();

    List<Matricula> findByModalidad(String modalidad);
    List<Matricula> findByTipoMatricula(String tipoMatricula);
    List<Matricula> findByHorasPracticas(int horasPracticas);
    List<Matricula> findByHorasTeoricas(int horasTeoricas);

    List<Matricula> findByModalidadAndTipoMatricula(String modalidad, String tipoMatricula);
    List<Matricula> findByModalidadAndHorasPracticas(String modalidad, int horasPracticas);
    List<Matricula> findByModalidadAndHorasTeoricas(String modalidad, int horasTeoricas);
    List<Matricula> findByTipoMatriculaAndHorasPracticas(String tipoMatricula, int horasPracticas);
    List<Matricula> findByTipoMatriculaAndHorasTeoricas(String tipoMatricula, int horasTeoricas);
    List<Matricula> findByHorasPracticasAndHorasTeoricas(int horasPracticas, int horasTeoricas);

    List<Matricula> findByModalidadAndTipoMatriculaAndHorasPracticas(String modalidad, String tipoMatricula, int horasPracticas);
    List<Matricula> findByModalidadAndTipoMatriculaAndHorasTeoricas(String modalidad, String tipoMatricula, int horasTeoricas);
    List<Matricula> findByModalidadAndHorasPracticasAndHorasTeoricas(String modalidad, int horasPracticas, int horasTeoricas);
    List<Matricula> findByTipoMatriculaAndHorasPracticasAndHorasTeoricas(String tipoMatricula, int horasPracticas, int horasTeoricas);

    List<Matricula> findByModalidadAndTipoMatriculaAndHorasPracticasAndHorasTeoricas(String modalidad, String tipoMatricula, int horasPracticas, int horasTeoricas);
}
