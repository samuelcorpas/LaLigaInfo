package com.laligainfo.laligainfo.vo;

import jakarta.persistence.Table;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

@Entity
@Table(name = "jugador_votado")
public class JugadorVotado {
    @EmbeddedId
    private AuxJugador id;

    private Integer votos;
    // Constructor vacío
    public JugadorVotado() {}

    // Constructor con parámetros
    public JugadorVotado(AuxJugador id, Integer votos) {
        this.id = id;
        this.votos = votos;
    }

    // Getters y setters
    public AuxJugador getId() {
        return id;
    }

    public void setId(AuxJugador id) {
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
        return "JugadorVO{" +
                "id=" + id +
                '}';
    }
}
