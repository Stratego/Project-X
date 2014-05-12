package com.models;

/**
 * Created by Manuel on 19/04/2014.
 */
public class Habilidad {
    private int id;
    private String nombre;
    private String descripcion;
    private int valor;

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getValor() {
        return valor;
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

    public void setValor(int valor) {
        this.valor = valor;
    }


    @Override
    public String toString() {
        return "tbObjeto{" +
                "id=" + id +
                ", nombre='" + nombre +
                ", descripcion='" + descripcion +
                ", valor='" + valor + '\'' +
                '}';
    }
}
