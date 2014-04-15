package com.rugbysurvive.partida.jugadores;

import com.rugbysurvive.partida.Jugador.Jugador;

/**
 * Created by Victor on 2/04/14.
 */
public class PosicionInicial {

    public PosicionInicial(Jugador jugador,int posicionX,int posicionY)
    {
        this.jugador = jugador;
        this.posicionX = posicionX;
        this.posicionY = posicionY;
    }
    public Jugador jugador;
    public int posicionX;
    public int posicionY;
}
