package com.uab.lis.rugby.database.models;

/**
 * Created by Adria on 12/05/2014.
 */
public class HistorialPartido {
    private int idEquipo1;
    private int puntuacionEquipo1;
    private int idEquipo2;
    private int puntuacionEquipo2;

    public HistorialPartido() {
    }

    public int getIdEquipo1() {
        return idEquipo1;
    }

    public void setIdEquipo1(int idEquipo1) {
        this.idEquipo1 = idEquipo1;
    }

    public int getPuntuacionEquipo1() {
        return puntuacionEquipo1;
    }

    public void setPuntuacionEquipo1(int puntuacionEquipo1) {
        this.puntuacionEquipo1 = puntuacionEquipo1;
    }

    public int getIdEquipo2() {
        return idEquipo2;
    }

    public void setIdEquipo2(int idEquipo2) {
        this.idEquipo2 = idEquipo2;
    }

    public int getPuntuacionEquipo2() {
        return puntuacionEquipo2;
    }

    public void setPuntuacionEquipo2(int puntuacionEquipo2) {
        this.puntuacionEquipo2 = puntuacionEquipo2;
    }
}
