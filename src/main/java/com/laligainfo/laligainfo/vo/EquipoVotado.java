package com.laligainfo.laligainfo.vo;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "equipo_votado")
public class EquipoVotado {
    @EmbeddedId
    private AuxEquipoVotado id;

    private Integer votos;

    // Constructor vacío
    public EquipoVotado() {}

    // Constructor con parámetros
    public EquipoVotado(AuxEquipoVotado id, Integer votos) {
        this.id = id;
        this.votos = votos;
    }

    // Getters y setters
    public AuxEquipoVotado getId() {
        return id;
    }

    public void setId(AuxEquipoVotado id) {
        this.id = id;
    }

    public Integer getVotos() {
        return votos;
    }

    public void setVotos(Integer votos) {
        this.votos = votos;
    }

    public void incrementarVotos() {
        this.votos += 1;
    }
    @Override
    public String toString() {
        return "EquipoVotadoVO{" +
                "id=" + id +
                ", votos=" + votos +
                '}';
    }
}
