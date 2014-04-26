package com.rugbysurvive.partida.arbitro;

import com.rugbysurvive.partida.Jugador.Jugador;

/**
 * Created by aitor on 25/04/14.
 * Regla que determina si un choque entre dos jugadores
 * es falta o no.
 */
public class Choque extends Regla{

    private Jugador jugadorAtacante;
    private Jugador jugadorDefensor;

    public Choque(Jugador jugadorAtacante, Jugador jugadorDefensor) {
        super();
        this.jugadorAtacante = jugadorAtacante;
        this.jugadorDefensor = jugadorDefensor;
    }


    @Override
    public boolean arbitrar() {

       // this.arbitro.esSucesoVisible(posicionX,posicionY de la jugada) {
            // CODIGO AQUI
        //}

        return false;
    }
}
