package com.rugbysurvive.partida.Simulador;

import com.rugbysurvive.partida.Jugador.Jugador;
import com.rugbysurvive.partida.Jugador.SinPelota;

/**
 * Created by Victor on 27/03/14.
 */
public class Pase extends Accion {

    int posXObjetivo;
    int posYObjetivo;
    private Jugador jugador;

    public Pase(Jugador jugador, int posX, int posY) {
        this.posXObjetivo = posX;
        this.posYObjetivo = posY;
        this.jugador = jugador;
    }


    @Override
    public boolean simular() {
        System.out.println("Pase lanzado a la posición: "+this.posXObjetivo+"-"+this.posYObjetivo);
        jugador.setEstado(new SinPelota());
        return true;
    }

    @Override
    public void simularAnimacion() {

    }
}
