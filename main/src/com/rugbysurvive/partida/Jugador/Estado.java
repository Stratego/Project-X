package com.rugbysurvive.partida.Jugador;

/**
 * Created by Victor on 27/03/14.
 */
public interface Estado {
    public Jugador jugador = null;
    public boolean seleccionado = false;
    public boolean bloqueado = false;
    public boolean paseOChute = true;


    public boolean generarAccion(Jugador jugador, int posX, int posY);

    public Jugador getJugador();
    public void setJugador(Jugador jugador);

    public boolean getSeleccionado();
    public void setSeleccionado(boolean seleccionado);

    public boolean getBloqueado();
    public void setBloqueado(boolean bloqueado);

    public boolean getPaseOChute();
    public void setPaseOChute(boolean paseOChute);

}
