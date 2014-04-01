package com.rugbysurvive.partida.Jugador;

import com.rugbysurvive.partida.Simulador.*;

import java.util.Objects;

/**
 * Created by Victor on 27/03/14.
 */
public class SinPelota implements Estado {

    @Override
    public Accion generarAccion(Jugador jugador) {
        return null;

        /*Si se hace algun cambio de estado hacemos un jugador.setEstado(new Estado())*/
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
    public Accion getAccion() {
        return null;
    }

    @Override
    public void setAccion(Objects accion) {

    }

    @Override
    public void setJugador(Jugador jugador) {

    }
}
