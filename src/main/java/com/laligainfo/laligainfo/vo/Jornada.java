package com.laligainfo.laligainfo.vo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Jornada {
    @Id
    private Integer numero;

    // Constructor vacío
    public Jornada() {}

    // Constructor con parámetros
    public Jornada(Integer numero) {
        this.numero = numero;
    }

    // Getters y setters
    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "JornadaVO{" +
               "numero=" + numero +
               '}';
    }
}
