package com.rugbysurvive.partida.Jugador.extras;

import com.rugbysurvive.partida.Jugador.DireccionJugador;
import com.rugbysurvive.partida.Jugador.Jugador;

/**
 * Created by aitor on 16/04/14.
 */
public class IndicadorMovimientos {
    int camino[][];
    Jugador jugador;
    DireccionJugador direccion;

    public IndicadorMovimientos(Jugador jugador,int camino[][])
    {
        this.jugador = jugador;
        this.camino = camino;
        this.direccion = this.jugador.getDireccion();
    }

    public void dibujar(){

        for(int i=0; i<camino.length; i++)
        {
            this.camino[i][0] = -1;
            this.camino[i][1] = -1;

        }
    }
}
