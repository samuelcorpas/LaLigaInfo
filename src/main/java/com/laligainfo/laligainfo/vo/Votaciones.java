package com.laligainfo.laligainfo.vo;

import jakarta.persistence.*;

import java.io.Serializable;                 // Para que la clase de clave compuesta sea Serializable


@Entity

public class Votaciones {
    @EmbeddedId
    private AuxVotaciones id;
    @ManyToOne
    @JoinColumn(name = "equipo", referencedColumnName = "nombre")
    private Equipo equipo; // Relación con Equipo

    @ManyToOne
    @JoinColumn(name = "jugador", referencedColumnName = "id_jugador")
    private Jugadores jugadores; // Relación con Equipo

    // Constructor vacío
    public Votaciones() {}

    // Constructor con parámetros
    public Votaciones(AuxVotaciones id, String correo, Equipo equipo, Jugadores jugador) {
        //this.jornada = jornada;
        //this.usuario = usuario;
        this.id = id;
        this.equipo = equipo;
        this.jugadores = jugador;
    }

    // Getters y setters
    public AuxVotaciones getId() {
        return id;
    }

    public void setId(AuxVotaciones id) {
        this.id = id;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public Jugadores getJugador() {
        return jugadores;
    }

    public void setJugador(Jugadores jugador) {
        this.jugadores = jugador;
    }

    @Override
    public String toString() {
        return "VotacionesVO{" +
                //"jornada=" + jornada +
                //", usuario='" + usuario + '\'' +
               // ", correo='" + correo + '\'' +
                ", equipo='" + equipo + '\'' +
                ", jugador='" + jugadores + '\'' +
                '}';
    }

}