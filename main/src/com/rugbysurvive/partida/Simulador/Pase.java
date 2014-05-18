package com.rugbysurvive.partida.Simulador;

import com.partido.GestorTurnos;
import com.rugbysurvive.partida.ConstantesJuego;
import com.rugbysurvive.partida.Dibujables.ElementoDibujable;
import com.rugbysurvive.partida.Dibujables.TipoDibujo;
import com.rugbysurvive.partida.Jugador.ConPelota;
import com.rugbysurvive.partida.Jugador.Jugador;
import com.rugbysurvive.partida.Jugador.SinPelota;
import com.rugbysurvive.partida.gestores.Procesos.Proceso;
import com.rugbysurvive.partida.gestores.Procesos.ProcesosContinuos;
import com.rugbysurvive.partida.tablero.Campo;

/**
 * Created by Victor on 27/03/14.
 */
public class Pase extends Accion implements Proceso {


    private static final int TIEMPO_VIDA_TEXTURA =100;
    int posXObjetivo;
    int posYObjetivo;
    private Jugador jugador;
    private boolean animacionInicializada;
    private boolean animacionActivada;
    private ElementoDibujable pelotaCogida;
    private int tiempo;
    private boolean animacionFinalizada;

    public Pase(Jugador jugador, int posX, int posY) {
        this.posXObjetivo = posX;
        this.posYObjetivo = posY;
        this.jugador = jugador;
        this.animacionFinalizada = false;
        this.animacionActivada = false;
    }


    @Override
    public boolean simular() {
        //jugador.setEstado(new SinPelota());

     if(!this.animacionInicializada) {
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
            Jugador jugador = Campo.getInstanciaCampo().getCasilla(posYObjetivo, posXObjetivo).getJugador();
            jugador.setEstado(new ConPelota(jugador));
            ProcesosContinuos.añadirProceso(this);
            this.animacionActivada = true;

        }
        else
        {
            if(Campo.getInstanciaCampo().getCasilla(posYObjetivo, posXObjetivo).getJugador() != null)
            {
                Jugador jugador = Campo.getInstanciaCampo().getCasilla(posYObjetivo, posXObjetivo).getJugador();
                jugador.setEstado(new ConPelota(jugador));
            }
            else
            {
                Campo.getInstanciaCampo().colocarPelota(posYObjetivo, posXObjetivo);
            }

        }


        System.out.println("Pase lanzado a la posición: "+this.posXObjetivo+"-"+this.posYObjetivo);
        jugador.setEstado(new SinPelota());

        if(this.animacionActivada) {
            return false;
        }
        else{
            return true;
        }
     }

     else{
          if(this.animacionFinalizada){
              return true;
          }
         return false;
     }

    }

    @Override
    public void simularAnimacion() {

    }


    @Override
    public boolean procesar() {
        if(!this.animacionInicializada) {

            this.tiempo = 0;
            this.pelotaCogida = new ElementoDibujable(TipoDibujo.interficieUsuario,"simulacion/rebrePassada.png");
            this.pelotaCogida.dibujar(ConstantesJuego.getHeight()/2-ConstantesJuego.TAMAÑO_PUÑO/2,
                                            ConstantesJuego.getWidth()/2-ConstantesJuego.TAMAÑO_PUÑO/2);
            this.animacionInicializada = true;
            Simulador.getInstance().setParado(false);
        }

        else{

            if(this.tiempo>= TIEMPO_VIDA_TEXTURA) {

                this.pelotaCogida .borrar();
                this.animacionFinalizada = true;
                return true;
            }
            this.tiempo++;
        }


        return false;
    }
}


