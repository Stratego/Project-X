package com.rugbysurvive.partida.Simulador;

import com.rugbysurvive.partida.Jugador.Jugador;
import com.rugbysurvive.partida.Jugador.SinPelota;

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
    }

    @Override
    public boolean simular() {
        contador++;//test
        System.out.println("mover");
        for (int i = 0; i < this.camino.length; i++) {
           //System.out.println("Me muevo a la posición:"+camino[i][0]+"-"+camino[i][1]);
        }
        if(contador == 5)return true;//test
        this.jugador.setEstado(new SinPelota());
        return false;
    }

    @Override
    public void simularAnimacion() {

    }
}
