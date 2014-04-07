package com.rugbysurvive.partida.Jugador;

import com.rugbysurvive.partida.Simulador.Simulador;

import java.util.List;

/**
 * Created by Victor on 27/03/14.
 */
public class EnMovimiento implements Estado {

    /*Lista de los movimientos que hara un jugador*/
    List movimientos;


    public boolean generarAccion(Jugador jugador) {
        Simulador simulador = Simulador.getInstance();

        simulador.addAccionesSimulador(jugador.getAccion());

        return false;
    }

    @Override
    public boolean generarAccion(Jugador jugador, int posX, int posY) {
        Simulador simulador = Simulador.getInstance();

        simulador.addAccionesSimulador(jugador.getAccion());

        return false;
    }

    @Override
    public Jugador getJugador() {
        return null;
    }

    @Override
    public boolean getSeleccionado() {
        return false;
    }

    @Override
    public void setSeleccionado(boolean seleccionado) {

    }

    @Override
    public boolean getBloqueado() {
        return false;
    }

    @Override
    public void setBloqueado(boolean bloqueado) {

    }


    @Override
    public boolean getPaseOChute() {
        return false;
    }

    @Override
    public void setPaseOChute(boolean paseOChute) {

    }


    @Override
    public void setJugador(Jugador jugador) {

    }
}
