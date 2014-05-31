package com.models;

import java.util.List;

/**
 * Created by Manuel on 7/04/14.
 */
public class Jugador {
    private int id;
    private String nombre;
    private Rol rol;
    private List<Habilidad> habilidades;
    private List<Objeto> objetos;
    private List<Powerup> powerups;
    private int posX;
    private int posY;
    private String extrasVisuales;

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Rol getRol() {
        return rol;
    }

    public List<Habilidad> getHabilidades() {
        return habilidades;
    }

    public List<Powerup> getPowerups() {
        return powerups;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public String getExtrasVisuales() {
        return extrasVisuales;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public void setHabilidades(List<Habilidad> habilidades) {
        this.habilidades = habilidades;
    }

    public void setObjetos(List<Objeto> objetos) {
        this.objetos = objetos;
    }

    public void setPowerups(List<Powerup> powerups) {
        this.powerups = powerups;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public void setExtrasVisuales(String extrasVisuales) {
        this.extrasVisuales = extrasVisuales;
    }



    @Override
    public String toString() {
        return "Jugador{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", posX=" + posX +
                ", posY=" + posY +
                '}';
    }
}