package com.rugbysurvive.partida.elementos.objetos;

import com.rugbysurvive.partida.Jugador.Jugador;

/**
 * Created by aitor on 10/04/14.
 * Objeto que se puede colocar en una casilla del campo y que reacciona ante un evento
 * sea alguna interaccion con otro objeto o por decision del jugador
 */
public abstract class ObjetoCampo {
    int posicionX;
    int posicionY;


    public abstract void efecto(Jugador jugador);

}
