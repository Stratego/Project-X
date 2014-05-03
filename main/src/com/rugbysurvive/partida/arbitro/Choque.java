package com.rugbysurvive.partida.arbitro;

import com.rugbysurvive.partida.Jugador.Jugador;
import com.rugbysurvive.partida.jugadores.Posicionamiento;

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
            if (this.jugadorDefensor.getPosicionY()>=7 && this.jugadorDefensor.getPosicionY()<=22 ){
                this.posicionamiento.generarMele(this.jugadorDefensor.getPosicionX(),this.jugadorDefensor.getPosicionY());
            }else{
                //GENERAR CHUTE
            }

            return true;
        }

        return false;
    }
}
