package com.rugbysurvive.partida.Simulador;

import com.rugbysurvive.partida.Jugador.ConPelota;
import com.rugbysurvive.partida.Jugador.Jugador;
import com.rugbysurvive.partida.Jugador.SinPelota;
import com.rugbysurvive.partida.jugadores.Posicionamiento;
import com.rugbysurvive.partida.tablero.Campo;
import com.rugbysurvive.partida.tablero.Casilla;

/**
 * Created by Aleix on 31/03/14.
 */
public class Chute extends Accion {

    int posXObjetivo;
    int posYObjetivo;
    private Jugador jugador;


    public Chute(Jugador jugador, int posX, int posY) {
        this.posXObjetivo = posX;
        this.posYObjetivo = posY;
        this.jugador = jugador;
    }

    @Override
    public boolean simular() {
        System.out.println("Chute hecho");
        //jugador.setEstado(new SinPelota());
        //return true;



        return false;
    }


    @Override
    public void simularAnimacion() {

    }
}