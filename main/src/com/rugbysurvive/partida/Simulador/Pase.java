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


        float contadorAux = 0;

        /*Posicion pixels jugador*/
        int pixelsJugadorX = (ConstantesJuego.ALTO_CASILLA*jugador.getPosicionX());
        int pixelsJugadorY = (ConstantesJuego.ALTO_CASILLA*jugador.getPosicionY());

        /*Destino pelota en pixels*/
        int pixelsDestinoX = this.posXObjetivo*ConstantesJuego.ALTO_CASILLA;
        int pixelsDestinoY = this.posYObjetivo*ConstantesJuego.ALTO_CASILLA;

        /*Distancia XY entre jugador y destino*/
        int pixelsDistanciaX = Math.abs(pixelsJugadorX-pixelsDestinoX);
        int pixelsDistanciaY = Math.abs(pixelsJugadorY-pixelsDestinoY);

        /*Incremento X Y para el bucle*/
        float incrementoX = 0;
        float incrementoY = 0;

        if(pixelsJugadorX > pixelsDestinoX)
        {
            incrementoX = ((float)pixelsJugadorX/(float)pixelsDestinoX) - 1;
        }
        else
        {
            incrementoX = ((float)pixelsDestinoX/(float)pixelsJugadorX) - 1;
        }

        if(pixelsJugadorY > pixelsDestinoY)
        {
            incrementoY = ((float)pixelsJugadorY/pixelsDestinoY) - 1;
        }
        else
        {
            incrementoY = ((float)pixelsDestinoY/pixelsJugadorY) - 1;
        }



        float incrementoXTotal = 0;
        float incrementoYTotal = 0;

        float contadorTotal = pixelsDistanciaX+pixelsDistanciaY;

        while (contadorAux<contadorTotal && pelotaAtrapada == false)
        {
            //Comprovem si hi ha un jugador
            int x = (int)Math.abs((pixelsJugadorX-incrementoXTotal)/64);
            int y = 0;
            if(jugador.getPosicionY() > this.posYObjetivo)
            {
                y =  (int)Math.abs((pixelsJugadorY-incrementoYTotal)/64);
            }
            else
            {
                y =  (int)Math.abs((pixelsJugadorY+incrementoYTotal)/64);
            }

            if(Campo.getInstanciaCampo().getCasilla(y,x).getJugador() != null)
            {
                //Si el jugador es de un equipo diferente, etonces interceptara la pelota
                if(jugador.getMiEquipo() != Campo.getInstanciaCampo().getCasilla(y,x).getJugador().getMiEquipo())
                {
                    //Campo.getInstanciaCampo().getCasilla(x, y).getJugador().setEstado(new ConPelota());
                    posXObjetivo = x;
                    posYObjetivo = y;

                    pelotaAtrapada = true;
                }
            }

            incrementoXTotal = incrementoXTotal + incrementoX;
            incrementoYTotal = incrementoYTotal + incrementoY;
            contadorAux = contadorAux + incrementoX + incrementoY;
        }

        /*En caso de que la pelota vaya a ser atrapada por un jugador, entonces se le cambia el estado a este jugador, sino, entonces se coloca la pelota en una casilla del campo*/
        if (pelotaAtrapada == true){
           Campo.getInstanciaCampo().getCasilla(posYObjetivo, posXObjetivo).getJugador().setEstado(new ConPelota());
        }
        else
        {
            if(Campo.getInstanciaCampo().getCasilla(posYObjetivo, posXObjetivo).getJugador() != null)
            {
                Campo.getInstanciaCampo().getCasilla(posYObjetivo, posXObjetivo).getJugador().setEstado(new ConPelota());
            }
            else
            {
                Campo.getInstanciaCampo().colocarPelota(posYObjetivo, posXObjetivo);
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
