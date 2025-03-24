package com.laligainfo.laligainfo.vo;

import java.io.Serializable;
import java.util.Objects;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class AuxEquipoVotado implements Serializable {
    @ManyToOne
    @JoinColumn(name = "jornada", referencedColumnName = "numero")
    private Jornada jornada;
    @ManyToOne
    @JoinColumn(name = "equipo", referencedColumnName = "nombre")
    private Equipo equipo;

    // Constructor vacío
    public AuxEquipoVotado() {}

    // Constructor con parámetros
    public AuxEquipoVotado(Jornada jornada, Equipo equipo) {
        this.jornada = jornada;
        this.equipo = equipo;
    }

    // Getters y setters
    public Jornada getJornada() {
        return jornada;
    }

    public void setJornada(Jornada jornada) {
        this.jornada = jornada;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AuxEquipoVotado)) return false;
        AuxEquipoVotado that = (AuxEquipoVotado) o;
        return Objects.equals(jornada, that.jornada) &&
                Objects.equals(equipo, that.equipo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jornada, equipo);
    }
}
