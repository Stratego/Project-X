package com.rugbysurvive.partida.Jugador;

import com.rugbysurvive.partida.Simulador.Accion;
import com.rugbysurvive.partida.Simulador.Simulador;

/**
 * Created by Victor on 27/03/14.
 */
public class ConPelota implements Estado {
    Jugador jugador = null;
    boolean seleccionado = false;
    boolean bloqueado = false;

    @Override
    public Accion generarAccion(Jugador jugador) {
        Simulador simulador = Simulador.getInstance();

        simulador.addAccionesSimulador(jugador.getAccion());

        return null;
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
        this.seleccionado = seleccionado;
    }

    @Override
    public boolean getBloqueado() {
        return false;
    }

    @Override
    public void setBloqueado(boolean bloqueado) {
        this.bloqueado = bloqueado;
    }

    @Override
    public Accion getAccion() {
        return null;
    }

    @Override
    public void setAccion(Accion accion) {

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
