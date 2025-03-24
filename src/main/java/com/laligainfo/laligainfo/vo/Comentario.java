package com.laligainfo.laligainfo.vo;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

@Entity
public class Comentario {

    @EmbeddedId
    private AuxComentario id;

    // Constructor vacío
    public Comentario() {}

    // Constructor con parámetros
    public Comentario(AuxComentario id) {
        this.id = id;
    }

    // Getters y setters
    public AuxComentario getId() {
        return id;
    }

    public void setId(AuxComentario id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Comentario{" +
                "id=" + id +
                '}';
    }
}
