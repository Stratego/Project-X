package com.models;


/**
 * Created by Manuel on 7/04/14.
 */
public class Objeto {
    private int id;
    private String nombre;
    private String descripcion;
    private String textura;

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getTextura() {
        return textura;
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

    public void setTextura(String textura) {
        this.textura = textura;
    }


    @Override
    public String toString() {
        return "tbObjeto{" +
                "id=" + id +
                ", nombre='" + nombre +
                ", descripcion='" + descripcion +
                ", textura='" + descripcion + '\'' +
                '}';
    }
}