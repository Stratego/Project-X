package com.rugbysurvive.partida.tablero;

import com.rugbysurvive.partida.gestores.Entrada.*;

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

    private Entrada entrada;  // USA LA INFORMACION QUE YA DISPONES!!!
    /**
     * indicara si el elemento esta selecionado
     */
    private boolean selecionado;


    /**
     * Constructor del elemento boton
     *
     * @param posX posicion x en el tablero
     * @param posY posicion y en el tablero
     * @param entrada tipo de boton que sera
     */
    public Boton(float posX, float posY, Entrada entrada) {
        this.posY = posY;
        this.posX = posX;
        this.entrada = entrada;
    }



    /**
     * indicamos que el elemento se ha seleccionado y su posicion en el tablero
     * @param posX eje x donde se ha realizado la accion /entrada
     * @param posY eje y donde se ha realizado la accion /entrada
     */
    public boolean esSeleccionado(float posX, float posY) {

        if (this.posX >= posX && this.posX <= 64){
            if (this.posX >= posY && this.posY <= 64){
                selecionado=true;
            }else {
                selecionado=false;
            }
        }
        return selecionado;
    }

       public Entrada obtenerEntrada()
       {
            return entrada;
        }


}
