package com.rugbysurvive.partida.Simulador;

import com.rugbysurvive.partida.Jugador.Jugador;
import com.rugbysurvive.partida.Jugador.SinPelota;

import java.util.List;

/**
 * Created by Victor on 27/03/14.
 */
public class Movimiento implements Accion{
    private List camino;
    private Jugador jugador;


    public Movimiento(List camino, Jugador jugador)
    {
        this.camino = camino;
        this.jugador = jugador;
    }

    @Override
    public void simular() {
        String posicion[];
        for (int i = 0; i < this.camino.size(); i++) {
           posicion = (String[]) this.camino.get(i);
           System.out.println("Me muevo a la posición:"+posicion[0]+"-"+posicion[1]);
        }

        this.jugador.setEstado(new SinPelota());
    }
}
