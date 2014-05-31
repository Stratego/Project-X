package com.models;

import java.util.List;

/**
 * Created by Manuel on 7/04/14.
 */
public class Equipo {
    private long id = -1;
    private String nombre;
    private List<Jugador> jugadores;
    private String escudo;
    private String equipacion;
    private List<Jugador> alineacion;

    public long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEscudo() {
        return escudo;
    }

    public String getEquipacion() {
        return equipacion;
    }

    public List<Jugador> getAlineacion() {
        return alineacion;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEscudo(String escudo) {
        this.escudo = escudo;
    }

    public void setEquipacion(String equipacion) {
        this.equipacion = equipacion;
    }

    public void setAlineacion(List<Jugador> alineacion) {
        this.alineacion = alineacion;
    }

    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }


    @Override
    public String toString() {
        return "tbEquipos{" +
                "id=" + id +
                "nombre=" + nombre +
                "escudo=" + escudo +
                ", equipacion='" + equipacion + '\'' +
                '}';
    }
}