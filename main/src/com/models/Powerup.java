package com.models;

/**
 * Created by Manuel on 28/04/14.
 */
public class Powerup extends Objeto {

    private int habilidad;
    private int efecto;

    public int getHabilidad() {
        return habilidad;
    }

    public int getEfecto() {
        return efecto;
    }

    public void setHabilidad(int habilidad) {
        this.habilidad = habilidad;
    }

    public void setEfecto(int efecto) {
        this.efecto = efecto;
    }


    @Override
    public String toString() {
        return "tbObjeto{}";
    }
}