package com.laligainfo.laligainfo.repository;

import com.laligainfo.laligainfo.vo.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.laligainfo.laligainfo.vo.Partido; // Asegúrate de que la ruta es correcta
import java.util.List;

@Repository
public interface PartidoDAO extends JpaRepository<Partido, Integer> {

    List<Partido> findByEquipoCasaOrEquipoFuera(Equipo equipoCasa, Equipo equipoFuera);
    List<Partido> findByEquipoCasaNombreOrEquipoFueraNombre(String equipoCasa, String equipoFuera);

    // Buscar partidos en una jornada específica
    List<Partido> findByJornadaNumero(Integer jornada);

    List<Partido> findByJornadaNumeroAndEquipoCasa(Integer jornada, Equipo equipoCasa);
    List<Partido> findByJornadaNumeroAndEquipoFuera(Integer jornada, Equipo equipoFuera);

}
