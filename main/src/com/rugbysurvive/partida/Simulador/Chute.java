package com.rugbysurvive.partida.Simulador;

import com.rugbysurvive.partida.Jugador.Jugador;
import com.rugbysurvive.partida.Jugador.SinPelota;

/**
 * Created by Aleix on 31/03/14.
 */
public class Chute implements Accion {

    int posXObjetivo;
    int posYObjetivo;
    private Jugador jugador;


    public Chute(Jugador jugador, int posX, int posY) {
        this.posXObjetivo = posX;
        this.posYObjetivo = posY;
        this.jugador = jugador;
    }

    @Override
    public void simular() {
        System.out.println("Chute hecho");
        jugador.setEstado(new SinPelota());
    }
}