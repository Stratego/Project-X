package com.rugbysurvive.partida.tablero;

import com.rugbysurvive.partida.ConstantesJuego;
import com.rugbysurvive.partida.gestores.Dibujable;
import com.rugbysurvive.partida.gestores.Entrada.GestionEntrada;
import com.rugbysurvive.partida.gestores.Entrada.*;
import com.rugbysurvive.partida.gestores.Dibujante;
import com.rugbysurvive.partida.Dibujables.*;

import java.util.ArrayList;

/**
 * Clase que crea y define el comportamiente del terreno del juego
 * Created by Victor on 24/03/14.
 */
public class Campo implements GestionEntrada,Dibujable {

    /**
     * indicara si el elemento esta selecionado
     */
    private boolean selecionado;



    Casilla [][] casillas= new Casilla [20][30];



    Dibujante dibujante;




    public Campo(Dibujante dibujante)  {
        this.dibujante = dibujante;
        dibujante.añadirDibujable(this,TipoDibujo.fondo);
        this.dibujarTablero();
    }



    /**
     * Dibujamos el tablero de juego
     */
    protected void dibujarTablero(){
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 30; j++) {
                casillas[i][j]=new Casilla(i,j);
            }


        }
    }


    @Override
    public void accionEntrada(Entrada entrada, float posX, float posY) {

       double anchura = ConstantesJuego.variables().getAnchoCasilla();
       double altura = ConstantesJuego.variables().getLargoCasilla();

       for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 30; j++) {
                    casillas[i][j].accionEntrada(entrada,(int)(posX/anchura),(int)(posY/altura));
                }

            }
        }

    @Override
    public void accionEntrada(Entrada entrada) {

    }

    @Override
    public String getTextura() {
        return "campo1.png";
    }

    @Override
    public int getPosicionX() {
        return 0;
    }

    @Override
    public int getPosicionY() {
        return 0;
    }
}
