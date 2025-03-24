package com.laligainfo.laligainfo.vo;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class AuxComentario implements Serializable {
    @ManyToOne
    @JoinColumn(name = "usuario", referencedColumnName = "correo", insertable = false, updatable = false)
    private Usuario usuario; // Relación con Equipo
    private String mensaje;

    // Constructor vacío
    public AuxComentario() {
    }

    // Constructor con parámetros
    public AuxComentario(Usuario usuario, String mensaje) {
        this.usuario = usuario;
        this.mensaje = mensaje;
    }

    // Getters y setters
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    // Implementación de equals() y hashCode() para la clave compuesta
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuxComentario that = (AuxComentario) o;
        return Objects.equals(usuario, that.usuario) && Objects.equals(mensaje, that.mensaje);
    }
}

