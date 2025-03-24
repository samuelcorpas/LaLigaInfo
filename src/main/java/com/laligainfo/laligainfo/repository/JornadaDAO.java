package com.laligainfo.laligainfo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.laligainfo.laligainfo.vo.Jornada; // Asegúrate de que la ruta es correcta
import java.util.List;

@Repository
public interface JornadaDAO extends JpaRepository<Jornada, Integer> {
    List<Jornada> findAll();

    Jornada findByNumero(int numero); // Método para obtener la jornada por su número

}
