package com.rugbysurvive.partida.Jugador;

import com.rugbysurvive.partida.Simulador.Accion;

/**
 * Created by Victor on 27/03/14.
 */
public class SinPelota implements Estado {


    public Accion generarAccion(Jugador jugador) {
        return null;

        /*Si se hace algun cambio de estado hacemos un jugador.setEstado(new Estado())*/
    }

    @Override
    public boolean generarAccion(Jugador jugador, int posX, int posY) {
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
