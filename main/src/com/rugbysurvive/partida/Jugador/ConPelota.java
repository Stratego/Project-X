package com.rugbysurvive.partida.Jugador;

import com.rugbysurvive.partida.Simulador.Chute;
import com.rugbysurvive.partida.Simulador.Pase;
import com.rugbysurvive.partida.Simulador.Simulador;

/**
 * Created by Victor on 27/03/14.
 */
public class ConPelota implements Estado {
    public boolean seleccionado = false;
    public boolean bloqueado = false;

    public boolean generarAccion(Jugador jugador) {

        Simulador.getInstance().addAccionesSimulador(jugador.getAccion());

        return false;
    }

    @Override
    public boolean generarAccion(Jugador jugador, int posX, int posY) {
        if(jugador.getEstado().getPaseOChute() == true)
        {
            jugador.setAccion(new Pase(jugador, posX, posY));
            System.out.println("La PASOOOOO!!!");
        }
        else
        {
            jugador.setAccion(new Chute(jugador, posX, posY));
            System.out.println("La CHUTOOO!!!");
        }


        if(jugador.getAccion() != null)
        {
            Simulador.getInstance().addAccionesSimulador(jugador.getAccion());
            jugador.getEstado().setBloqueado(true);
            return true;
        }

        return false;
    }

    @Override
    public Jugador getJugador() {
        return null;
    }

    @Override
    public boolean getSeleccionado() {
        return this.seleccionado;
    }

    @Override
    public void setSeleccionado(boolean seleccionado) {
        this.seleccionado = seleccionado;
    }

    @Override
    public boolean getBloqueado() {
        return this.bloqueado;
    }

    @Override
    public void setBloqueado(boolean bloqueado) {
        this.bloqueado = bloqueado;
    }

    @Override
    public boolean getPaseOChute() {
        return true;
    }

    @Override
    public void setPaseOChute(boolean paseOChute) {

    }


    @Override
    public void setJugador(Jugador jugador) {

    }


}
