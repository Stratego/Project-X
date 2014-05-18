package com.rugbysurvive.partida.Jugador;



import com.rugbysurvive.partida.Simulador.Accion;
import com.rugbysurvive.partida.Simulador.Simulador;
import com.rugbysurvive.partida.gestores.Entrada.Entrada;

/**
 * Created by Victor on 27/03/14.
 */
public class SinPelota implements Estado {
    public boolean seleccionado = false;
    public boolean bloqueado = false;

    public Jugador jugador;

    /*public boolean generarAccion(Jugador jugador) {

        Simulador.getInstance().addAccionesSimulador(jugador.getAccion());

        return false;
    }*/


    public Accion generarAccion(Jugador jugador) {
        return null;

        /*Si se hace algun cambio de estado hacemos un jugador.setEstado(new Estado())*/
    }

    @Override
    public boolean generarAccion(Jugador jugador, int posX, int posY) {
        return false;
    }

    @Override
    public boolean generarAccion(Jugador jugador, int posX, int posY, Entrada entrada) {

        if (entrada == Entrada.arrastrar){
            int distancia = jugador.getResistencia()/10;
            if(distancia <=0)
            {
                distancia = 1;
            }

            jugador.setEstado(new EnMovimiento(distancia,this));
            return false;
        }

        if (jugador.getAccion() != null)
        {
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
