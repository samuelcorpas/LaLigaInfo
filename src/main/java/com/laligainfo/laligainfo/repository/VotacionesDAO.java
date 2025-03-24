package com.laligainfo.laligainfo.repository;

import com.laligainfo.laligainfo.vo.Jornada;
import com.laligainfo.laligainfo.vo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.laligainfo.laligainfo.vo.Votaciones;
import com.laligainfo.laligainfo.vo.AuxVotaciones;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


@Repository
public interface VotacionesDAO extends JpaRepository<Votaciones, AuxVotaciones> {

    // Registrar una votación
    Votaciones save(Votaciones votacion);

    // Obtener resultados de las votaciones
    //List<Votaciones> findAllByJornada(int jornada);
    // Verificar si el usuario ya ha votado en la jornada para un jugador
    /*@Query("SELECT COUNT(v) > 0 FROM Votaciones v WHERE v.id.jornada = :jornada AND v.id.usuario = :usuario AND v.jugador IS NOT NULL")
    boolean existsByJornadaAndUsuarioForJugador(@Param("jornada") Jornada jornada, @Param("usuario") Usuario usuario);

    // Verificar si el usuario ya ha votado en la jornada para un equipo
    @Query("SELECT COUNT(v) > 0 FROM Votaciones v WHERE v.id.jornada = :jornada AND v.id.usuario = :usuario AND v.equipo IS NOT NULL")
    boolean existsByJornadaAndUsuarioForEquipo(@Param("jornada") Jornada jornada, @Param("usuario") Usuario usuario);
*/
    // Eliminar votaciones (método deleteById() ya existe)
}

