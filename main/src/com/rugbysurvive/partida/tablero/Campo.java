package com.rugbysurvive.partida.tablero;

import com.rugbysurvive.partida.gestores.Entrada.GestionEntrada;
import com.rugbysurvive.partida.gestores.Entrada.*;
import com.rugbysurvive.partida.gestores.Dibujante;
import com.rugbysurvive.partida.Dibujables.*;

import java.util.ArrayList;

/**
 * Clase que crea y define el comportamiente del terreno del juego
 * Created by Victor on 24/03/14.
 */
public class Campo implements GestionEntrada {




    /**
     * indicara si el elemento esta selecionado
     */
    private boolean selecionado;


    /**
     *Coleccion de casillas que tenemos en nuestra pantalla
     */
    //ArrayList <Casilla> casillas= new ArrayList <Casilla>();

    Casilla [][] casillas= new Casilla [20][30];



    Dibujante dibujante;

    CampoDibujable campoDibujable;


    public Campo(Dibujante dibujante) {
        this.dibujante = dibujante;
        dibujarTablero(dibujante);
    }



    /**
     * Dibujamos el tablero de juego
     */
    public void dibujarTablero(Dibujante dibujante){
        float x = 0;
        float y = 0;
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 30; j++) {
                casillas[i][j]=new Casilla(x,y,dibujante);
                x= x+64;
            }
            x=0;
            y= y+64;

        }
    }


    @Override
    public void accionEntrada(Entrada entrada, float posX, float posY) {
        //seleccion:
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 30; j++) {
                if (casillas[i][j].esSeleccionado(posX, posY)){
                    casillas[i][j].accionEntrada(entrada);
                    //break seleccion;
                }

            }

        }
    }

    @Override
    public void accionEntrada(Entrada entrada) {

    }
}
