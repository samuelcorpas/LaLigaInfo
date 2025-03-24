package com.laligainfo.laligainfo.vo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Equipo {
    @Id
    private String nombre;

    private String estadio;

    private String escudo;

    // Constructor vacío
    public Equipo() {}

    // Constructor con parámetros
    public Equipo(String nombre, String estadio, String escudo) {
        this.nombre = nombre;
        this.estadio = estadio;
        this.escudo = escudo;
    }

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstadio() {
        return estadio;
    }

    public void setEstadio(String estadio) {
        this.estadio = estadio;
    }

    public String getEscudo() {
        return escudo;
    }


    @Override
    public String toString() {
        return nombre;
    }
}
