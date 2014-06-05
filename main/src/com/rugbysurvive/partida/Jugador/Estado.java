package com.rugbysurvive.partida.Jugador;

import com.rugbysurvive.partida.gestores.Entrada.Entrada;

/**
 * Created by Victor on 27/03/14.
 * Interfaz que debe contener cualquier estado
 * de un jugador.
 * Contiene las funciones basicas necesarias para
 * construir un estado que sea indpendiente y que permita
 * una gestion individualizada.
 */
public interface Estado {
    public Jugador jugador = null;
    public boolean paseOChute = true;
    public Estado estadoAnterior = null;

    /**
     * Interfaz por el cual se debe generar una accion segun el estado implementado
     ** @param jugador Jugador que genera la Accion
     * @param posX Posición X del jugador
     * @param posY Posicion Y del jugador
     * @return Indicacion de accion finalizada
     */
    public boolean generarAccion(Jugador jugador, int posX, int posY);

    /**
    *Interfaz por el cual se debe generar una accion segun el estado implementado
    * Añade la opcion de saber que tipo de entrada ha recibido el jugador
    *
    * @param jugador Jugador que genera la Accion
    * @param posX Posición X del jugador
    * @param posY Posicion Y del jugador
    * @param entrada Tipo de evento que recibe el jugador
    * @return Indicacion de accion finalizada
     * */

     public boolean generarAccion(Jugador jugador, int posX, int posY, Entrada entrada);

    public Jugador getJugador();
    public void setJugador(Jugador jugador);

    public boolean getSeleccionado();
    public void setSeleccionado(boolean seleccionado);

    public boolean getBloqueado();
    public void setBloqueado(boolean bloqueado);

    public boolean getPaseOChute();
    public void setPaseOChute(boolean paseOChute);

    public Estado getEstado();

    public Estado getEstadoAnterior();

}
