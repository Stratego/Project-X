package com.rugbysurvive.partida.arbitro;

import com.rugbysurvive.partida.Jugador.Jugador;
import com.rugbysurvive.partida.jugadores.Posicionamiento;
import com.rugbysurvive.partida.tablero.Lado;

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

        if(this.arbitro.esSucesoVisible(this.jugadorDefensor.getPosicionX(),this.jugadorDefensor.getPosicionY())==true){
            System.out.println("ha ocurrido un choque");

            if ((jugadorAtacante.getMiEquipo().getLado()== Lado.izquierda && this.jugadorDefensor.getPosicionX()>=7)||(jugadorAtacante.getMiEquipo().getLado()== Lado.derecha && this.jugadorDefensor.getPosicionX()<=22)){
                this.posicionamiento.generarPenalty(this.jugadorDefensor.getMiEquipo(),jugadorDefensor.getPosicionX(),jugadorDefensor.getPosicionY());
            }else{
                this.posicionamiento.generarMele(this.jugadorDefensor.getPosicionX(),this.jugadorDefensor.getPosicionY());
            }

            return true;
        }

        return false;
    }
}
