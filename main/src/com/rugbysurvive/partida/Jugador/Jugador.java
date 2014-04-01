package com.rugbysurvive.partida.Jugador;

import com.rugbysurvive.partida.Simulador.*;

/**
 * Created by Victor on 27/03/14.
 */
public class Jugador {

    private Estado estado;
    private Accion accion = null;
    /*Le asignamos aquí tambien la posicion en la que se encuentra el jugador?*/

    public void generarAccion()
    {
        this.estado.generarAccion(this);
    }

    public Estado getEstado()
    {
        return this.estado;
    }

    public void setEstado(Estado estado)
    {
        this.estado = estado;
    }

    public Accion getAccion()
    {
        return this.accion;
    }

    public void setAccion(Accion accion)
    {
        this.accion = accion;
    }

    //public void recibirImput();
}
