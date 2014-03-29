package com.rugbysurvive.partida.tablero;

import com.rugbysurvive.partida.gestores.Entrada.GestionEntrada;
import com.rugbysurvive.partida.gestores.Entrada.*;

import java.util.ArrayList;

/**
 * Clase que crea y define el comportamiente del terreno del juego
 * Created by Victor on 24/03/14.
 */
public class Campo implements GestionEntrada {

    /**
     * posicion x en el tablero
     */
    private float posX;

    /**
     * posicion y en el tablero
     */
    private float posY;


    /**
     * indicara si el elemento esta selecionado
     */
    private boolean selecionado;


    /**
     *Coleccion de casillas que tenemos en nuestra pantalla
     */
    //ArrayList <Casilla> casillas= new ArrayList <Casilla>();

    Casilla [][] casillas= new Casilla [20][30];

    /**
     * definicion del elemento casilla que compone nuestro tablero
     */
    //private Casilla casilla;

    public Campo() {
        dibujarTablero();
    }
/*
    public Campo(float posX, float posY) {
        this.posY = posY;
        this.posX = posX;
    }
*/
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

    /**
     * Dibujamos el tablero de juego
     */
    public void dibujarTablero(){
        float x = 0;
        float y = 0;
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 30; j++) {

                casillas[i][j]=new Casilla(x,y);
                y= y+64;
            }
            x=x+64;
        }
    }


    @Override
    public void accionEntrada(Entrada entrada, float posX, float posY) {

        float x = 0;
        float y = 0;
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 30; j++) {
                if (casillas[i][j].esSeleccionado(posX, posY)){
                    casillas[i][j].accionEntrada(entrada);
                }

            }

        }
    }

    @Override
    public void accionEntrada(Entrada entrada) {

       /* float x = 0;
        float y = 0;
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 30; j++) {
                if (casillas[i][j].esSeleccionado(posX, posY)){
                    casillas[i][j].accionEntrada(entrada);
                }

            }

        }*/

    }
}
