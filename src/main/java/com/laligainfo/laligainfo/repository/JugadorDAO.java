package com.laligainfo.laligainfo.repository;


import com.laligainfo.laligainfo.vo.Equipo;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.laligainfo.laligainfo.vo.Jugadores; // poner carpetas de nuestro proyecto
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

@Repository
public interface JugadorDAO extends JpaRepository<Jugadores, Integer> {
    
    // Buscar jugador por nombre
    Jugadores findByNombre(String nombre);
    
    // Obtener información detallada del jugador
    Optional<Jugadores> findById(Integer id);

    // Buscar jugadores por coincidencia parcial en el nombre, ignorando mayúsculas y minúsculas
    List<Jugadores> findByNombreContainingIgnoreCase(String nombre);

    List<Jugadores> findByEquipoAndTitularTrue(Equipo equipo);

    @Modifying
    //@Transactional
    @Query("UPDATE Jugadores j SET j.estado = :nuevoEstado WHERE j.id_jugador = :jugadorId")
    void actualizarEstado(Integer jugadorId, String nuevoEstado);
    // Actualizar información de jugador (método save() maneja esto)
}
