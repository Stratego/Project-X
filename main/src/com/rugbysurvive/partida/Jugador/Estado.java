package com.rugbysurvive.partida.Jugador;

import com.rugbysurvive.partida.Simulador.*;

<<<<<<< HEAD


=======
>>>>>>> 6c355688e8ef14893dd6b0fa0e567ff991c48511
/**
 * Created by Victor on 27/03/14.
 */
public interface Estado {
    public Jugador jugador = null;
    public boolean seleccionado = false;
    public boolean bloqueado = false;
    public Accion accio = null;
    public boolean paseOChute = false;


    public Accion generarAccion(Jugador jugador);

    public Jugador getJugador();
    public void setJugador(Jugador jugador);

    public boolean getSeleccionado();
    public void setSeleccionado(boolean seleccionado);

    public boolean getBloqueado();
    public void setBloqueado(boolean bloqueado);

    public Accion getAccion();
<<<<<<< HEAD
    //public void setAccion(Objects accion);
=======
    public void setAccion(Accion accion);

    public boolean getPaseOChute();
    public void setPaseOChute(boolean paseOChute);
>>>>>>> 6c355688e8ef14893dd6b0fa0e567ff991c48511

}
