package com.models;

import java.util.List;

/**
 * Created by Manuel on 19/04/2014.
 */
public class Usuario {
    private int id;
    private String nombre;
    private List<Equipo> equipos;

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Equipo> getEquipos() {
        return equipos;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEquipos(List<Equipo> equipos) {
        this.equipos = equipos;
    }


    @Override
    public String toString() {
        return "tbObjeto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }

}
