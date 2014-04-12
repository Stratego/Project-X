package com.models;

import java.util.ArrayList;

/**
 * Created by adria on 11/04/14.
 */
public class Equipo {
    private ArrayList<Jugador> plantilla;
    private String rutaEscudo;
    private String nombreEquipo;
    private ArrayList<Jugador> bankillo;

    public Equipo(ArrayList<Jugador> plantilla, String rutaEscudo, String nombreEquipo, ArrayList<Jugador> bankillo) {
        this.plantilla = plantilla;
        this.rutaEscudo = rutaEscudo;
        this.nombreEquipo = nombreEquipo;
        this.bankillo = bankillo;
    }

    public ArrayList<Jugador> getPlantilla() {
        return plantilla;
    }

    public void setPlantilla(ArrayList<Jugador> plantilla) {
        this.plantilla = plantilla;
    }

    public String getRutaEscudo() {
        return rutaEscudo;
    }

    public void setRutaEscudo(String rutaEscudo) {
        this.rutaEscudo = rutaEscudo;
    }

    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }

    public ArrayList<Jugador> getBankillo() {
        return bankillo;
    }

    public void setBankillo(ArrayList<Jugador> bankillo) {
        this.bankillo = bankillo;
    }
}
