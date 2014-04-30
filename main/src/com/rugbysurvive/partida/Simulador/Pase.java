package com.rugbysurvive.partida.Simulador;

import com.rugbysurvive.partida.Jugador.ConPelota;
import com.rugbysurvive.partida.Jugador.Jugador;
import com.rugbysurvive.partida.Jugador.SinPelota;
import com.rugbysurvive.partida.tablero.Campo;

/**
 * Created by Victor on 27/03/14.
 */
public class Pase extends Accion {

    int posXObjetivo;
    int posYObjetivo;
    private Jugador jugador;

    public Pase(Jugador jugador, int posX, int posY) {
        this.posXObjetivo = posX;
        this.posYObjetivo = posY;
        this.jugador = jugador;
    }


    @Override
    public boolean simular() {
        //jugador.setEstado(new SinPelota());

        int contadorTotal = ((64*jugador.getPosicionX()+34)+(64*jugador.getPosicionY()+34)+(64*this.posXObjetivo+34)+(64*this.posYObjetivo+34));
        int contadorAux = 0;

        int x = 64*jugador.getPosicionX()+34 / 64*this.posXObjetivo+34;
        int y = 64*jugador.getPosicionY()+34 / 64*this.posYObjetivo+34;
        int casillaX = (jugador.getPosicionX()-x)%64;
        int casillaY = (jugador.getPosicionY()-y)%64;

        while (contadorAux<=contadorTotal){
            //Comprovem si hi ha un jugador
            if(Campo.getInstanciaCampo().getCasilla(casillaX,casillaY).getJugador() != null){
                //Comprovem si es o no del nostre equip
                //if(jugador.getMiEquipo() != Campo.getInstanciaCampo().getCasilla(casillaX,casillaY).getJugador().getMiEquipo()){
                Campo.getInstanciaCampo().getCasilla(casillaX, casillaY).getJugador().setEstado(new ConPelota());
            }
        contadorAux = contadorAux + casillaX + casillaY;
        }

        System.out.println("Pase lanzado a la posición: "+this.posXObjetivo+"-"+this.posYObjetivo);
        jugador.setEstado(new SinPelota());
        return true;
    }

    @Override
    public void simularAnimacion() {

    }
}
