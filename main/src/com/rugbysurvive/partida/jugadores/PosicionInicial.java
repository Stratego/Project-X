package com.rugbysurvive.partida.jugadores;

import com.rugbysurvive.partida.Jugador.Jugador;

/**
 * Created by Victor on 2/04/14.
 * Clase que agrupa un conjunto de atributos
 * clave y que van unidos . Facilita
 * la gestion del posicionamiento del jugador
 * dentro de ciertas clases.
 */
public class PosicionInicial {

    public Jugador jugador;
    public int posicionX;
    public int posicionY;


    public PosicionInicial(Jugador jugador,int posicionX,int posicionY)  {
        this.jugador = jugador;
        this.posicionX = posicionX;
        this.posicionY = posicionY;
    }

    public Jugador getJugador() {return this.jugador;}
    public int getPosicionX() {return this.posicionX;}
    public int getPosicionY() {return this.posicionY;}
}
