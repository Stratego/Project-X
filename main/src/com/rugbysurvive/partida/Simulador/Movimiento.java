package com.rugbysurvive.partida.Simulador;

import com.rugbysurvive.partida.Jugador.ConPelota;
import com.rugbysurvive.partida.Jugador.Jugador;
import com.rugbysurvive.partida.Jugador.SinPelota;
import com.rugbysurvive.partida.tablero.Campo;

/**
 * Created by Victor on 27/03/14.
 */
public class Movimiento extends Accion {
    private int camino[][];
    private Jugador jugador;
    private int contador  = 0;//test

    public Movimiento(Jugador jugador,int camino[][])
    {
        this.camino = camino;
        this.jugador = jugador;
        this.contador = 0;
    }

    @Override
    public boolean simular() {
        System.out.println("mover");

        //System.out.println("Me muevo a la posición:"+camino[i][0]+"-"+camino[i][1]);


        if(jugador.getEstado().getEstadoAnterior() instanceof ConPelota)
        {

            /*Comprobaciones si esta con pelota el jugador*/
        }
        else
        {
            if(jugador.getEstado().getEstadoAnterior() instanceof SinPelota)
            {
                /*Comprobaciones si esta sin pelota el jugador*/
            }
        }


        this.jugador.colocar(Campo.getInstanciaCampo().getCasilla(this.camino[contador][1],this.camino[contador][0]));
        contador = contador + 1;

        //System.out.println(this.camino[contador][1]+"-"+this.camino[contador][0]);

        if(contador == this.camino.length)return true;//test
        return false;
    }


    @Override
    public void simularAnimacion() {

    }
}
