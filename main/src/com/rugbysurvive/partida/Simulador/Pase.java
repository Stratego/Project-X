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


        int contadorAux = 0;

        /*Posicion pixels jugador*/
        int pixelsJugadorX = (ConstantesJuego.ALTO_CASILLA*jugador.getPosicionX())+34;
        int pixelsJugadorY = (ConstantesJuego.ALTO_CASILLA*jugador.getPosicionY())+34;

        /*Destino pelota en pixels*/
        int pixelsDestinoX = this.posXObjetivo*ConstantesJuego.ALTO_CASILLA;
        int pixelsDestinoY = this.posYObjetivo*ConstantesJuego.ALTO_CASILLA;

        /*Distancia XY entre jugador y destino*/
        int pixelsDistanciaX = pixelsJugadorX-pixelsDestinoX;
        int pixelsDistanciaY = pixelsJugadorY-pixelsDestinoY;

        /*Incremento X Y para el bucle*/
        int incrementoX = pixelsJugadorX/pixelsDestinoX;
        int incrementoY = pixelsJugadorY/pixelsDestinoY;

        int contadorTotal = pixelsJugadorX+pixelsJugadorY+pixelsDistanciaX+pixelsDistanciaY;

        while (contadorAux<contadorTotal && pelotaAtrapada == false){
            //Comprovem si hi ha un jugador
            if(Campo.getInstanciaCampo().getCasilla((pixelsJugadorX-incrementoX)/64,(pixelsJugadorY-incrementoY)/64).getJugador() != null){

                //Comprovem si es o no del nostre equip
                //if(jugador.getMiEquipo() != Campo.getInstanciaCampo().getCasilla(casillaX,casillaY).getJugador().getMiEquipo()){

                Campo.getInstanciaCampo().getCasilla((pixelsJugadorX-incrementoX)/64, (pixelsJugadorY-incrementoY)/64).getJugador().setEstado(new ConPelota());
                pelotaAtrapada = true;
            }
            contadorAux = contadorAux + incrementoX + incrementoX;
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
