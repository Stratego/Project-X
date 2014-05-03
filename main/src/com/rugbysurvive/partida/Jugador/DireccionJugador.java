package com.rugbysurvive.partida.Jugador;

/**
 * Created by aitor on 16/04/14.
 */
public enum DireccionJugador {
    izquierda,derecha,arriba,abajo,frontal;

    public static DireccionJugador getRandom() {
        return values()[(int) (Math.random() * values().length)];
    }
}
