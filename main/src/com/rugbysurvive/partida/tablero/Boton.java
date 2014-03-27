package com.rugbysurvive.partida.tablero;

import com.rugbysurvive.partida.gestores.Entrada.GestionEntrada;

/**
 * Clase que define la posicion y comportamiento de un boron dentro del tablero de juego
 * Created by Victor on 24/03/14.
 */
public class Boton {

    /**
     * posicion x en el tablero
     */
    private float posX;

    /**
     * posicion y en el tablero
     */
    private float posY;

    /private entrada; !!!!! // USA LA INFORMACION QUE YA DISPONES!!!
    /**
     * indicara si el elemento esta selecionado
     */
    private boolean selecionado;


    /**
     * Constructor del elemento boton
     *
     * @param posX posicion x en el tablero
     * @param posY posicion y en el tablero
     * @param nombre nombre del boton
     */
    public Boton(float posX, float posY, Entrada entrada) {
        this.posY = posY;
        this.posX = posX;
        this.nombre = nombre;
    }



    /**
     * indicamos que el elemento se ha seleccionado y su posicion en el tablero
     * @param posX eje x donde se ha realizado la accion /entrada
     * @param posY eje y donde se ha realizado la accion /entrada
     */
    public boolean esSeleccionado(float posX, float posY) {
      // TIENES QUE COMPARAR DE TOCAR LA IMAAGEN (ES UN CUAdrada qe va desde a posicion inicial)
        //PERO QUE TIENE UNA ALARGADA Y ANCHURA SEGUN EL TAMAÑAO DE LA TEXTURA
    }

       public Entrada obtenerEntrada()  !!!! //PIENSA COMO GENERAR FUNCIONES QUE AYUDEN
       {
            return entrada;
        }


}
