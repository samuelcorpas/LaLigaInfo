package com.laligainfo.laligainfo.vo;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class AuxVotaciones implements Serializable {
    @ManyToOne
    @JoinColumn(name = "usuario", referencedColumnName = "correo", insertable = false, updatable = false)
    private Usuario usuario; // Relación con Equipo

    @ManyToOne
    @JoinColumn(name = "jornada", referencedColumnName = "numero", insertable = false, updatable = false)
    private Jornada jornada; // Relación con Jornada

    // Constructor vacío
    public AuxVotaciones() {}

    // Constructor con parámetros
    public AuxVotaciones(Usuario usuario, Jornada jornada) {
        this.usuario = usuario;
        this.jornada = jornada;
    }

    // Getters y setters
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Jornada getJornada() {
        return jornada;
    }

    public void setJornada(Jornada jornada) {
        this.jornada = jornada;
    }

    // equals y hashCode para la clave compuesta
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AuxVotaciones)) return false;
        AuxVotaciones that = (AuxVotaciones) o;
        return Objects.equals(usuario, that.usuario) &&
               Objects.equals(jornada, that.jornada);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuario, jornada);
    }
}
