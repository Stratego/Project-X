package com.rugbysurvive.partida.Simulador;

import com.rugbysurvive.partida.ConstantesJuego;
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

        boolean pelotaAtrapada = false;

        int contadorTotal = ((ConstantesJuego.ALTO_CASILLA*jugador.getPosicionX()+34)+(ConstantesJuego.ALTO_CASILLA*jugador.getPosicionY()+34)+(ConstantesJuego.ALTO_CASILLA*this.posXObjetivo+34)+(ConstantesJuego.ALTO_CASILLA*this.posYObjetivo+34));
        int contadorAux = 0;

        int x = ConstantesJuego.ALTO_CASILLA*jugador.getPosicionX()+34 / ConstantesJuego.ALTO_CASILLA*this.posXObjetivo+34;
        int y = ConstantesJuego.ALTO_CASILLA*jugador.getPosicionY()+34 / ConstantesJuego.ALTO_CASILLA*this.posYObjetivo+34;
        int casillaX = (jugador.getPosicionX()-x)%ConstantesJuego.ALTO_CASILLA;
        int casillaY = (jugador.getPosicionY()-y)%ConstantesJuego.ALTO_CASILLA;

        while (contadorAux<contadorTotal && pelotaAtrapada == false){
            //Comprovem si hi ha un jugador
            if(Campo.getInstanciaCampo().getCasilla(casillaX,casillaY).getJugador() != null){

                //Comprovem si es o no del nostre equip
                //if(jugador.getMiEquipo() != Campo.getInstanciaCampo().getCasilla(casillaX,casillaY).getJugador().getMiEquipo()){

                Campo.getInstanciaCampo().getCasilla(casillaX, casillaY).getJugador().setEstado(new ConPelota());
                pelotaAtrapada = true;
            }
            contadorAux = contadorAux + casillaX + casillaY;
        }

        if (pelotaAtrapada = false){
            if (Campo.getInstanciaCampo().getCasilla(posXObjetivo, posYObjetivo).getJugador() == null){
                Campo.getInstanciaCampo().colocarPelota(posXObjetivo, posYObjetivo);
            }
        }

        System.out.println("Pase lanzado a la posición: "+this.posXObjetivo+"-"+this.posYObjetivo);
        jugador.setEstado(new SinPelota());
        return true;
    }

    @Override
    public void simularAnimacion() {

    }
}
