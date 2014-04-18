package com.rugbysurvive.partida.elementos;

import com.rugbysurvive.partida.Jugador.Jugador;
import com.rugbysurvive.partida.jugadores.Equipo;

/**
 * Created by aitor on 18/04/14.
 */
public class Marcador {

    private int puntuacionEquipo1;
    private int puntuacionEquipo2;
    private static Marcador marcador;
    private Equipo equipo1;
    private Equipo equipo2;

    public Marcador(Equipo equipo,Equipo equipo2){

        this.puntuacionEquipo1 = 0;
        this.puntuacionEquipo2 = 0;
    }

    public Marcador getInstanciaMarcador()
    {
        return marcador;
    }

    public void sumarPuntuacion(int puntuacion,Jugador jugador)
    {
        this.puntuacionEquipo1  = this.puntuacionEquipo1 + puntuacion;
    }




}
