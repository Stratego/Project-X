package com.rugbysurvive.partida.Simulador;

import com.rugbysurvive.partida.Jugador.*;

/**
 * Created by Victor on 27/03/14.
 */
public class Pase implements Accion {

    private String[][] objetivo;
    private Jugador jugador;

    public Pase(String[][] objetivo, Jugador jugador) {
        this.objetivo = objetivo;
        this.jugador = jugador;
    }


    @Override
    public void simular() {
        System.out.println("Pase lanzado a la posición: "+this.objetivo[0][0]+"-"+this.objetivo[0][1]);
        jugador.setEstado(new SinPelota());
    }
}
