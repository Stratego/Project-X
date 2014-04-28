package com.rugbysurvive.partida.Simulador;


import com.rugbysurvive.partida.tablero.Campo;

/**
 * Created by Victor on 27/03/14.
 */
public abstract class Accion{
    private Campo campo;
    protected boolean finalizado = false;

    public Accion()
    {
        this.campo = Campo.getInstanciaCampo();
    }
    /**
     * Cada vez que se llama esta funcion se realiza un proceso de simulacion
     * Una accion puede contener tantas simulaciones como sean necesarias para
     * completar la accion.
     */
    public abstract boolean simular();

    /**
     * Cada vez que se llama esta funcion se realiza un proceso de
     * animacion.Las animmaciones se realizan entre dos simulaciones.
     */
    public abstract void simularAnimacion();

    public boolean getFinalizado(){return this.finalizado;}

}
