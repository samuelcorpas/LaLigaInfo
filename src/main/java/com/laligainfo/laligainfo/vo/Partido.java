package com.laligainfo.laligainfo.vo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Partido {
    @Id
    private Integer id_partido;
    private Integer gol_local;
    private Integer gol_visitante;

    @ManyToOne
    @JoinColumn(name = "equipocasa", referencedColumnName = "nombre", nullable = false)
    private Equipo equipoCasa;

    @ManyToOne
    @JoinColumn(name = "equipofuera", referencedColumnName = "nombre", nullable = false)
    private Equipo equipoFuera;

    @ManyToOne
    @JoinColumn(name = "jornada", referencedColumnName = "numero", nullable = false)
    private Jornada jornada;
    private Integer dia;
    private Integer año;

    // Constructor vacío
    public Partido() {}

    // Constructor con parámetros
    public Partido(Integer id_partido, Integer gol_local, Integer gol_visitante, Equipo equipoCasa, Equipo equipoFuera, Jornada jornada, Integer dia, Integer año) {
        this.id_partido = id_partido;
        this.gol_local = gol_local;
        this.gol_visitante = gol_visitante;
        this.equipoCasa = equipoCasa;
        this.equipoFuera = equipoFuera;
        this.jornada = jornada;
        this.dia = dia;
        this.año = año;
    }

    // Getters y setters

    public Integer getId_partido() {
        return id_partido;
    }

    public void setId_partido(Integer id_partido) {
        this.id_partido = id_partido;
    }

    public Integer getGol_local() {
        return gol_local;
    }

    public void setGol_local(Integer gol_local) {
    this.gol_local = gol_local;
    }

    public Integer getGol_visitante() {
        return gol_visitante;
    }

    public void setGol_visitante(Integer gol_visitante) {
        this.gol_visitante = gol_visitante;
    }

    public Equipo getEquipoCasa() {
        return equipoCasa;
    }

    public void setEquipoCasa(Equipo equipoCasa) {
        this.equipoCasa = equipoCasa;
    }

    public Equipo getEquipoFuera() {return equipoFuera;}

    public void setEquipoFuera(Equipo equipoFuera) {
        this.equipoFuera = equipoFuera;
    }

    public Jornada getJornada() {
        return jornada;
    }

    public void setJornada(Jornada jornada) {
        this.jornada = jornada;
    }

    public Integer getDia() {
        return dia;
    }

    public void setDia(Integer dia) {
        this.dia = dia;
    }

    public Integer getAño() {
        return año;
    }

    public void setAño(Integer año) {
        this.año = año;
    }

    @Override
    public String toString() {
        return "PartidoVO{" +
               "golLocal=" + gol_local +
               ", golVisitante=" + gol_visitante +
               ", equipoCasa='" + equipoCasa + '\'' +
               ", equipoFuera='" + equipoFuera + '\'' +
               ", jornada=" + jornada +
               ", dia=" + dia +
               ", año=" + año +
               '}';
    }
}
