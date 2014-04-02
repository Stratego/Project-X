package com.rugbysurvive.partida.tablero;


import com.rugbysurvive.partida.ConstantesJuego;
import com.rugbysurvive.partida.gestores.Dibujable;

import com.rugbysurvive.partida.gestores.Dibujante;
import com.rugbysurvive.partida.gestores.Entrada.Entrada;
import com.rugbysurvive.partida.gestores.Entrada.GestionEntrada;


/**
 * Definicion de la casilla, elemento basico del que se compone el tablero de juego
 * Created by Victor on 24/03/14.
 */
public class Casilla implements GestionEntrada {
    /**
     * posicion x en el tablero
     */
    private float posX;

    /**
     * posicion y en el tablero
     */
    private float posY;


    /**
     * indicara si el elemento esta seleccionado
     */
    private boolean selecionado;



    /**
     * Constructor de  casilla
     * @param posX posicion X del la casilla
     * @param posY posicion Y de la casilla
     */
    public Casilla (float posX, float posY) {
        this.posY = posY;
        this.posX = posX;



    }



    @Override
    public void accionEntrada(Entrada entrada, float posX, float posY) {



    }

    @Override
    public void accionEntrada(Entrada entrada) {


    }






    public float getPosX() {
        return posX;
    }

    public void setPosX(float posX) {
        this.posX = posX;
    }

    public float getPosY() {
        return posY;
    }

    public void setPosY(float posY) {
        this.posY = posY;
    }



}