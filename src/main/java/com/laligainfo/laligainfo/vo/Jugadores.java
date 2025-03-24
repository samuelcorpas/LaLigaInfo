package com.laligainfo.laligainfo.vo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Jugadores {
    @Id
    private Integer id_jugador;
    private String nombre;
    private Integer peso;
    private Integer altura;
    private String nacionalidad;
    private String posicion;
    private String estado;
    private String imagen;
    private Boolean titular;
    @ManyToOne // o @JoinColumn si no se usa directamente un @ManyToOne
    @JoinColumn(name = "equipo", referencedColumnName = "nombre") // Asegúrate de que "equipo" está relacionado con el nombre del equipo.
    private Equipo equipo;

    // Constructor vacío
    public Jugadores() {}

    // Constructor con parámetros
    public Jugadores(Integer id_jugador, String nombre, Integer peso, Integer altura, String nacionalidad, String posicion, Equipo equipo, String estado, Boolean titular, String imagen) {
        this.id_jugador = id_jugador;
        this.nombre = nombre;
        this.peso = peso;
        this.altura = altura;
        this.nacionalidad = nacionalidad;
        this.posicion = posicion;
        this.equipo = equipo;
        this.titular = titular;
        this.estado = estado;
        this.imagen = imagen;
    }

    // Getters y setters
    public Integer getId_jugador() {
        return id_jugador;
    }

    public void setId_jugador(Integer id_jugador) {
        this.id_jugador = id_jugador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getPeso() {
        return peso;
    }

    public void setPeso(Integer peso) {
        this.peso = peso;
    }

    public Integer getAltura() {
        return altura;
    }

    public void setAltura(Integer altura) {
        this.altura = altura;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Boolean getTitular() {
        return titular;
    }

    public void setTitular(Boolean titular) {
        this.titular = titular;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    @Override
    public String toString() {
        return "JugadorVO{" +
               "idJugador=" + id_jugador +
               ", nombre='" + nombre + '\'' +
               ", peso=" + peso +
               ", altura=" + altura +
               ", nacionalidad='" + nacionalidad + '\'' +
               ", posicion='" + posicion + '\'' +
               ", equipo='" + equipo + '\'' +
               '}';
    }
}
