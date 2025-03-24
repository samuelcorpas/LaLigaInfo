package com.laligainfo.laligainfo.vo;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Administrador {

    @Id
    private String nombre;
    private String clave;

    public Administrador() {
    }

    public Administrador(String nombre, String clave) {
        this.nombre =nombre;
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Getter y Setter
    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
}
