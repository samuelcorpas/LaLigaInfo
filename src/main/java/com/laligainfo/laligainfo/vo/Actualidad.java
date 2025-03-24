package com.laligainfo.laligainfo.vo;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Actualidad {

    @EmbeddedId
    private AuxActualidad id; // Mantener la clave compuesta


    private int puntos;
    private int goles_favor;
    private int goles_contra;

    // Constructores, Getters y Setters
    public Actualidad() {
    }

    public Actualidad(AuxActualidad id, int puntos, int goles_favor, int goles_contra) {
        this.id = id;
        this.puntos = puntos;
        this.goles_favor = goles_favor;
        this.goles_contra = goles_contra;
    }

    // Getters y Setters
    public AuxActualidad getId() {
        return id;
    }

    public void setId(AuxActualidad id) {
        this.id = id;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public int getGoles_favor() {
        return goles_favor;
    }

    public void setGoles_favor(int goles_favor) {
        this.goles_favor = goles_favor;
    }

    public int getGoles_contra() {
        return goles_contra;
    }

    public void setGoles_contra(int goles_contra) {
        this.goles_contra = goles_contra;
    }
}
