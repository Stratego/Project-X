package com.rugbysurvive.partida.Jugador;

import com.rugbysurvive.partida.Simulador.Chute;
import com.rugbysurvive.partida.Simulador.Pase;
import com.rugbysurvive.partida.Simulador.Simulador;
import com.rugbysurvive.partida.gestores.Entrada.Entrada;

/**
 * Created by Victor on 27/03/14.
 */
public class ConPelota implements Estado {
    public boolean seleccionado = false;
    public boolean bloqueado = false;

    public boolean generarAccion(Jugador jugador) {

        Simulador.getInstance().añadirAccion(jugador.getAccion());

        return false;
    }

    @Override
    public boolean generarAccion(Jugador jugador, int posX, int posY) {

        return false;
    }

    @Override
    public boolean generarAccion(Jugador jugador, int posX, int posY, Entrada entrada) {


        if(entrada == Entrada.arrastrar)
        {
            jugador.setEstado(new EnMovimiento(8,this));
            System.out.println("<ME PONGO EN MOVIMIENTO>");
            return false;
        }
        else
        {
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
        }


        if(jugador.getAccion() != null)
        {
            Simulador.getInstance().añadirAccion(jugador.getAccion());
            jugador.setBloqueado(true);
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
    public Estado getEstado() {
        return this;
    }

    @Override
    public Estado getEstadoAnterior() {
        return null;
    }


    @Override
    public void setJugador(Jugador jugador) {

    }


}
