package com.rugbysurvive.partida.Jugador;

import com.rugbysurvive.partida.gestores.Entrada.Entrada;

/**
 * Created by Victor on 27/03/14.
 */
public interface Estado {
    public Jugador jugador = null;
    public boolean paseOChute = true;


    public boolean generarAccion(Jugador jugador, int posX, int posY);
    public boolean generarAccion(Jugador jugador, int posX, int posY, Entrada entrada);

    public Jugador getJugador();
    public void setJugador(Jugador jugador);

    public boolean getSeleccionado();
    public void setSeleccionado(boolean seleccionado);

    public boolean getBloqueado();
    public void setBloqueado(boolean bloqueado);

    public boolean getPaseOChute();
    public void setPaseOChute(boolean paseOChute);

}
