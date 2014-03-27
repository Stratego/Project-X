package com.partido;

/**
 * Created by Victor on 24/03/14.
 */
public interface Entrada {

    /**
     * Indicamos el imput que se ha realizado y su posicion dentro del tablero
     * Imput tipo de entrada
     * @param posX eje x donde se ha realizado la accion /entrada
     * @param posY eje y donde se ha realizado la accion /entrada
     */
    public void accionEntrada(Imput imput,float posX, float posY);

    /**
     * Indicamos el imput que se ha realizado
     * @param imput tipo de entrada
     */
    public void accionEntrada(Imput imput);


    /**
     * indicamos que el elemento se ha seleccionado comparando su posicion en el tablero
     * @param posX eje x donde se ha realizado la accion /entrada
     * @param posY eje y donde se ha realizado la accion /entrada
     */
    public boolean esSeleccionado(float posX, float posY);
}
