package com.rugbysurvive.partida.arbitro;

import com.rugbysurvive.partida.elementos.ComponentesJuego;
import com.rugbysurvive.partida.gestores.Dibujable;
import com.rugbysurvive.partida.tablero.Campo;

/**
 * Created by aitor on 26/04/14.
 *
 * Personaje encargado de visualizar las jugadas.
 * Se mueve y gira durante el campo con una linia visual
 * concreta. Fuera de esta linia visual las reglas no se evaluan
 *
 */
public class Arbitro implements Dibujable{


   private static Arbitro arbitro = null;

    private Arbitro() {

    }
    public static Arbitro getInstancia() {

        if(arbitro == null) {
            arbitro = new Arbitro();
            return arbitro;
        }
        return arbitro;
    }


    /**
     * Mueve el arbitro de posicion y lo hace girar sobre si mismo
     * Solo puede moverse a casillas donde no hay ningun objeto o
     * jugador y se puede girar como quiera.
     */
    public void mover(){
        if(arbitro == null){
            arbitro = new Arbitro();

        }
        else {

            Campo campo = ComponentesJuego.getComponentes().getCampo();
            // CODIGO AQUI PARA EL MOVIMIENTO
        }
    }

    /**
     * Indica se una jugada ha sido visualizada o no por el arbitro
     * @param posicionX Posicion
     * @param posicionY
     * @return
     */
    public boolean esSucesoVisible(int posicionX,int posicionY){

        // CODIGO PARA INDICAR SI VISUALIZA LA JUGADA
        return false;
    }


    @Override
    public String getTextura() {
        return null;
    }

    @Override
    public int getPosicionX() {
        return 0;
    }

    @Override
    public int getPosicionY() {
        return 0;
    }
}
