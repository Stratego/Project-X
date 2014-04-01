package com.rugbysurvive.partida.Simulador;

import com.rugbysurvive.partida.Jugador.*;

/**
 * Created by Aleix on 31/03/14.
 */
public class Chute implements Accion {

    private String[][] objetivo;
    private Jugador jugador;

    public Chute(String[][] objetivo, Jugador jugador) {
        this.objetivo = objetivo;
        this.jugador = jugador;
    }

    @Override
    public void simular() {
        System.out.println("Chute hecho");
        jugador.setEstado(new SinPelota());
    }
}