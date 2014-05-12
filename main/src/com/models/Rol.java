package com.models;

/**
 * Created by Manuel on 18/04/2014.
 */
public class Rol {
    private int id;
    private String nombre;
    private String descripcion;

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


    @Override
    public String toString() {
        return "tbObjeto{" +
                "id=" + id +
                ", nombre='" + nombre +
                ", descripcion='" + descripcion +'\'' +
                '}';
    }
}