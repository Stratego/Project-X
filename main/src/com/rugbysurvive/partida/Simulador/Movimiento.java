package com.rugbysurvive.partida.Simulador;

import com.rugbysurvive.partida.Jugador.Jugador;
import com.rugbysurvive.partida.Jugador.SinPelota;

/**
 * Created by Victor on 27/03/14.
 */
public class Movimiento implements Accion{
    private int camino[][];
    private Jugador jugador;


    public Movimiento(Jugador jugador,int camino[][])
    {
        this.camino = camino;
        this.jugador = jugador;
    }

    @Override
    public void simular() {

        for (int i = 0; i < this.camino.length; i++) {
           System.out.println("Me muevo a la posición:"+camino[i][0]+"-"+camino[i][1]);
        }

        this.jugador.setEstado(new SinPelota());
    }
}
