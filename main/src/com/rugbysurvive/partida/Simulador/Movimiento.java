package com.rugbysurvive.partida.Simulador;

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

        this.jugador.colocar(Campo.getInstanciaCampo().getCasilla(this.camino[contador][1],this.camino[contador][0]));
        contador = contador + 1;

        System.out.println(this.camino[contador][1]+"-"+this.camino[contador][0]);

        if(contador == 5)return true;//test
        this.jugador.setEstado(new SinPelota());
        return false;
    }

    @Override
    public void simularAnimacion() {

    }
}
