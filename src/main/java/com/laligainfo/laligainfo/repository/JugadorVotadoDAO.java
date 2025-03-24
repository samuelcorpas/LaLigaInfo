package com.laligainfo.laligainfo.repository;

import com.laligainfo.laligainfo.vo.AuxJugador;
import com.laligainfo.laligainfo.vo.JugadorVotado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.laligainfo.laligainfo.vo.Partido; // Asegúrate de que la ruta es correcta

import java.util.Optional;


@Repository
public interface JugadorVotadoDAO extends JpaRepository<JugadorVotado, AuxJugador> {
    Optional<JugadorVotado> findTopByOrderByVotosDesc();
}