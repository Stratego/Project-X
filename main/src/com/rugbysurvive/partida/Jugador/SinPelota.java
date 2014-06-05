package com.rugbysurvive.partida.Jugador;



import com.rugbysurvive.partida.Simulador.Accion;
import com.rugbysurvive.partida.Simulador.Simulador;
import com.rugbysurvive.partida.gestores.Entrada.Entrada;

/**
 * Created by Victor on 27/03/14.
 * Gestiona el estado SinPelota, si un
 * jugador se encuentra en este estado
 * solo se le permitira cambiar al estado EnMovimiento
 */
public class SinPelota implements Estado {

    // estados
    public boolean seleccionado = false;
    public boolean bloqueado = false;

    public Jugador jugador;



    public Accion generarAccion(Jugador jugador) {
        return null;

        /*Si se hace algun cambio de estado hacemos un jugador.setEstado(new Estado())*/
    }

    @Override
    public boolean generarAccion(Jugador jugador, int posX, int posY) {
        return false;
    }

    /**
     *cambio al estado movimiento uan vez indicado el jugador
     *
     * @param jugador Jugador que genera la Accion
     * @param posX Posición X del jugador
     * @param posY Posicion Y del jugador
     * @param entrada Tipo de evento que recibe el jugador
     * @return Boolean indicacion de accion finalizada
     */
    @Override
    public boolean generarAccion(Jugador jugador, int posX, int posY, Entrada entrada) {

        if (entrada == Entrada.arrastrar){
            int distancia = jugador.getResistencia()/10;
            if(distancia <=2) {
                distancia = 3;
            }

            jugador.setEstado(new EnMovimiento(distancia,this));
            return false;
        }

        if (jugador.getAccion() != null) {
            Simulador.getInstance().añadirAccion(jugador.getAccion());
            jugador.setBloqueado(true);
            return true;
        }

        this.jugador = jugador;

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
        return false;
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
