package com.laligainfo.laligainfo.repository;

import com.laligainfo.laligainfo.vo.AuxEquipoVotado;
import com.laligainfo.laligainfo.vo.EquipoVotado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface EquipoVotadoDAO extends JpaRepository<EquipoVotado, AuxEquipoVotado> {
    Optional<EquipoVotado> findTopByOrderByVotosDesc();
}