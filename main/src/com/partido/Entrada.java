package com.partido;

/**
 * Created by Victor on 24/03/14.
 */
public interface Entrada {

    /**
     * Indicamos el imput que se ha realizado y su posicion dentro del tablero
     * Imput tipo de entrada
     * posX eje x donde se ha realizado la acciion /entrada
     * posY eje y donde se ha realizado la acciion /entrada
     */
    public void accionEntrada(Imput imput,float posX, float posY);


    public void accionEntrada(Imput imput);


    /**
     * indicamos que el elemento se ha seleccionado y su posicion en el tablero
     */
    public boolean esSeleccionado(float posX, float posY);
}
