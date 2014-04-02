package com.rugbysurvive.partida.Jugador;
<<<<<<< HEAD
import com.rugbysurvive.partida.Simulador.*;


=======
import com.rugbysurvive.partida.Simulador.Accion;
>>>>>>> 6c355688e8ef14893dd6b0fa0e567ff991c48511

/**
 * Created by Victor on 27/03/14.
 */
public class Holligan implements Estado {
    @Override
    public Accion generarAccion(Jugador jugador) {
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

<<<<<<< HEAD
  //  @Override
    //public void setAccion(Objects accion) {
=======
    @Override
    public void setAccion(Accion accion) {

    }

    @Override
    public boolean getPaseOChute() {
        return false;
    }

    @Override
    public void setPaseOChute(boolean paseOChute) {
>>>>>>> 6c355688e8ef14893dd6b0fa0e567ff991c48511

  //  }

    @Override
    public void setJugador(Jugador jugador) {

    }
}
