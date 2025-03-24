package com.laligainfo.laligainfo.repository;

import com.laligainfo.laligainfo.vo.Jugadores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.laligainfo.laligainfo.vo.Equipo; // poner carpetas de nuestro proyecto
import java.util.List;
@Repository
public interface EquipoDAO extends JpaRepository<Equipo, String> {
    // Obtener información general de un equipo por su nombre
    Equipo findByNombre(String nombre);
    // Obtener la clasificación actual
    //List<Equipo> findAllByOrderByPuntosDesc(); // Supongamos que tienes un campo "puntos" para la clasificación
    List<Equipo> findByNombreContainingIgnoreCase(String nombre);

    // El método save() se puede usar para actualizar los detalles de un equipo
}
