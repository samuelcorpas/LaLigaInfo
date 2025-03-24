package com.laligainfo.laligainfo.vo;

import java.io.Serializable;
import java.util.Objects;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class AuxJugador implements Serializable {
    @ManyToOne
    @JoinColumn(name = "jornada", referencedColumnName = "numero")
    private Jornada jornada;
    @ManyToOne
    @JoinColumn(name = "jugador", referencedColumnName = "id_jugador")
    private Jugadores jugador;

    // Constructor vacío
    public AuxJugador() {}

    // Constructor con parámetros
    public AuxJugador(Jornada jornada, Jugadores jugador) {
        this.jornada = jornada;
        this.jugador = jugador;
    }

    // Getters y setters
    public Jornada getJornada() {
        return jornada;
    }

    public void setJornada(Jornada jornada) {
        this.jornada = jornada;
    }

    public Jugadores getJugador() {
        return jugador;
    }

    public void setJugador(Jugadores jugador) {
        this.jugador = jugador;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AuxJugador)) return false;
        AuxJugador that = (AuxJugador) o;
        return Objects.equals(jornada, that.jornada) &&
                Objects.equals(jugador, that.jugador);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jornada, jugador);
    }
}
