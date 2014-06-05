package com.rugbysurvive.partida.arbitro;

import com.rugbysurvive.partida.Jugador.Jugador;
import com.rugbysurvive.partida.Simulador.Simulador;
import com.rugbysurvive.partida.tablero.Lado;

/**
 * Created by Victor on 25/04/14.
 * Regla que determina si un choque entre dos jugadores sin pelota
 * es falta o no en funcion de la posicion y si esta a la vista del arbitro.
 * En caso afirmativo se hace una meele o un penalty  en funcion de la posicion del jugador
 */
public class Choque extends Regla{
    /**
     * jugador que realiza el ataque aun jugador sin pelota
     */
    private Jugador jugadorAtacante;

    /**
     * jugador sin pelota que recibe un ataque
     */
    private Jugador jugadorDefensor;


    /**
     * constructor de la clase choque
     * @param jugadorAtacante jugador que realiza el ataque
     * @param jugadorDefensor jugador que recibe el ataque
     */
    public Choque(Jugador jugadorAtacante, Jugador jugadorDefensor) {
        this.jugadorAtacante = jugadorAtacante;
        this.jugadorDefensor = jugadorDefensor;
    }


    @Override
    public boolean arbitrar() {

        //si esta en la zona de meele la hace en funcion de los lados de los equipos
        if(this.arbitro.esSucesoVisible(this.jugadorDefensor.getPosicionX(),this.jugadorDefensor.getPosicionY())==true){
            Simulador.getInstance().reiniciar();
            if ((jugadorDefensor.getMiEquipo().getLado()== Lado.derecha && this.jugadorDefensor.getPosicionX()<=7)||
                    (jugadorDefensor.getMiEquipo().getLado()== Lado.izquierda && this.jugadorDefensor.getPosicionX()>=23)){
                this.posicionamiento.generarPenalty(this.jugadorDefensor.getMiEquipo(),jugadorDefensor.getPosicionX(),
                        jugadorDefensor.getPosicionY());
            }else{
                this.posicionamiento.generarMele(this.jugadorDefensor.getPosicionX(),this.jugadorDefensor.getPosicionY());
            }

            return true;
        }

        return false;
    }
}
