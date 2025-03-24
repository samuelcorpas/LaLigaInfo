package com.laligainfo.laligainfo.vo;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class AuxActualidad implements Serializable {
    @ManyToOne
    @JoinColumn(name = "equipo", referencedColumnName = "nombre", insertable = false, updatable = false)
    private Equipo equipo; // Relación con Equipo

    @ManyToOne
    @JoinColumn(name = "jornada", referencedColumnName = "numero", insertable = false, updatable = false)
    private Jornada jornada; // Relación con Jornada

    public AuxActualidad() {
    }

    public AuxActualidad(Equipo equipo, Jornada jornada) {
        this.equipo = equipo;
        this.jornada = jornada;
    }

    // Getters y Setters
    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public Jornada getJornada() {
        return jornada;
    }

    public void setJornada(Jornada jornada) {
        this.jornada = jornada;
    }

    // Métodos equals y hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuxActualidad that = (AuxActualidad) o;
        return jornada == that.jornada && Objects.equals(equipo, that.equipo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(equipo, jornada);
    }
}
