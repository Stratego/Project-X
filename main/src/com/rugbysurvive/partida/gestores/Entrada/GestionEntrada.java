package com.rugbysurvive.partida.gestores.Entrada;

/**
 * Created by Victor on 24/03/14.
 */
public interface GestionEntrada {

    /**
     * Indicamos el imput que se ha realizado y su posicion dentro del tablero
     * @param entrada tipo de entrada
     * @param posX eje x donde se ha realizado la acciion /entrada
     * @param posY eje y donde se ha realizado la acciion /entrada
     */
    public void accionEntrada(Entrada entrada,float posX, float posY);


    /**
     * Indicamos el imput que se ha realizado
     * @param entrada tipo de entrada
     */
    public void accionEntrada(Entrada entrada);


}
