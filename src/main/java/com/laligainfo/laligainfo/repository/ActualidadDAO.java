package com.laligainfo.laligainfo.repository;

import com.laligainfo.laligainfo.vo.Actualidad;
import com.laligainfo.laligainfo.vo.AuxActualidad;
import com.laligainfo.laligainfo.vo.Equipo;
import com.laligainfo.laligainfo.vo.Jornada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ActualidadDAO extends JpaRepository<Actualidad, AuxActualidad> {
    Optional<Actualidad> findById(AuxActualidad id);

    List<Actualidad> findByIdJornadaNumero(int jornada);

    List<Actualidad> findByIdEquipoAndIdJornadaNumero(Equipo equipo, Integer jornada);

}

